package org.demosoft.life.model;


import java.io.Serializable;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface GameMap extends Serializable {

    int getMapSize();

    Cell[][] getCells();

    Cell getCellAt(int x, int y);

    void setCell(Cell cell);

    void reCreate(int size);
}
