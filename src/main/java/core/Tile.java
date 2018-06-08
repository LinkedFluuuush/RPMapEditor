package core;

import java.util.Objects;

public class Tile {
    private final TileType type;
    private final TileOrientation orientation;
    private final boolean room;

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

    public Tile(TileType type, TileOrientation orientation, boolean room) {
        this.type = type;
        this.orientation = orientation;
        this.room = room;
    }

    public Tile(){
        this.type = TileType.EMPTY;
        this.orientation = TileOrientation.TOP;
        this.room = false;
    }

    public TileType getType() {
        return type;
    }

    public TileOrientation getOrientation() {
        return orientation;
    }

    public boolean isRoom() {
        return room;
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
