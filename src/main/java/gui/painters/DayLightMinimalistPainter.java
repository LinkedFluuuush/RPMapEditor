package gui.painters;

import core.RPMap;
import core.Tile;
import core.util.Pair;
import gui.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static core.Tile.TileType.BIGDIRT;
import static core.Tile.TileType.SMALLDIRT;
import static core.Tile.TileType.TREE;

public class DayLightMinimalistPainter extends MapPainter {
    private final HashMap<Pair<Integer>, List<Pair<Pair<Integer>>>> treeMap;
    private final HashMap<Pair<Integer>, List<Pair<Pair<Integer>>>> bigDirtMap;
    private final HashMap<Pair<Integer>, List<Pair<Pair<Integer>>>> smallDirtMap;

    private int drawingOffsetX, drawingOffsetY;

    public DayLightMinimalistPainter(RPMap map){
        super(map);

        this.treeMap = new HashMap<>();
        this.bigDirtMap = new HashMap<>();
        this.smallDirtMap = new HashMap<>();
    }


    private Tile getTileAt(int i, int j){
        return this.getMap().getMapTiles().get(new Pair<>(i, j));
    }

    private boolean isFullCorner(int i, int j, MapPanel.Corner corner){
        Tile baseTile = this.getTileAt(i, j);

        if(baseTile != null) {
            Tile.TileType type = baseTile.getType();

            switch(corner){
                case TOPLEFT:
                    if(getTileAt(i-1, j) != null &&
                            getTileAt(i-1, j-1) != null &&
                            getTileAt(i, j-1) != null){
                        return (getTileAt(i-1, j).getType() == type) &&
                                (getTileAt(i-1, j-1).getType() == type) &&
                                (getTileAt(i, j-1).getType() == type);
                    }
                    break;
                case TOPRIGHT:
                    if(getTileAt(i+1, j) != null &&
                            getTileAt(i+1, j-1) != null &&
                            getTileAt(i, j-1) != null){
                        return (getTileAt(i+1, j).getType() == type) &&
                                (getTileAt(i+1, j-1).getType() == type) &&
                                (getTileAt(i, j-1).getType() == type);
                    }
                    break;
                case BOTTOMLEFT:
                    if(getTileAt(i-1, j) != null &&
                            getTileAt(i-1, j+1) != null &&
                            getTileAt(i, j+1) != null){
                        return (getTileAt(i-1, j).getType() == type) &&
                                (getTileAt(i-1, j+1).getType() == type) &&
                                (getTileAt(i, j+1).getType() == type);
                    }
                    break;
                case BOTTOMRIGHT:
                    if(getTileAt(i+1, j) != null &&
                            getTileAt(i+1, j+1) != null &&
                            getTileAt(i, j+1) != null){
                        return (getTileAt(i+1, j).getType() == type) &&
                                (getTileAt(i+1, j+1).getType() == type) &&
                                (getTileAt(i, j+1).getType() == type);
                    }
                    break;
                default:
                    break;
            }
        }

        return false;
    }

