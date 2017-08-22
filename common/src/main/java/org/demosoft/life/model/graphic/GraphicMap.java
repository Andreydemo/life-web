package org.demosoft.life.model.graphic;


import com.sun.prism.Texture;
import org.demosoft.life.model.GameMap;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
public interface GraphicMap extends GameMap {

    int getSelectedY();

    int getSelectedX();

    void setSelectedX(int selectedX);

    void setSelectedY(int selectedY);

    GraphicCell getCellAt(int x, int y);

    Texture getMiniMap();

    void setMiniMap(Texture miniMap);

    void generateMiniMap();
}
