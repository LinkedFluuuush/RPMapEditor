package gui.actions;

import core.Tile;
import core.util.Pair;
import gui.BasePanel;
import gui.MainFrame;
import gui.MapPanel;
import gui.util.MapAction;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MapMouseListener implements MouseListener, MouseMotionListener {
    private final MapPanel mapPanel;

    private final List<Pair<Integer>> positionList;

    public MapMouseListener(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        positionList = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.positionList.clear();
        this.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x, y;

        x = (int) Math.floor(e.getX() / 30);
        y = (int) Math.floor(e.getY() / 30);

        BasePanel basePanel = (BasePanel) mapPanel.getParent();

        if(!positionList.contains(new Pair<>(x, y))) {
            Tile tile = this.mapPanel.getTileAt(x, y);
            basePanel.clearRedoAction();
            basePanel.addUndoAction(new MapAction(tile, x, y));

            positionList.add(new Pair<>(x, y));
            this.mapPanel.addTileAt(x, y);
        }

        if(basePanel.isSaved()) {
            basePanel.setSaved(false);
            Container ctr = basePanel.getParent();
            while (ctr.getClass() != MainFrame.class) {
                ctr = ctr.getParent();
            }
            MainFrame frame = (MainFrame) ctr;

            frame.setTitle(basePanel.getSavingFile().getName().substring(0, basePanel.getSavingFile().getName().lastIndexOf('.')) + " (*) - RP Map Editor");
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
