package org.demosoft.life.model.impl;

import org.demosoft.life.logic.math.XMath;
import org.demosoft.life.model.Plant;
import org.demosoft.life.model.type.PlantType;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class PlantImpl implements Plant {

    private CellImpl relatedCell;

    public PlantImpl(CellImpl relatedCell) {
        this.relatedCell = relatedCell;
    }

    @Override
    public PlantType getType() {
        return PlantType.decodeAndGetByValue(relatedCell.getValue());
    }

    @Override
    public void setType(PlantType plantType) {
        relatedCell.setValue(UcfCoder.encodePlantType(relatedCell.getValue(), XMath.getValueInRange(plantType.getValue(), 0, 1)));
    }

    @Override
    public int getFruits() {
        return UcfCoder.decodePlantFruits(relatedCell.getValue());
    }

    @Override
    public void setFruits(int fruits) {
        relatedCell.setValue(UcfCoder.encodePlantFruits(relatedCell.getValue(), XMath.getValueInRange(fruits, 0, 63)));
    }
}
