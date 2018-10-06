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
package gui.painters;

import core.RPMap;
import core.Tile;

import java.awt.*;

@SuppressWarnings("unused")
public abstract class MapPainter {
    private RPMap map;
    protected boolean workingMode;

    protected MapPainter(RPMap map){
        this.map = map;
    }

    protected RPMap getMap(){
        return this.map;
    }

    public void setMap(RPMap map){
        this.map = map;
    }

    public abstract void drawStairs(int i, int j, Tile.TileOrientation orientation, Graphics g);

    public abstract void drawDoor(int i, int j, Tile.TileOrientation orientation, Graphics g);

    public abstract void drawWindow(int i, int j, Tile.TileOrientation orientation, Graphics g);

    public abstract void drawTree(int i, int j, Graphics g);

    public abstract void drawBigDirt(int i, int j, Graphics g);

    public abstract void drawSmallDirt(int i, int j, Graphics g);

    public abstract void drawWater(int i, int j, Graphics g);

    public abstract void drawBlur(int i, int j, Graphics g);

    public abstract void drawOutsideTile(int i, int j, Graphics g);

    public abstract void paintMap(RPMap map, int width, int height, Graphics g);

    public abstract void paintMap(RPMap map, int width, int height, int offsetX, int offsetY, Graphics g);

    public abstract void paintMap(RPMap map, int width, int height, int offsetX, int offsetY, boolean drawBackground, Graphics g);

    public void setWorkingMode(boolean workingMode){
        this.workingMode = workingMode;
    }

    public static String getPainterName(){
        return "Abstract MapPainter";
    }
}
