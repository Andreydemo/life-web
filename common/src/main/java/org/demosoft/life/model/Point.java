package org.demosoft.life.model;

import lombok.Value;

@Value
public class Point {

    int x;
    int y;

    public boolean isXAfter(int x) {
        return x >= this.x;
    }

    public boolean isYAfter(int y) {
        return y >= this.y;
    }

    public boolean isXBefore(int x) {
        return x <= this.x;
    }

    public boolean isYBefore(int y) {
        return y <= this.y;
    }

    public boolean isAfterPoint(int x , int y) {
        return isXAfter(x) && isYAfter(y);
    }

    public boolean isAfterPoint(Point point) {
        return isXAfter(point.getX()) && isYAfter(point.getY());
    }
    public boolean isBeforePoint(int x , int y) {
        return isXBefore(x) && isYBefore(y);
    }

    public boolean isBeforePoint(Point point) {
        return isXBefore(point.getX()) && isYBefore(point.getY());
    }
}
