package org.demosoft.life.model;


import org.demosoft.life.model.type.PlantType;

import java.io.Serializable;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface Plant extends Serializable {

    PlantType getType();

    void setType(PlantType plantType);

    int getFruits();

    void setFruits(int fruits);
}
