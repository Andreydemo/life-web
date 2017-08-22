package org.demosoft.life;

import org.demosoft.life.logic.force.Force;
import org.demosoft.life.model.GameMap;
import org.demosoft.life.model.Point;

public interface MapService {

    /**
     * mapSize = (int) (Math.pow(2, size) + 1);
     *
     * @param size
     * @return
     */
    String generateMap(int size);

    GameMap getMap(String id);

    GameMap getMapPart(String id, Point topLeft, Point bottomRight);

    Force makeForce(String gameMapId);
}
