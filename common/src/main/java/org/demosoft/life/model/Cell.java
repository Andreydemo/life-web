package org.demosoft.life.model;

import java.io.Serializable;

/**
 * Created by Andrii_Korkoshko on 1/24/2017.
 */
public interface Cell extends Serializable {

    /*  long getValue();

      void setValue(long Value);
  */
    int getY();

    void setY(int y);

    int getX();

    void setX(int x);

    void setHuman(Human human);

    Human getHuman();

    void setPlant(Plant plant);

    Plant getPlant();

    void setLandscape(LandScape plant);

    LandScape getLandscape();

    void setActiveFlagHuman(boolean activeFlagHuman);

    boolean getActiveFlagHuman();

    void setActiveFlagPlant(boolean activeFlagPlant);

    boolean getActiveFlagPlant();

}
