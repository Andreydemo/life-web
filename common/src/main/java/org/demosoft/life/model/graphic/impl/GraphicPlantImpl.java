package org.demosoft.life.model.graphic.impl;

import org.demosoft.life.model.Plant;
import org.demosoft.life.model.graphic.GraphicPlant;
import org.demosoft.life.model.type.PlantType;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class GraphicPlantImpl implements GraphicPlant {
    private Plant plant;

    public GraphicPlantImpl(Plant plant) {
        this.plant = plant;
    }

    public int getFruits() {
        return plant.getFruits();
    }

    public void setFruits(int fruits) {
        plant.setFruits(fruits);
    }

    public void setType(PlantType plantType) {
        plant.setType(plantType);
    }

    public PlantType getType() {
        return plant.getType();
    }

    @Override
    public int getColor() {
        return getType().getColor();
    }

    @Override
    public void setColor(int color) {
        throw new IllegalArgumentException();
    }

    @Override
    public String getMessage() {
        return getType().getMessage();
    }

    @Override
    public void setMessage(String message) {
        throw new IllegalArgumentException();
    }
}
