package org.demosoft.life.model.graphic;


import org.demosoft.life.model.Human;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface GraphicHuman extends Human {

    String getMessage();

    void setMessage(String message);

    int getColor();

    void setColor(int color);

}
