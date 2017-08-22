package org.demosoft.life.model.impl;

import org.demosoft.life.logic.random.XRandom;
import org.demosoft.life.model.*;
import org.demosoft.life.model.type.LandscapeType;
import org.demosoft.life.model.type.HumanType;
import org.demosoft.life.model.type.PlantType;

import java.util.Random;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class MapFactoryImpl implements MapFactory {

    private Random random = new Random();

    @Override
    public GameMap createMap(int size) {
        System.out.println("MapFactoryImpl map start createMap");
        MapImpl map = new MapImpl(size);
        System.out.println("MapFactoryImpl map end createMap");
        fill(map);
        return map;
    }

    private void fill(GameMap map) {
        int precent = 0;
        for (int i = 0; i < map.getMapSize(); i++) {
            int newPrecent = getPrecent(i, map);
            if (newPrecent != precent) {
                System.out.print("Fill: " + newPrecent + "% ");
                precent = newPrecent;
            }
            for (int j = 0; j < map.getMapSize(); j++) {
                map.setCell(this.createEmptyCell(i, j));
            }
        }
    }

    private int getPrecent(double i, GameMap map) {
        double v = i / map.getMapSize();
        v *= 100;
        return (int) v;
    }

    @Override
    public GameMap generateRandomMap(int size) {
        System.out.println("MapFactoryImpl gameMap start generateRandomMap");
        GameMap gameMap = createMap(size);
        generateLandscape(gameMap);
        generatePlants(gameMap, 20);
        generatePeoples(gameMap, 10, 10);
        System.out.println("MapFactoryImpl gameMap finish generateRandomMap");
        return gameMap;
    }


    @Override
    public void generatePeoples(GameMap gameMap, int menCount, int womanCount) {
        System.out.println("MapFactoryImpl gameMap start generatePeoples");
        MapImpl mapImpl = (MapImpl) gameMap;
        cleanHumans(mapImpl);
        for (int i = 0; i < menCount; i++) {
            if (menCount > 0) {
                menCount = tryGenerateMens(mapImpl, menCount);
            } else {
                break;
            }
        }

        for (int i = 0; i < womanCount; i++) {
            if (womanCount > 0) {
                womanCount = tryGenerateWomans(mapImpl, womanCount);
            } else {
                break;
            }
        }
        System.out.println("MapFactoryImpl gameMap end generatePeoples");
    }

    @Override
    public void generatePlants(GameMap gameMap, int count) {
        System.out.println("MapFactoryImpl gameMap start generatePlants");
        MapImpl mapImpl = (MapImpl) gameMap;
        cleanTrees(mapImpl);
        for (int i = 0; i < count; i++) {
            if (count > 0) {
                count = tryGeneratePlant(mapImpl, count);

            } else {
                break;
            }
        }
        System.out.println("MapFactoryImpl gameMap end generatePlants");
    }


    @Override
    public void generateLandscape(GameMap gameMap) {
        System.out.println("MapFactoryImpl gameMap start generateLandscape");
        MapImpl mapImpl = (MapImpl) gameMap;

        mapImpl.setCell(createCell(LandscapeType.LANDSCAPE_MAX_VALUE, 0, 0));
        mapImpl.setCell(createCell(LandscapeType.LANDSCAPE_MAX_VALUE, 0, mapImpl.getMapSize() - 1));
        mapImpl.setCell(createCell(LandscapeType.LANDSCAPE_MAX_VALUE, mapImpl.getMapSize() - 1, 0));
        mapImpl.setCell(createCell(LandscapeType.LANDSCAPE_MAX_VALUE, mapImpl.getMapSize() - 1, mapImpl.getMapSize() - 1));
        final float[] landscapeShift = {20};
        final double[] prcent = {0};
        int log = Integer.numberOfLeadingZeros(mapImpl.getMapSize() - 1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int bigStep = mapImpl.getMapSize() - 1; bigStep >= 2; bigStep /= 2, landscapeShift[0] /= 2.0f) {
                    int smallStep = bigStep / 2;
                    //diamond step
                    double newPercent = getPercent(log, bigStep);
                    if (prcent[0] != newPercent) {
                        System.out.print("Gen: " + newPercent + "% ");
                        prcent[0] = newPercent;
                    }
                    diamondStep(mapImpl, landscapeShift[0], bigStep, smallStep);
                    //square step
                    squareStep(mapImpl, landscapeShift[0], bigStep, smallStep);
                }
                System.out.println("MapFactoryImpl gameMap end generateLandscape");
            }
        }).start();

    }

    private double getPercent(int log, int bigStep) {
        int currentLog = Integer.numberOfLeadingZeros(bigStep);
        double v = Math.abs(((double) currentLog / log) - 1);
        int newRes = (int) (v * 10000);
        return (double) newRes / 100;
    }


    public CellImpl createCell(long value, int x, int y) {
        CellImpl cell = (CellImpl) this.createCell();
        cell.setValue(value);
        cell.setX(x);
        cell.setY(y);
        cell.setHuman(this.createHuman(cell));
        cell.setLandscape(this.createLandscape(cell));
        cell.setPlant(this.createPlant(cell));
        return cell;
    }

    @Override
    public Human createHuman(Cell cell) {
        return new HumanImpl((CellImpl) cell);
    }

    @Override
    public LandScape createLandscape(Cell cell) {
        return new LandscapeImpl((CellImpl) cell);
    }

    @Override
    public Plant createPlant(Cell cell) {
        return new PlantImpl((CellImpl) cell);
    }

    @Override
    public Cell createCell() {
        return new CellImpl();
    }

    @Override
    public Cell createEmptyCell(int x, int y) {
        return createCell(0, x, y);
    }

    private int tryGenerateWomans(GameMap gameMapImpl, int menCount) {
        for (Cell[] cellRow : gameMapImpl.getCells()) {
            for (Cell cell : cellRow) {
                LandscapeType landscape = cell.getLandscape().getType();
                HumanType human = cell.getHuman().getType();
                if (!landscape.isRockBlock() && !landscape.isWatterBlock() && human == HumanType.HUMAN_TYPE_EMPTY) {
                    if (XRandom.generateBoolean(1) && menCount > 0) {
                        menCount--;
                        placeWoman(cell);
                    }
                }
            }
        }
        return menCount;
    }

    private void placeWoman(Cell cell) {
        cell.getHuman().setType(HumanType.HUMAN_TYPE_WOMAN);
        initHuman(cell);
    }

    private void initHuman(Cell cell) {
        cell.getHuman().setAge(30 * 300);
        cell.getHuman().setEnergy(63);
        cell.getHuman().setSatiety(63);
    }

    private int tryGenerateMens(GameMap gameMapImpl, int menCount) {
        for (Cell[] cellRow : gameMapImpl.getCells()) {
            for (Cell cell : cellRow) {
                LandscapeType landscape = cell.getLandscape().getType();
                HumanType human = cell.getHuman().getType();
                if (!landscape.isRockBlock() && !landscape.isWatterBlock() && human == HumanType.HUMAN_TYPE_EMPTY) {
                    if (XRandom.generateBoolean(1) && menCount > 0) {
                        menCount--;
                        placeMan(cell);
                    }
                }
            }
        }
        return menCount;
    }

    private void placeMan(Cell cell) {
        cell.getHuman().setType(HumanType.HUMAN_TYPE_MAN);
        initHuman(cell);
    }

    private void cleanHumans(GameMap gameMap) {
        for (Cell[] cellRow : gameMap.getCells()) {
            for (Cell cell : cellRow) {
                if (cell.getHuman().getType() != HumanType.HUMAN_TYPE_EMPTY) {
                    cell.getHuman().setType(HumanType.HUMAN_TYPE_EMPTY);
                }
            }
        }
    }


    private void cleanTrees(GameMap gameMapImpl) {
        for (Cell[] cellRow : gameMapImpl.getCells()) {
            for (Cell cell : cellRow) {
                if (cell.getPlant().getType() != PlantType.PLANT_TYPE_EMPTY) {
                    cell.getPlant().setType(PlantType.PLANT_TYPE_EMPTY);
                }
            }
        }
    }

    private int tryGeneratePlant(GameMap gameMapImpl, int treesToPlace) {
        for (Cell[] cellRow : gameMapImpl.getCells()) {
            for (Cell cell : cellRow) {
                LandscapeType landscape = cell.getLandscape().getType();
                if (!landscape.isRockBlock() && !landscape.isWatterBlock()) {
                    if (XRandom.generateBoolean(1) && treesToPlace > 0) {
                        treesToPlace--;
                        placeTree(cell);
                    }
                }
            }
        }
        return treesToPlace;
    }

    private void placeTree(Cell cell) {
        PlantType plant = PlantType.getByValue(XRandom.generateInteger(1, 3));
        cell.getPlant().setType(plant);
        cell.getPlant().setFruits(30);
    }

    private void diamondStep(GameMap gameMap, float landscapeShift, int bigStep, int smallStep) {
        for (int x = smallStep; x < gameMap.getMapSize(); x += bigStep) {
            for (int y = smallStep; y < gameMap.getMapSize(); y += bigStep) {
                long topLeftValue = gameMap.getCellAt(x - smallStep, y - smallStep).getLandscape().getHeight();
                long topRightValue = gameMap.getCellAt(x + smallStep, y - smallStep).getLandscape().getHeight();
                long bottomLeftValue = gameMap.getCellAt(x - smallStep, y + smallStep).getLandscape().getHeight();
                long bottomRightValue = gameMap.getCellAt(x + smallStep, y + smallStep).getLandscape().getHeight();
                float average = (topLeftValue + topRightValue + bottomLeftValue + bottomRightValue) / 4;
                int centralValue = (int) (average + random.nextInt(3) * landscapeShift - landscapeShift); // -landscapeShift 0 landscapeShift
                gameMap.setCell(createCell(getValueInRange(centralValue, 1, LandscapeType.LANDSCAPE_MAX_VALUE), x, y));
            }
        }
    }

    private void squareStep(GameMap gameMap, float landscapeShift, int bigStep, int smallStep) {
        for (int x = 0; x < gameMap.getMapSize(); x += smallStep) {
            for (int y = (x + smallStep) % bigStep; y < gameMap.getMapSize(); y += bigStep) {
                long topValue = gameMap.getCellAt((y - smallStep + gameMap.getMapSize() - 1) % (gameMap.getMapSize() - 1), x).getLandscape().getHeight();
                long leftValue = gameMap.getCellAt(y, (x - smallStep + gameMap.getMapSize() - 1) % (gameMap.getMapSize() - 1)).getLandscape().getHeight();
                long rightValue = gameMap.getCellAt(y, (x + smallStep) % (gameMap.getMapSize() - 1)).getLandscape().getHeight();
                long bottomValue = gameMap.getCellAt((y + smallStep) % (gameMap.getMapSize() - 1), x).getLandscape().getHeight();
                float avg = (topValue + leftValue + rightValue + bottomValue) / 4;
                int centerValue = getValueInRange((int) (avg + this.random.nextInt(3) * landscapeShift - landscapeShift), 1, LandscapeType.LANDSCAPE_MAX_VALUE);
                gameMap.setCell(createCell(centerValue, y, x));
            }
        }
    }

    private int getValueInRange(int value, int min, int max) {
        return Math.max(Math.min(value, max), min);
    }
}
