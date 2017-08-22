package org.demosoft.life.logic.force;

import lombok.Getter;
import org.demosoft.life.model.*;
import org.demosoft.life.logic.random.XRandom;
import org.demosoft.life.logic.statistic.Statistic;
import org.demosoft.life.model.impl.CellImpl;
import org.demosoft.life.model.type.HumanType;
import org.demosoft.life.model.type.LandscapeType;
import org.demosoft.life.model.type.PlantType;

import javax.swing.*;
import java.io.Serializable;


public class Force implements Serializable {

    private static int date = 1;
    private static Timer timer;
    private static int timerDelay = 50;

    @Getter
    private final GameMap gameMap;
    @Getter
    private final Statistic statistic;
    @Getter
    private final MapInformationController mapInformationController;

    public Force(GameMap gameMap) {
        this.gameMap = gameMap;
        this.mapInformationController = new MapInformationController(gameMap);
        this.statistic = mapInformationController.getStatistic();
    }

    public void start() {
        if (timer == null) {
            timer = new Timer(timerDelay, e -> {
                for (int y = 0; y < gameMap.getMapSize(); y++) {
                    for (int x = 0; x < gameMap.getMapSize(); x++) {
                        gameMap.getCellAt(y, x).setActiveFlagHuman(true);
                        gameMap.getCellAt(y, x).setActiveFlagPlant(true);
                    }
                }
                for (int y = 0; y < gameMap.getMapSize(); y++) {
                    for (int x = 0; x < gameMap.getMapSize(); x++) {
                        act(((CellImpl) gameMap.getCellAt(x, y)).getValue(), y, x);
                    }
                }
                statistic.update();
                mapInformationController.update(++date);
            });
        }
        timer.start();
    }