    public void drawStairs(int i, int j, Tile.TileOrientation orientation, Graphics g){
        Color original = g.getColor();

        switch (orientation){
            case BOTTOM:
                g.setColor(Color.GRAY);
                g.fillRect((i*30), (j*30) + 2,  30, 3);
                g.fillRect((i*30), (j*30) + 8, 30, 3);
                g.fillRect((i*30), (j*30) + 14, 30, 3);
                g.fillRect((i*30), (j*30) + 20, 30, 3);
                g.fillRect((i*30), (j*30) + 26, 30, 3);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect((i*30), (j*30) + 5, 30, 3);
                g.fillRect((i*30), (j*30) + 11, 30, 3);
                g.fillRect((i*30), (j*30) + 17, 30, 3);
                g.fillRect((i*30), (j*30) + 23, 30, 3);
                g.fillRect((i*30), (j*30) + 29, 30, 3);
                break;
            case LEFT:
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect((i*30) - 2, (j*30), 3, 30);
                g.fillRect((i*30) + 4, (j*30), 3, 30);
                g.fillRect((i*30)+ 10, (j*30), 3, 30);
                g.fillRect((i*30) + 16, (j*30), 3, 30);
                g.fillRect((i*30) + 22, (j*30), 3, 30);

                g.setColor(Color.GRAY);
                g.fillRect((i*30) + 1, (j*30), 3, 30);
                g.fillRect((i*30) + 7, (j*30), 3, 30);
                g.fillRect((i*30) + 13, (j*30), 3, 30);
                g.fillRect((i*30) + 19, (j*30), 3, 30);
                g.fillRect((i*30) + 25, (j*30), 3, 30);
                break;
            case RIGHT:
                g.setColor(Color.GRAY);
                g.fillRect((i*30)+ 2, (j*30),  3, 30);
                g.fillRect((i*30)+ 8, (j*30),  3, 30);
                g.fillRect((i*30)+ 14, (j*30), 3, 30);
                g.fillRect((i*30)+ 20, (j*30), 3, 30);
                g.fillRect((i*30)+ 26, (j*30), 3, 30);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect((i*30) + 5, (j*30),  3, 30);
                g.fillRect((i*30) + 11, (j*30), 3, 30);
                g.fillRect((i*30) + 17, (j*30), 3, 30);
                g.fillRect((i*30) + 23, (j*30), 3, 30);
                g.fillRect((i*30) + 29, (j*30), 3, 30);
                break;
            case TOP:
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect((i*30), (j*30) - 2, 30, 3);
                g.fillRect((i*30), (j*30) + 4, 30, 3);
                g.fillRect((i*30), (j*30) + 10, 30, 3);
                g.fillRect((i*30), (j*30) + 16, 30, 3);
                g.fillRect((i*30), (j*30) + 22, 30, 3);

                g.setColor(Color.GRAY);
                g.fillRect((i*30), (j*30) + 1, 30, 3);
                g.fillRect((i*30), (j*30) + 7, 30, 3);
                g.fillRect((i*30), (j*30) + 13, 30, 3);
                g.fillRect((i*30), (j*30) + 19, 30, 3);
                g.fillRect((i*30), (j*30) + 25, 30, 3);
            default:
                break;
        }

        g.setColor(original);
    }

    public void drawDoor(int i, int j, Tile.TileOrientation orientation, Graphics g){
        Color original = g.getColor();
        g.setColor(new Color(165, 42, 42));

        switch(orientation){
            case TOP:
            case BOTTOM:
                g.fillRect((i*30)+10, (j*30)-2, 10, 34);
                break;
            case RIGHT:
            case LEFT:
            default:
                g.fillRect((i*30)-2, (j*30)+10, 34, 10);
                break;
        }

        g.setColor(original);
    }

    public void drawWindow(int i, int j, Tile.TileOrientation orientation, Graphics g){
        Color original = g.getColor();
        g.setColor(Color.CYAN);

        switch(orientation){
            case TOP:
            case BOTTOM:
                g.fillRect((i*30)+10, (j*30)+2, 10, 26);
                break;
            case RIGHT:
            case LEFT:
            default:
                g.fillRect((i*30)+2, (j*30)+10, 26, 10);
                break;
        }

        g.setColor(original);
    }

