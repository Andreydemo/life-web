package org.demosoft.life.model.impl;


import org.demosoft.life.model.Cell;
import org.demosoft.life.model.GameMap;

/**
 * Created by Andrii_Korkoshko on 1/24/2017.
 */
public class MapImpl implements GameMap {

    public final static int CELL_SIZE = 10;
    public static final int TREES_TO_PLACE = 20;

    private int mapSize;

    private Cell[][] cells;

    public MapImpl() {
    }

    public MapImpl(int size) {
        this.mapSize = (int) (Math.pow(2, size) + 1);
        cells = new Cell[mapSize][mapSize];
    }



    public void setCell(Cell cell) {
        cells[cell.getX()][cell.getY()] = cell;
    }

    @Override
    public void reCreate(int size) {
        this.mapSize = size;
        long time = System.currentTimeMillis();
        System.out.println("reCreate map init start");
        cells = new Cell[size][size];
        System.out.println("reCreate map init end" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        System.out.println("reCreate map fill start");
    }

    public int getMapSize() {
        return mapSize;
    }

    @Override
    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public Cell getCellAt(int x, int y) {
        /*if (notInRange(x, y)) {
            return mapFactory.createEmptyCell(x, y);
        }
        if (cells[x][y] == null) {
            cells[x][y] = mapFactory.createCell();
        }*/
        return cells[x][y];
    }

    private boolean notInRange(int x, int y) {
        return x > mapSize - 1 || x < 0 || y > mapSize - 1 || y < 0;
    }

}
