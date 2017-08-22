package org.demosoft.life.logic.force;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventLog implements Serializable {
    List<MapEvent> events = new ArrayList<>();

    public void add(int date, String s) {
        events.add(new MapEvent(date, s));
    }
}