    public void drawTree(int i, int j, Graphics g) {
        Color original = g.getColor();
        java.util.List<Pair<Pair<Integer>>> treeList = new ArrayList<>();
        Random r = new Random();
        g.setColor(Color.GREEN);

        if(treeMap.get(new Pair<>(i, j)) == null) {
            int nTrees = 5 + r.nextInt(10);

            for (int n = 0; n < nTrees; n++) {
                int treeSize = 10 + r.nextInt(16);
                int treeXOffset = r.nextInt(40 - treeSize) - 5;
                int treeYOffset = r.nextInt(40 - treeSize) - 5;

                treeList.add(new Pair<>(new Pair<>(treeXOffset, treeYOffset),
                        new Pair<>(treeSize, treeSize)));
            }

            treeMap.put(new Pair<>(i,j), treeList);
        } else {
            treeList = treeMap.get(new Pair<>(i,j));
        }


        for(Pair<Pair<Integer>> aTree : treeList){
            g.setColor(new Color(0, 150, 0));
            g.fillOval((i*30) + aTree.getP1().getP1(), (j*30) + aTree.getP1().getP2(),
                    aTree.getP2().getP1(), aTree.getP2().getP2());
        }

        for(Pair<Pair<Integer>> aTree : treeList){
            g.setColor(new Color(0,175,0));
            int x = (i*30) + aTree.getP1().getP1() + 2;
            int y = (j*30) + aTree.getP1().getP2() + 2;
            int size = aTree.getP2().getP1() - 4;

            g.fillOval(x, y, size, size);
        }

        for(Pair<Pair<Integer>> aTree : treeList){
            g.setColor(new Color(0,200,0));
            int x = (i*30) + aTree.getP1().getP1() + 6;
            int y = (j*30) + aTree.getP1().getP2() + 6;
            int size = aTree.getP2().getP1() - 12;

            g.fillOval(x, y, size, size);
        }

        g.setColor(original);
    }

    public void drawBigDirt(int i, int j, Graphics g){
        Color original = g.getColor();
        java.util.List<Pair<Pair<Integer>>> bigDirtList = new ArrayList<>();
        Random r = new Random();

        if(bigDirtMap.get(new Pair<>(i, j)) == null) {
            int nDirt = 5 + r.nextInt(10);

            for (int n = 0; n < nDirt; n++) {
                int dirtSize = 4 + r.nextInt(16);
                int dirtXOffset = r.nextInt(40 - dirtSize) - 5;
                int dirtYOffset = r.nextInt(40 - dirtSize) - 5;

                bigDirtList.add(new Pair<>(new Pair<>(dirtXOffset, dirtYOffset),
                        new Pair<>(dirtSize, dirtSize)));
            }
            bigDirtMap.put(new Pair<>(i, j), bigDirtList);
        } else {
            bigDirtList = bigDirtMap.get(new Pair<>(i, j));
        }

        for(Pair<Pair<Integer>> aBigDirt : bigDirtList){
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval((i * 30) + aBigDirt.getP1().getP1(), (j * 30) + aBigDirt.getP1().getP2(),
                    aBigDirt.getP2().getP1(), aBigDirt.getP2().getP2());
            g.setColor(Color.GRAY);
            g.drawOval((i * 30) + aBigDirt.getP1().getP1(), (j * 30) + aBigDirt.getP1().getP2(),
                    aBigDirt.getP2().getP1(), aBigDirt.getP2().getP2());
        }

        g.setColor(original);
    }

    public void drawSmallDirt(int i, int j, Graphics g){
        Color original = g.getColor();
        List<Pair<Pair<Integer>>> smallDirtList = new ArrayList<>();
        Graphics2D g2d = (Graphics2D) g;
        Random r = new Random();

        if(smallDirtMap.get(new Pair<>(i, j)) == null) {
            int nDirt = 5 + r.nextInt(10);

            for (int n = 0; n < nDirt; n++) {
                int dirtSize = 1 + r.nextInt(10);
                int dirtXOffset = r.nextInt(30 - dirtSize);
                int dirtYOffset = r.nextInt(30 - dirtSize);

                smallDirtList.add(new Pair<>(new Pair<>(dirtXOffset, dirtYOffset),
                        new Pair<>(dirtSize, dirtSize)));
            }

            smallDirtMap.put(new Pair<>(i, j), smallDirtList);
        } else {
            smallDirtList = smallDirtMap.get(new Pair<>(i, j));
        }

        for (Pair<Pair<Integer>> aSmallDirt : smallDirtList){
            g2d.setPaint(new Color(100, 100, 100, 100));
            g2d.fillOval((i * 30) + aSmallDirt.getP1().getP1(), (j * 30) + aSmallDirt.getP1().getP2(),
                    aSmallDirt.getP2().getP1(), aSmallDirt.getP2().getP2());
        }

        g.setColor(original);
    }

