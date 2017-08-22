package org.demosoft.life.model.graphic;


import org.demosoft.life.model.Cell;

/**
 * Created by Andrii_Korkoshko on 1/24/2017.
 */
public interface GraphicCell extends Cell {

    void setGraphicHuman(GraphicHuman human);

    GraphicHuman getGraphicHuman();

    void setGraphicPlant(GraphicPlant plant);

    GraphicPlant getGraphicPlant();

    void setGraphicLandscape(GraphicLandscape plant);

    GraphicLandscape getGraphicLandscape();


}
