package org.demosoft.life.model;


import org.demosoft.life.model.type.LandscapeType;

import java.io.Serializable;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface LandScape extends Serializable {

    LandscapeType getType();

    void setType(LandscapeType landscapeType);

    int getHeight();

    void setHeight(int height);
}
