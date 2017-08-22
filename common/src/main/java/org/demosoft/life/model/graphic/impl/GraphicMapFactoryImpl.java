package org.demosoft.life.model.graphic.impl;


import org.demosoft.life.model.*;
import org.demosoft.life.model.graphic.GraphicMap;
import org.demosoft.life.model.graphic.GraphicMapFactory;
import org.demosoft.life.model.impl.CellImpl;
import org.demosoft.life.model.impl.MapFactoryImpl;
import org.demosoft.life.model.impl.MapImpl;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii_Korkoshko on 2/15/2017.
 */
@Component
public class GraphicMapFactoryImpl extends MapFactoryImpl implements GraphicMapFactory {

    @Override
    public GraphicMap createMap(int size) {
        System.out.println("GraphicMapFactoryImpl map start createMap");
        GraphicMapImpl graphicMap = new GraphicMapImpl((MapImpl) super.createMap(size));
        System.out.println("GraphicMapFactoryImpl map end createMap");
        return graphicMap;
    }

    @Override
    public void generateLandscape(GameMap map) {
        System.out.println("GraphicMapFactoryImpl map start generateLandscape");
        super.generateLandscape(map);
        ((GraphicMap) map).generateMiniMap();
        System.out.println("GraphicMapFactoryImpl map end generateLandscape");
    }


    @Override
    public Cell createCell() {
        return new GraphicCellImpl((CellImpl) super.createCell());
    }

    @Override
    public Human createHuman(Cell cell) {
        return new GraphicHumanImpl(super.createHuman(cell));
    }

    @Override
    public LandScape createLandscape(Cell cell) {
        return new GraphicLandscapeImpl(super.createLandscape(cell));
    }

    @Override
    public Plant createPlant(Cell cell) {
        return new GraphicPlantImpl(super.createPlant(cell));
    }

    @Override
    public void generatePeoples(GameMap map, int menCount, int womanCount) {
        System.out.println("GraphicMapFactoryImpl map start generatePeoples");
        super.generatePeoples(map, menCount, womanCount);
        System.out.println("GraphicMapFactoryImpl map end generatePeoples");
    }

    @Override
    public void generatePlants(GameMap map, int count) {
        System.out.println("GraphicMapFactoryImpl map start generatePlants");
        super.generatePlants(map, count);
        System.out.println("GraphicMapFactoryImpl map end generatePlants");
    }

    @Override
    public GraphicMap generateRandomMap(int size) {
        System.out.println("GraphicMapFactoryImpl map start generateRandomMap");
        GraphicMapImpl graphicMap = (GraphicMapImpl) super.generateRandomMap(size);
        System.out.println("GraphicMapFactoryImpl map end generateRandomMap");
        return graphicMap;
    }

}
