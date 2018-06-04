package core;

import java.util.Objects;

public class Tile {
    private final TileType type;
    private final TileOrientation orientation;
    private final boolean isRoom;

    public enum TileType{
        EMPTY,
        DOOR,
        WINDOW,
        SMALLDIRT,
        BIGDIRT,
        TREE,
        WATER,
        BLUR,
        STAIR
    }
    public enum TileOrientation{
        TOP,
        LEFT,
        BOTTOM,
        RIGHT
    }

    public Tile(TileType type, TileOrientation orientation, boolean isRoom) {
        this.type = type;
        this.orientation = orientation;
        this.isRoom = isRoom;
    }

    public TileType getType() {
        return type;
    }

    public TileOrientation getOrientation() {
        return orientation;
    }

    public boolean isRoom() {
        return isRoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return isRoom() == tile.isRoom() &&
                getType() == tile.getType() &&
                getOrientation() == tile.getOrientation();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getType(), getOrientation(), isRoom());
    }
}
