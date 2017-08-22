package org.demosoft.life.impl;

import org.demosoft.life.MapService;
import org.demosoft.life.logic.force.Force;
import org.demosoft.life.logic.force.MapInformationController;
import org.demosoft.life.model.GameMap;
import org.demosoft.life.model.MapFactory;
import org.demosoft.life.model.Point;
import org.demosoft.life.model.impl.GameMapPart;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MapServiceImpl implements MapService {

    private final MapFactory mapFactory;

    public MapServiceImpl(MapFactory mapFactory) {
        this.mapFactory = mapFactory;
    }

    private Map<String, GameMap> maps = new ConcurrentHashMap();

    @Override
    public String generateMap(int size) {
        String id = UUID.randomUUID().toString();
        maps.put(id, mapFactory.createMap(size));
        return id;
    }

    @Override
    public GameMap getMap(String id) {
        return maps.get(id);
    }

    @Override
    public GameMap getMapPart(String id, Point topLeft, Point bottomRight) {
        GameMap map = getMap(id);
        return new GameMapPart(map, topLeft, bottomRight);
    }

    @Override
    public Force makeForce(String gameMapId) {
        GameMap gameMap = maps.get(gameMapId);
        return new Force(gameMap);
    }
}