    public void pause() {
        if (timer != null) {
            timer.stop();
        }
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }
        date = 1;
    }

    // ACT
    private void act(long cellData, int y, int x) {
        Cell cell = gameMap.getCellAt(x, y);
        if (cell.getActiveFlagHuman() && cell.getHuman().getType() != HumanType.HUMAN_TYPE_EMPTY) {
            if (tryToDie(cell)
                    || tryToGiveBirth(cell)
                    || tryToSleep(cell)
                    || tryToEat(cell)
                    || tryToMakeChild(cell)
                    || tryToMove(cell, 0, 0)) {/*Do nothing*/}
        }
        if (cell.getActiveFlagPlant() && cell.getPlant().getType() != PlantType.PLANT_TYPE_EMPTY) {
            tryToMakeFruits(y, x);
            tryToDropFruit(y, x);
        }
    }

    // HUMAN - DIE
    private boolean tryToDie(Cell cell) {
        Human human = cell.getHuman();
        int energy = human.getEnergy();
        if (energy == 0) {
            clearHuman(human);
            statistic.peopleDied++;
            statistic.peopleDiedByEnergy++;
            mapInformationController.getEventLog().add(date, "Human died by [Low Energy].");
            return true;
        }
        int satiety = human.getSatiety();
        if (satiety == 0) {
            clearHuman(human);
            statistic.peopleDied++;
            statistic.peopleDiedBySatiety++;
            mapInformationController.getEventLog().add(date, "Human died [Low Satiety].");
            return true;
        }
        // f(x): f(0..9000) <= 0, f(24000..32767) >= 100;
        // f(x) = 2*(x/300) - 60;
        // Every year + 2% (since 31 years)
        boolean decision = XRandom.generateBoolean(2 * (human.getAge() / 350) - 60);
        if (decision) {
            clearHuman(human);
            statistic.peopleDied++;
            statistic.peopleDiedByAge++;
            mapInformationController.getEventLog().add(date, "GraphicHuman died [Age].");
            return true;
        }
        return false;
    }

    // HUMAN - EAT
    private boolean tryToEat(Cell cell) {
        // f(x): f(0..10) >= 100, f(60..64) <= 0;
        // f(x) = 120 - 2*x;
        boolean decision = XRandom.generateBoolean(120 - 2 * cell.getHuman().getSatiety());
        if (decision) {
            int y = cell.getY();
            int x = cell.getX();
            for (int yShift = -1; yShift < 2; yShift++) {
                for (int xShift = -1; xShift < 2; xShift++) {
                    int yTarget = y + yShift;
                    int xTarget = x + xShift;
                    if (isCellInMapRange(yTarget, xTarget)) {
                        Plant plant = gameMap.getCellAt(xTarget, yTarget).getPlant();
                        Human human = cell.getHuman();
                        int fruitsTarget = plant.getFruits();
                        if (fruitsTarget != 0) {
                            plant.setFruits(--fruitsTarget);
                            if (human.getAge() < 10) {
                                human.setSatiety(human.getSatiety() + 32);
                            } else {
                                human.setSatiety(human.getSatiety() + 16);
                            }
                            human.setEnergy(human.getEnergy() - 1);
                            human.setAge(human.getAge() + 1);
                            cell.setActiveFlagHuman(false);
                            return true;
                        }
                    }
                }
            }
            int minTarget = gameMap.getMapSize() * gameMap.getMapSize() + gameMap.getMapSize() * gameMap.getMapSize();
            int yTarget = y;
            int xTarget = x;
            for (int yTemp = 0; yTemp < gameMap.getMapSize(); yTemp++) {
                for (int xTemp = 0; xTemp < gameMap.getMapSize(); xTemp++) {
                    if (gameMap.getCellAt(xTemp, yTemp).getPlant().getFruits() != 0) {
                        int yDelta = Math.abs(yTemp - y);
                        int xDelta = Math.abs(xTemp - x);
                        int minTemp = yDelta * yDelta + xDelta * xDelta;
                        if (minTemp < minTarget) {
                            minTarget = minTemp;
                            yTarget = yTemp;
                            xTarget = xTemp;
                        }
                    }
                }
            }
            if (yTarget != y || xTarget != x) {
                int yShift = 0;
                int xShift = 0;
                int yDelta = yTarget - y;
                int xDelta = xTarget - x;
                if (yDelta < 0) {
                    yShift = -1;
                } else if (yDelta > 0) {
                    yShift = 1;
                }
                if (xDelta < 0) {
                    xShift = -1;
                } else if (xDelta > 0) {
                    xShift = 1;
                }
                return tryToMove(cell, yShift, xShift);
            }
        }
        return false;
    }

    // HUMAN - SLEEP
    private boolean tryToSleep(Cell cell) {
        // f(x): f(0..10) >= 100, f(60..64) <= 0;
        // f(x) = 120 - 2*x;
        boolean decision = XRandom.generateBoolean(120 - 2 * cell.getHuman().getEnergy());
        if (decision) {
            Human human = cell.getHuman();
            human.setEnergy(63);
            human.setSatiety(human.getSatiety() - 1);
            human.setAge(human.getAge() + 1);
            cell.setActiveFlagHuman(false);
            return true;
        }
        return false;
    }

    // HUMAN - MAKE CHILD
    private boolean tryToMakeChild(Cell cell) {
        Human human = cell.getHuman();
        if (human.getType() == HumanType.HUMAN_TYPE_WOMAN && human.getPregnancy() == 0) {
            for (int yShift = -1; yShift < 2; yShift++) {
                for (int xShift = -1; xShift < 2; xShift++) {
                    int yTarget = cell.getY() + yShift;
                    int xTarget = cell.getX() + xShift;
                    Cell targetCell = gameMap.getCellAt(xTarget, yTarget);
                    Human targetHuman = targetCell.getHuman();
                    if (isCellInMapRange(yTarget, xTarget)
                            && targetHuman.getType() == HumanType.HUMAN_TYPE_MAN
                            && targetCell.getActiveFlagHuman()) {
                        boolean decision = XRandom.generateBoolean(30);
                        if (decision) {
                            human.setPregnancy(1);
                            human.setEnergy(human.getEnergy() - 4);
                            human.setSatiety(human.getSatiety() - 4);
                            human.setAge(human.getAge() + 1);
                            cell.setActiveFlagHuman(false);
                            targetHuman.setEnergy(targetHuman.getEnergy() - 4);
                            targetHuman.setSatiety(targetHuman.getSatiety() - 4);
                            targetHuman.setAge(targetHuman.getAge() + 1);
                            targetCell.setActiveFlagHuman(false);
                            mapInformationController.getEventLog().add(date, "Woman got pregnant.");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // HUMAN - GIVE BIRTH
    private boolean tryToGiveBirth(Cell cell) {
        Human human = cell.getHuman();
        int pregnancy = human.getPregnancy();
        if (pregnancy != 0 && pregnancy < 300) {
            human.setPregnancy(human.getPregnancy() + 1);
        } else if (pregnancy == 300) {
            human.setPregnancy(0);
            human.setEnergy(human.getEnergy() - 4);
            human.setSatiety(human.getSatiety() - 4);
            human.setAge(human.getAge() + 1);
            cell.setActiveFlagHuman(false);
            for (int yShift = -1; yShift < 2; yShift++) {
                for (int xShift = -1; xShift < 2; xShift++) {
                    int yTarget = cell.getY() + yShift;
                    int xTarget = cell.getX() + xShift;
                    Cell targetCell = gameMap.getCellAt(xTarget, yTarget);
                    Human targetHuman = targetCell.getHuman();
                    if (isCellInMapRange(yTarget, xTarget) && targetHuman.getType() == HumanType.HUMAN_TYPE_EMPTY) {
                        targetHuman.setType(XRandom.generateBoolean() ? HumanType.HUMAN_TYPE_MAN : HumanType.HUMAN_TYPE_WOMAN);
                        targetHuman.setAge(301);
                        targetHuman.setEnergy(63);
                        targetHuman.setSatiety(63);
                        targetHuman.setPregnancy(0);
                        targetCell.setActiveFlagHuman(false);
                        statistic.childrenWereBorn++;
                        mapInformationController.getEventLog().add(date, "Child was born.");
                        return true;
                    }
                }
            }
            human.setPregnancy(0);
            statistic.childrenDied++;
            mapInformationController.getEventLog().add(date, "Child died.");
            return true;
        }
        return false;
    }

    // HUMAN - MOVE
    private boolean tryToMove(Cell cell, int yShift, int xShift) {
        int x = cell.getX();
        int y = cell.getY();
        if (yShift == 0 && xShift == 0) {
            int tryCount = 10;
            do {
                yShift = XRandom.generateInteger(-1, 1);
                xShift = XRandom.generateInteger(-1, 1);
                tryCount--;
            }
            while (tryCount > 0 || !isCellInMapRange(x + xShift, y + yShift) || !canMove(gameMap.getCellAt(x + xShift, y + yShift)));
        }
        if (yShift != 0 || xShift != 0) {
            int yTarget = y + yShift;
            int xTarget = x + xShift;
            if (!isCellInMapRange(yTarget, xTarget)) {
                clearHuman(cell.getHuman());
                statistic.peopleDied++;
                statistic.peopleDiedByLost++;
                mapInformationController.getEventLog().add(date, "GraphicHuman died [Lost].");
                return true;
            }
            Cell targetCell = gameMap.getCellAt(xTarget, yTarget);
            Human targetHuman = targetCell.getHuman();
            LandScape targetLandscape = targetCell.getLandscape();
            if (targetHuman.getType() == HumanType.HUMAN_TYPE_EMPTY) {
                moveHuman(y, x, yTarget, xTarget);
                if (targetLandscape.getType() == LandscapeType.LANDSCAPE_TYPE_WATER_LOW || targetLandscape.getType() == LandscapeType.LANDSCAPE_TYPE_WATER_HIGH) {
                    targetHuman.setEnergy(targetHuman.getEnergy() - 3);
                    targetHuman.setSatiety(targetHuman.getSatiety() - 3);
                } else {
                    targetHuman.setEnergy(targetHuman.getEnergy() - 2);
                    targetHuman.setSatiety(targetHuman.getSatiety() - 2);
                }
                targetHuman.setAge(targetHuman.getAge() + 1);
                targetCell.setActiveFlagHuman(false);
                return true;
            }
        }
        Human human = cell.getHuman();
        human.setEnergy(human.getEnergy() - 1);
        human.setSatiety(human.getSatiety() - 1);
        human.setAge(human.getAge() + 1);
        cell.setActiveFlagHuman(false);
        return false;
    }

    // PLANT - MAKE FRUITS
    private void tryToMakeFruits(int y, int x) {
        if (date % 30 == 0) {
            Plant plant = gameMap.getCellAt(x, y).getPlant();
            plant.setFruits(plant.getFruits() + XRandom.generateInteger(5, 15));
        }
    }

    // PLANT - DROP FRUIT
    private void tryToDropFruit(int y, int x) {
        Plant plant = gameMap.getCellAt(x, y).getPlant();
        if (plant.getFruits() == 0) {
            return;
        }
        // 0.01 * 0.50 = 0.005
        boolean decision = XRandom.generateBoolean(1) && XRandom.generateBoolean(50);
        if (decision) {
            plant.setFruits(plant.getFruits() - 1);
            int yTarget = y + XRandom.generateInteger(-2, 2);
            int xTarget = x + XRandom.generateInteger(-2, 2);
            Cell targetCell = gameMap.getCellAt(xTarget, yTarget);
            if (isCellInMapRange(yTarget, xTarget) && (yTarget != y || xTarget != x)) {
                LandscapeType landscapeTarget = targetCell.getLandscape().getType();
                HumanType humanTarget = targetCell.getHuman().getType();
                PlantType plantTarget = targetCell.getPlant().getType();
                if (landscapeTarget != LandscapeType.LANDSCAPE_TYPE_WATER_LOW
                        && landscapeTarget != LandscapeType.LANDSCAPE_TYPE_WATER_HIGH
                        && humanTarget == HumanType.HUMAN_TYPE_EMPTY
                        && plantTarget == PlantType.PLANT_TYPE_EMPTY && canMove(targetCell)) {
                    targetCell.getPlant().setType(PlantType.PLANT_TYPE_APPLE);
                }
            }
        }
    }

    private void moveHuman(int yFrom, int xFrom, int yTo, int xTo) {
        Human humanFrom = gameMap.getCellAt(xFrom, yFrom).getHuman();
        Human humanTo = gameMap.getCellAt(xTo, yTo).getHuman();
        humanTo.setType(humanFrom.getType());
        humanTo.setAge(humanFrom.getAge());
        humanTo.setEnergy(humanFrom.getEnergy());
        humanTo.setSatiety(humanFrom.getSatiety());
        humanTo.setPregnancy(humanFrom.getPregnancy());
        clearHuman(humanFrom);
    }

    private void clearHuman(Human human) {
        human.setType(HumanType.HUMAN_TYPE_EMPTY);
        human.setAge(0);
        human.setEnergy(0);
        human.setSatiety(0);
        human.setPregnancy(0);
    }

    private boolean isCellInMapRange(int y, int x) {
        return y >= 0 && y < gameMap.getMapSize() && x >= 0 && x < gameMap.getMapSize();
    }

    private boolean canMove(Cell cell) {
        return !cell.getLandscape().getType().isRockBlock() && !cell.getLandscape().getType().isWatterBlock();
    }
}