package org.demosoft.life.model.graphic;


import org.demosoft.life.model.Plant;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface GraphicPlant extends Plant {

    int getColor();

    void setColor(int color);

    String getMessage();

    void setMessage(String message);
}
