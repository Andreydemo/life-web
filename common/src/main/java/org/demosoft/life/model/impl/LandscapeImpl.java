package org.demosoft.life.model.impl;

import org.demosoft.life.logic.math.XMath;
import org.demosoft.life.model.type.LandscapeType;
import org.demosoft.life.model.LandScape;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class LandscapeImpl implements LandScape {
    private CellImpl relatedCell;

    public LandscapeImpl(CellImpl relatedCell) {
        this.relatedCell = relatedCell;
    }

    @Override
    public LandscapeType getType() {
        return LandscapeType.decodeAndGetByValue(relatedCell.getValue());
    }

    @Override
    public void setType(LandscapeType landscapeType) {
        relatedCell.setValue(UcfCoder.encodeLandscapeType(relatedCell.getValue(), XMath.getValueInRange(landscapeType.getValue(), 0, 7)));
    }

    @Override
    public int getHeight() {
        return getType().getValue();
    }

    @Override
    public void setHeight(int height) {
        setType(LandscapeType.decodeAndGetByValue(height));
    }
}