    public void drawWater(int i, int j, Graphics g) {
        Color original = g.getColor();

        g.setColor(Color.BLUE);
        g.fillOval((i*30)+2, (j*30)+2, 26, 26);

        Tile tmpTile = this.getTileAt(i + this.drawingOffsetX, j + this.drawingOffsetY-1);
        if(tmpTile != null && tmpTile.getType() == Tile.TileType.WATER){
            g.fillRect((i*30)+2, (j*30), 26, 15);
        }

        tmpTile = this.getTileAt(i + this.drawingOffsetX+1, j + this.drawingOffsetY);
        if(tmpTile != null && tmpTile.getType() == Tile.TileType.WATER){
            g.fillRect((i*30)+15, (j*30)+2, 15, 26);
        }

        tmpTile = this.getTileAt(i + this.drawingOffsetX, j + this.drawingOffsetY+1);
        if(tmpTile != null && tmpTile.getType() == Tile.TileType.WATER){
            g.fillRect((i*30)+2, (j*30)+15, 26, 15);
        }

        tmpTile = this.getTileAt(i + this.drawingOffsetX-1, j + this.drawingOffsetY);
        if(tmpTile != null && tmpTile.getType() == Tile.TileType.WATER){
            g.fillRect((i*30), (j*30)+2, 15, 26);
        }

        if(isFullCorner(i + this.drawingOffsetX, j + this.drawingOffsetY, MapPanel.Corner.TOPLEFT)){
            g.fillRect((i*30), (j*30), 5, 5);
        }

        if(isFullCorner(i + this.drawingOffsetX, j + this.drawingOffsetY, MapPanel.Corner.TOPRIGHT)){
            g.fillRect(((i+1)*30)-5, (j*30), 5, 5);
        }

        if(isFullCorner(i + this.drawingOffsetX, j + this.drawingOffsetY, MapPanel.Corner.BOTTOMLEFT)){
            g.fillRect((i*30), ((j+1)*30)-5, 5, 5);
        }

        if(isFullCorner(i + this.drawingOffsetX, j + this.drawingOffsetY, MapPanel.Corner.BOTTOMRIGHT)){
            g.fillRect(((i+1)*30)-5, ((j+1)*30)-5, 5, 5);
        }

        g.setColor(original);
    }

