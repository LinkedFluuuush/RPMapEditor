package gui.util;

import core.Tile;

import java.util.Objects;

public class MapAction {
    private final Tile tile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapAction mapAction = (MapAction) o;
        return getX() == mapAction.getX() &&
                getY() == mapAction.getY() &&
                Objects.equals(getTile(), mapAction.getTile());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTile(), getX(), getY());
    }

    private final int x, y;

    public MapAction(Tile tile, int x, int y) {
        this.tile = tile;
        this.x = x;
        this.y = y;
    }

    public Tile getTile() {
        return tile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "MapAction{" +
                "tile=" + tile +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
