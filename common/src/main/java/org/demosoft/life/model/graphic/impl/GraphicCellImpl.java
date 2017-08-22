package org.demosoft.life.model.graphic.impl;


import org.demosoft.life.model.Human;
import org.demosoft.life.model.LandScape;
import org.demosoft.life.model.Plant;
import org.demosoft.life.model.graphic.GraphicCell;
import org.demosoft.life.model.graphic.GraphicHuman;
import org.demosoft.life.model.graphic.GraphicLandscape;
import org.demosoft.life.model.graphic.GraphicPlant;
import org.demosoft.life.model.impl.CellImpl;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class GraphicCellImpl extends CellImpl implements GraphicCell {
    private CellImpl baseCell;

    public GraphicCellImpl(CellImpl baseCell) {
        super();
        this.baseCell = baseCell;
    }

    @Override
    public boolean getActiveFlagHuman() {
        return baseCell.getActiveFlagHuman();
    }

    @Override
    public boolean getActiveFlagPlant() {
        return baseCell.getActiveFlagPlant();
    }

    @Override
    public Human getHuman() {
        return baseCell.getHuman();
    }

    @Override
    public LandScape getLandscape() {
        return baseCell.getLandscape();
    }

    @Override
    public Plant getPlant() {
        return baseCell.getPlant();
    }

    @Override
    public long getValue() {
        return baseCell.getValue();
    }

    @Override
    public int getX() {
        return baseCell.getX();
    }

    @Override
    public int getY() {
        return baseCell.getY();
    }

    @Override
    public void setActiveFlagHuman(boolean activeFlagHuman) {
        baseCell.setActiveFlagHuman(activeFlagHuman);
    }

    @Override
    public void setActiveFlagPlant(boolean activeFlagPlant) {
        baseCell.setActiveFlagPlant(activeFlagPlant);
    }

    @Override
    public void setHuman(Human human) {
        baseCell.setHuman(human);
    }

    @Override
    public void setLandscape(LandScape plant) {
        baseCell.setLandscape(plant);
    }

    @Override
    public void setPlant(Plant plant) {
        baseCell.setPlant(plant);
    }

    @Override
    public void setValue(long value) {
        baseCell.setValue(value);
    }

    @Override
    public void setX(int x) {
        baseCell.setX(x);
    }

    @Override
    public void setY(int y) {
        baseCell.setY(y);
    }

    @Override
    public void setGraphicHuman(GraphicHuman human) {
        baseCell.setHuman(human);
    }

    @Override
    public GraphicHuman getGraphicHuman() {
        return (GraphicHuman) baseCell.getHuman();
    }

    @Override
    public void setGraphicPlant(GraphicPlant plant) {
        baseCell.setPlant(plant);
    }

    @Override
    public GraphicPlant getGraphicPlant() {
        return (GraphicPlant) baseCell.getPlant();
    }

    @Override
    public void setGraphicLandscape(GraphicLandscape landscape) {
        baseCell.setLandscape(landscape);
    }

    @Override
    public GraphicLandscape getGraphicLandscape() {
        return (GraphicLandscape) baseCell.getLandscape();
    }
}
