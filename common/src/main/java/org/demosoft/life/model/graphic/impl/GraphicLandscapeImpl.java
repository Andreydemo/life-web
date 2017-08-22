package org.demosoft.life.model.graphic.impl;

import org.demosoft.life.model.LandScape;
import org.demosoft.life.model.graphic.GraphicLandscape;
import org.demosoft.life.model.type.LandscapeType;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public class GraphicLandscapeImpl implements GraphicLandscape {

    private LandScape baseLandscape;

    public GraphicLandscapeImpl(LandScape baseLandscape) {

        this.baseLandscape = baseLandscape;
    }

    @Override
    public LandscapeType getType() {
        return baseLandscape.getType();
    }

    @Override
    public void setHeight(int height) {
        baseLandscape.setHeight(height);
    }

    @Override
    public void setType(LandscapeType landscapeType) {
        baseLandscape.setType(landscapeType);
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

    @Override
    public int getHeight() {
        return baseLandscape.getHeight();
    }
}
