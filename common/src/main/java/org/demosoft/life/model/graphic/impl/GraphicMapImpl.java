package org.demosoft.life.model.graphic.impl;

import com.sun.prism.Texture;
import org.demosoft.life.model.Cell;
import org.demosoft.life.model.graphic.GraphicCell;
import org.demosoft.life.model.graphic.GraphicMap;
import org.demosoft.life.model.impl.MapImpl;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class GraphicMapImpl implements GraphicMap {
    private MapImpl map;

    private int selectedY;
    private int selectedX;

    private Texture miniMap;

    public GraphicMapImpl(MapImpl map) {
        this.map = map;
    }

    @Override
    public GraphicCell getCellAt(int x, int y) {
        return (GraphicCell) map.getCellAt(x, y);
    }

    @Override
    public Cell[][] getCells() {
        return map.getCells();
    }

    @Override
    public int getMapSize() {
        return map.getMapSize();
    }

    @Override
    public void reCreate(int size) {
        map.reCreate(size);
    }

    @Override
    public void setCell(Cell cell) {
        map.setCell(cell);
    }

    public int getSelectedY() {
        return selectedY;
    }

    public int getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }

    public Texture getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(Texture miniMap) {
        this.miniMap = miniMap;
    }

    @Override
    public void generateMiniMap() {
        /*Pixmap pixmap = new Pixmap(map.getMapSize(), map.getMapSize(), Pixmap.Format.RGBA8888);
        for (int x = 0; x < map.getMapSize(); x++) {
            for (int y = 0; y < map.getMapSize(); y++) {
                pixmap.drawPixel(x, y, getCellAt(x, y).getGraphicLandscape().getColor());
            }
        }
        miniMap = new Texture(pixmap);*/
    }
}
