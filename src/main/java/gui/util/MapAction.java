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
