package org.demosoft.life.model.impl;

import org.demosoft.life.model.Cell;
import org.demosoft.life.model.GameMap;
import org.demosoft.life.model.Point;

public class GameMapPart implements GameMap {

    private final GameMap gameMap;
    private final Point topLeft;
    private final Point bottomRight;

    public GameMapPart(GameMap gameMap, Point topLeft, Point bottomRight) {
        this.gameMap = gameMap;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    @Override
    public int getMapSize() {
        return -1;
    }

    @Override
    public Cell[][] getCells() {
        Cell[][] resultCells = new Cell[getXSize()][getYSize()];
        Cell[][] cells = gameMap.getCells();
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                if (isInPart(cell)) {
                    resultCells[cell.getX() - topLeft.getX()][cell.getY() - topLeft.getY()] = cell;
                }
            }
        }

        return resultCells;
    }

    @Override
    public Cell getCellAt(int x, int y) {
        checkPoint(x, y);
        return gameMap.getCellAt(x, y);
    }

    @Override
    public void setCell(Cell cell) {
        checkPoint(cell);
        gameMap.setCell(cell);
    }

    @Override
    public void reCreate(int size) {
        gameMap.reCreate(size);
    }

    private int getXSize() {
        return (bottomRight.getX() + 1) - topLeft.getX();
    }

    private int getYSize() {
        return (bottomRight.getY() + 1) - topLeft.getY();
    }

    private boolean isInPart(int x, int y) {
        return topLeft.isAfterPoint(x, y) && bottomRight.isBeforePoint(x, y);
    }

    private boolean isInPart(Cell cell) {
        return topLeft.isAfterPoint(cell.getX(), cell.getY()) && bottomRight.isBeforePoint(cell.getX(), cell.getY());
    }

    private void checkPoint(Cell cell) {
        if (!isInPart(cell)) throw new IllegalArgumentException("point out of bounds");
    }

    private void checkPoint(int x, int y) {
        if (!isInPart(x, y)) throw new IllegalArgumentException("point out of bounds");
    }
}
