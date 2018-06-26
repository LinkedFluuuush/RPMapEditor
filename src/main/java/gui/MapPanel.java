package gui;

import core.RPMap;
import core.Tile;
import core.util.Pair;
import gui.actions.MapMouseListener;
import gui.painters.realPainter.DayLightMinimalistPainter;
import gui.painters.MapPainter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;

import static core.Tile.TileOrientation.TOP;
import static core.Tile.TileType.*;

public class MapPanel extends JPanel {
    private RPMap map;
    private MapPainter painter;

    private int offsetX, offsetY;

    public enum Corner {
        TOPLEFT,
        TOPRIGHT,
        BOTTOMLEFT,
        BOTTOMRIGHT
    }

    MapPanel(Class painter){
        this.map = new RPMap();

        try {
            @SuppressWarnings("unchecked") Constructor<?> ctor = painter.getConstructor(RPMap.class);
            this.painter = (MapPainter) ctor.newInstance(new Object[] { this.map });
        } catch (Exception e) {
            e.printStackTrace();
            this.painter = new DayLightMinimalistPainter(this.map);
        }

        this.setBounds(0,0, 1,1);

        this.setOffsetX(0);
        this.setOffsetY(0);

        MapMouseListener mapMouseListener = new MapMouseListener(this);

        this.addMouseListener(mapMouseListener);
        this.addMouseMotionListener(mapMouseListener);
    }

    public RPMap getMap() {
        return map;
    }

    public void setMap(RPMap map) {
        this.map = map;
        this.painter.setMap(map);
    }

    public MapPainter getPainter() {
        return painter;
    }

    @SuppressWarnings("unused")
    public void setPainter(MapPainter painter) {
        this.painter = painter;
    }

    @Override
    public void paintComponent(Graphics g){
        this.setBounds(0,0, this.getParent().getWidth(), this.getParent().getHeight());
        super.paintComponent(g);

        Color original = g.getColor();

        this.painter.paintMap(map, this.getWidth(), this.getHeight(), this.getOffsetX(), this.getOffsetY(), true, g);

        g.setColor(original);
    }

    public Tile getTileAt(int i, int j){
        return this.getMap().getMapTiles().get(new Pair<>(i, j));
    }

    public void addTileAt(int x, int y, boolean adding){
        ToolPanel toolPanel = ((BasePanel)this.getParent()).getToolPanel();

        Tile.TileType addingType = toolPanel.getSelectedTool();
        Tile.TileOrientation addingOrientation = toolPanel.getToolOrientation();

        Tile existingTile = this.getTileAt(x, y);
        boolean isRoom = existingTile != null && existingTile.isRoom();
        Tile.TileType existingType = existingTile != null ? existingTile.getType() : EMPTY;
        Tile.TileOrientation existingOrientation = existingTile != null ? existingTile.getOrientation() : TOP;

        Tile newTile;

        if(addingType.equals(EMPTY)){
            newTile = new Tile(existingType, existingOrientation, adding);
        } else {
            if (existingType.equals(addingType) && !adding) {
                newTile = new Tile(EMPTY, addingOrientation, isRoom);
            } else if (adding) {
                newTile = new Tile(addingType, addingOrientation, isRoom);
            } else {
                newTile = new Tile(existingType, existingOrientation, isRoom);
            }
        }

        this.addTileAt(newTile, x, y);
    }

    public void addTileAt(Tile tile, int x, int y) {
        this.getMap().setTile(x, y, tile);
        this.repaint();
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}