    public void drawOutsideTile(int i, int j, Graphics g) {
        Color original = g.getColor();
        Graphics2D g2d = (Graphics2D) g;

        boolean drawUp = !(this.getTileAt(      i + this.drawingOffsetX,    j + this.drawingOffsetY-1) != null &&   this.getTileAt(i + this.drawingOffsetX,     j + this.drawingOffsetY-1).isRoom());
        boolean drawLeft = !(this.getTileAt(    i + this.drawingOffsetX-1,  j + this.drawingOffsetY) != null &&     this.getTileAt(i + this.drawingOffsetX-1,   j + this.drawingOffsetY).isRoom());
        boolean drawRight = !(this.getTileAt(   i + this.drawingOffsetX+1,  j + this.drawingOffsetY) != null &&     this.getTileAt(i + this.drawingOffsetX+1,   j + this.drawingOffsetY).isRoom());
        boolean drawDown = !(this.getTileAt(    i + this.drawingOffsetX,    j + this.drawingOffsetY+1) != null &&   this.getTileAt(i + this.drawingOffsetX,     j + this.drawingOffsetY+1).isRoom());

        boolean drawUpLeft = !(this.getTileAt(      i + this.drawingOffsetX-1, j + this.drawingOffsetY-1) != null && this.getTileAt(i + this.drawingOffsetX-1, j + this.drawingOffsetY-1).isRoom());
        boolean drawUpRight = !(this.getTileAt(     i + this.drawingOffsetX+1, j + this.drawingOffsetY-1) != null && this.getTileAt(i + this.drawingOffsetX+1, j + this.drawingOffsetY-1).isRoom());
        boolean drawDownLeft = !(this.getTileAt(    i + this.drawingOffsetX-1, j + this.drawingOffsetY+1) != null && this.getTileAt(i + this.drawingOffsetX-1, j + this.drawingOffsetY+1).isRoom());
        boolean drawDownRight = !(this.getTileAt(   i + this.drawingOffsetX+1, j + this.drawingOffsetY+1) != null && this.getTileAt(i + this.drawingOffsetX+1, j + this.drawingOffsetY+1).isRoom());

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine((i*30) + 10, (j*30) + 20, (i*30) + 20, (j*30) + 10);

        if(drawUp){
            g.drawLine((i*30) + 10, (j*30) + 5, (i*30) + 15, (j*30));
        } else {
            drawUpLeft = false;
            drawUpRight = false;

            g.setColor(Color.GRAY);
            g.drawLine((i*30) + 10, (j*30) + 10, (i*30) + 20, (j*30) + 10);
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawLeft){
            g.drawLine((i*30), (j*30) + 15, (i*30) + 5, (j*30) + 10);
        } else {
            drawUpLeft = false;
            drawDownLeft = false;

            g.setColor(Color.GRAY);
            g.drawLine((i*30) + 10, (j*30) + 10, (i*30) + 10, (j*30) + 20);
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawRight){
            g.drawLine((i*30) + 25, (j*30) + 20, (i*30) + 30, (j*30) + 15);
        } else {
            drawUpRight = false;
            drawDownRight = false;

            g.setColor(Color.GRAY);
            g.drawLine((i*30) + 20, (j*30) + 10, (i*30) + 20, (j*30) + 20);
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawDown){
            g.drawLine((i*30) + 15, (j*30) + 30, (i*30) + 20, (j*30) + 25);
        } else {
            drawDownLeft = false;
            drawDownRight = false;

            g2d.setColor(new Color(0, 0, 0, 75));
            g2d.fillRect((i*30) + 10, (j*30) + 20, 10, 5);

            g.setColor(Color.GRAY);
            g.drawLine((i*30) + 10, (j*30) + 20, (i*30) + 20, (j*30) + 20);
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawUpLeft){
            g.drawLine((i*30) + 5, (j*30) + 10, (i*30) + 10, (j*30) + 5);
        } else {
            g.setColor(Color.GRAY);
            if(drawUp){
                g.drawLine((i*30) + 10, (j*30), (i*30) + 10, (j*30) + 10);
            }

            if(drawLeft){
                g.drawLine((i*30), (j*30) + 10, (i*30) + 10, (j*30) + 10);
            }
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawUpRight){
            g.drawLine((i*30) + 20, (j*30) + 10, (i*30) + 30, (j*30));
        } else {
            g.setColor(Color.GRAY);
            if(drawUp){
                g.drawLine((i*30) + 20, (j*30), (i*30) + 20, (j*30) + 10);
            }

            if(drawRight){
                g.drawLine((i*30) + 20, (j*30) + 10, (i*30) + 30, (j*30) + 10);
            }
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawDownLeft){
            g.drawLine((i*30), (j*30) + 30, (i*30) +10, (j*30) + 20);
        } else {
            g.setColor(Color.GRAY);
            if(drawDown){
                g.drawLine((i*30) + 10, (j*30) + 20, (i*30) + 10, (j*30) + 30);
            }

            if(drawLeft){
                g2d.setColor(new Color(0, 0, 0, 75));
                g2d.fillRect((i*30), (j*30) + 20, 10, 5);
                g.setColor(Color.GRAY);
                g.drawLine((i*30), (j*30) + 20, (i*30) + 10, (j*30) + 20);
            }
            g.setColor(Color.LIGHT_GRAY);
        }

        if(drawDownRight){
            g.drawLine((i*30) + 20, (j*30) + 25, (i*30) + 25, (j*30) + 20);
        } else {
            g.setColor(Color.GRAY);
            if(drawDown){
                g.drawLine((i*30) + 20, (j*30) + 20, (i*30) + 20, (j*30) + 30);
            }

            if(drawRight){
                g2d.setColor(new Color(0, 0, 0, 75));
                g2d.fillRect((i*30) + 20, (j*30) + 20, 10, 5);
                g.setColor(Color.GRAY);
                g.drawLine((i*30) + 20, (j*30) + 20, (i*30) + 30, (j*30) + 20);
            }
            g.setColor(Color.LIGHT_GRAY);
        }

        g.setColor(original);
    }

