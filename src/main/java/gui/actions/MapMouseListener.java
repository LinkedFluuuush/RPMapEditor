package gui.actions;

import core.Tile;
import core.util.Pair;
import gui.BasePanel;
import gui.MainFrame;
import gui.MapPanel;
import gui.ToolPanel;
import gui.util.MapAction;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MapMouseListener implements MouseListener, MouseMotionListener {
    private final MapPanel mapPanel;

    private int originX, originY;
    private int currentButton;
    private boolean adding;

    public MapMouseListener(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.currentButton = e.getButton();

        ToolPanel toolPanel = ((BasePanel)this.mapPanel.getParent()).getToolPanel();
        Tile.TileType tileType = toolPanel.getSelectedTool();

        if(this.currentButton == MouseEvent.BUTTON1 && !toolPanel.isMovePanel()) {
            int x = (int) Math.floor(e.getX() / 30) + this.mapPanel.getOffsetX();
            int y = (int) Math.floor(e.getY() / 30) + this.mapPanel.getOffsetY();
            Tile tile = this.mapPanel.getTileAt(x, y);

            adding = tile == null || (tileType.equals(Tile.TileType.EMPTY) && !tile.isRoom()) || !tileType.equals(tile.getType());
        } else {
            this.originX = (int) Math.floor(e.getX() / 30);
            this.originY = (int) Math.floor(e.getY() / 30);
        }

        this.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.currentButton = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ToolPanel toolPanel = ((BasePanel)this.mapPanel.getParent()).getToolPanel();
        if (this.currentButton == MouseEvent.BUTTON1 && !toolPanel.isMovePanel()) {
            int x, y;

            x = (int) Math.floor(e.getX() / 30) + this.mapPanel.getOffsetX();
            y = (int) Math.floor(e.getY() / 30) + this.mapPanel.getOffsetY();

            BasePanel basePanel = (BasePanel) mapPanel.getParent();

            Tile tile = this.mapPanel.getTileAt(x, y);
            basePanel.clearRedoAction();
            basePanel.addUndoAction(new MapAction(tile, x, y));

            this.mapPanel.addTileAt(x, y, adding);

            if (basePanel.isSaved()) {
                basePanel.setSaved(false);
                Container ctr = basePanel.getParent();
                while (ctr.getClass() != MainFrame.class) {
                    ctr = ctr.getParent();
                }
                MainFrame frame = (MainFrame) ctr;

                frame.setTitle(basePanel.getSavingFile().getName().substring(0, basePanel.getSavingFile().getName().lastIndexOf('.')) + " (*) - RP Map Editor");
            }
        } else {
            int x, y;

            x = (int) Math.floor(e.getX() / 30);
            y = (int) Math.floor(e.getY() / 30);

            if(x != originX || y != originY) {
                this.mapPanel.setOffsetX(this.mapPanel.getOffsetX() + (originX - x));
                this.mapPanel.setOffsetY(this.mapPanel.getOffsetY() + (originY - y));
                this.mapPanel.repaint();

                originX = x;
                originY = y;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
