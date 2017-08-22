package org.demosoft.life.model;


import org.demosoft.life.model.type.HumanType;

import java.io.Serializable;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface Human extends Serializable {

    void setType(HumanType humanType);

    HumanType getType();

    void setAge(int age);

    int getAge();

    void setEnergy(int energy);

    int getEnergy();

    void setSatiety(int satiety);

    int getSatiety();

    void setPregnancy(int pregnancy);

    int getPregnancy();
}
