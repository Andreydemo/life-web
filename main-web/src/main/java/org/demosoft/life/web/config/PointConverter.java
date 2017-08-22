package org.demosoft.life.web.config;

import org.demosoft.life.model.Point;
import org.springframework.core.convert.converter.Converter;


public class PointConverter implements Converter<String, Point> {

    @Override
    public Point convert(String source) {
        String[] split = source.split(",");
        return new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }
}
