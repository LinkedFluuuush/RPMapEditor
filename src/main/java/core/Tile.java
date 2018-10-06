/*
   Copyright 2018 Jean-Baptiste Louvet

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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
