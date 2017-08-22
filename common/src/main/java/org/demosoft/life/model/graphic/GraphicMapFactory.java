package org.demosoft.life.model.graphic;


import org.demosoft.life.model.MapFactory;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface GraphicMapFactory extends MapFactory {
    @Override
    GraphicMap createMap(int size);

    @Override
    GraphicMap generateRandomMap(int size);

}
