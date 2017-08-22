package org.demosoft.life.model;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface MapFactory {

    /**
     * mapSize = (int) (Math.pow(2, size) + 1);
     * @param size
     * @return
     */
    GameMap createMap(int size);

    GameMap generateRandomMap(int size);

    void generateLandscape(GameMap gameMap);

    Cell createCell();

    Cell createEmptyCell(int x, int y);

    Human createHuman(Cell cell);

    LandScape createLandscape(Cell cell);

    Plant createPlant(Cell cell);

    void generatePeoples(GameMap gameMap, int menCount, int womanCount);

    void generatePlants(GameMap gameMap, int count);

}
