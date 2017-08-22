package org.demosoft.life.model.graphic;


import org.demosoft.life.model.LandScape;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface GraphicLandscape extends LandScape {

    int getColor();

    void setColor(int color);

    String getMessage();

    void setMessage(String message);
}