    @Override
    public void paintMap(RPMap map, int width, int height, Graphics g) {
        this.paintMap(map, width, height, 0, 0, g);
    }


    @Override
    public void paintMap(RPMap map, int width, int height, int offsetX, int offsetY, Graphics g) {
        g.setColor(Color.LIGHT_GRAY);

        this.drawingOffsetX = offsetX;
        this.drawingOffsetY = offsetY;

        Tile tile;
        for(int i = 0; i < Math.ceil(width / 30); i++) {
            for (int j = 0; j < Math.ceil(height / 30); j++) {
                tile = this.getTileAt(i + this.drawingOffsetX, j + this.drawingOffsetY);

                // Drawing below void
                if (tile != null) {
                    switch (tile.getType()) {
                        case WATER:
                            this.drawWater(i, j, g);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        for(int i = 0; i < Math.ceil(width / 30); i++){
            for(int j = 0; j < Math.ceil(height / 30); j++) {
                tile = this.getTileAt(i + this.drawingOffsetX, j + this.drawingOffsetY);

                if (tile == null || !tile.isRoom()) {
                    this.drawOutsideTile(i, j, g);
                }
            }
        }

        for(int i = 0; i < Math.ceil(width / 30); i++){
            for(int j = 0; j < Math.ceil(height / 30); j++){
                tile = this.getTileAt(i + this.drawingOffsetX, j + this.drawingOffsetY);
                // Drawing above void
                if(tile != null){
                    switch(tile.getType()){
                        case BLUR:
                            g.setColor(Color.BLACK);
                            g.drawLine((i*30) + 5, (j*30) + 5, ((i+1)*30) - 5, ((j+1)*30) - 5);
                            g.drawLine(((i+1)*30) - 5, (j*30) + 5, (i*30) + 5, ((j+1)*30) - 5);
                            break;
                        case DOOR:
                            this.drawDoor(i, j, tile.getOrientation(), g);
                            break;
                        case TREE:
                            this.drawTree(i, j, g);
                            break;
                        case STAIR:
                            this.drawStairs(i, j, tile.getOrientation(), g);
                            break;
                        case WINDOW:
                            this.drawWindow(i, j, tile.getOrientation(), g);
                            break;
                        case BIGDIRT:
                            this.drawBigDirt(i, j, g);
                            break;
                        case SMALLDIRT:
                            this.drawSmallDirt(i, j, g);
                            break;
                        default:
                            break;
                    }

                    if(tile.getType() != TREE){
                        treeMap.remove(new Pair<>(i,j));
                    }

                    if(tile.getType() != SMALLDIRT){
                        smallDirtMap.remove(new Pair<>(i, j));
                    }

                    if(tile.getType() != BIGDIRT){
                        bigDirtMap.remove(new Pair<>(i, j));
                    }
                }
            }
        }

        g.setColor(Color.LIGHT_GRAY);

        //Dessin de la grille :
        for(int i = 0; i < width; i += 30){
            g.drawLine(i, 0, i, height);
        }

        for(int i = 0; i < height; i += 30){
            g.drawLine(0, i, width, i);
        }
    }

    @Override
    public void paintMap(RPMap map, int width, int height, int offsetX, int offsetY, boolean drawBackground, Graphics g){
        Color original = g.getColor();
        g.setColor(UIManager.getColor ( "Panel.background" ));
        g.fillRect(0, 0, width, height);
        g.setColor(original);
        this.paintMap(map, width, height, offsetX, offsetY, g);
    }

}
