package org.demosoft.life.web;

import org.demosoft.life.MapService;
import org.demosoft.life.logic.force.Force;
import org.demosoft.life.model.GameMap;
import org.demosoft.life.model.Point;
import org.demosoft.life.web.model.CreateMapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapController {


    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GameMap getMap(@PathVariable("id") String id,
                          @RequestParam(required = false) Point topLeft, @RequestParam(required = false) Point bottomRight) {
        return mapService.getMapPart(id, topLeft, bottomRight);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CreateMapResponse createMap(@RequestParam int size) {
        return new CreateMapResponse(mapService.generateMap(size));
    }

    @RequestMapping(value = "/force", method = RequestMethod.POST)
    public Force createForce(@RequestParam String mapId) {
        return mapService.makeForce(mapId);
    }


}
