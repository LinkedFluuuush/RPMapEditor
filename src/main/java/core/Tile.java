package core;

public class Tile {
    private TileType type;
    private TileOrientation orientation;
    private boolean isRoom;

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

    public Tile() {
        this (TileType.EMPTY, TileOrientation.TOP, false);
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(TileOrientation orientation) {
        this.orientation = orientation;
    }

    public boolean isRoom() {
        return isRoom;
    }

    public void setRoom(boolean room) {
        isRoom = room;
    }
}
