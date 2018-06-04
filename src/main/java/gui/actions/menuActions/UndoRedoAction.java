package gui.actions.menuActions;

import gui.BasePanel;
import gui.util.MapAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoRedoAction implements ActionListener {
    private BasePanel basePanel;
    private boolean undo;

    public UndoRedoAction(BasePanel basePanel, boolean undo){
        this.basePanel = basePanel;
        this.undo = undo;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MapAction action;
        if(this.undo){
            action = this.basePanel.getLastUndoAction();
            if(action != null) {
                this.basePanel.addRedoAction(
                        new MapAction(this.basePanel.getMapPanel().getTileAt(action.getX(), action.getY()),
                                action.getX(), action.getY())
                );
            }
        } else {
            action = this.basePanel.getLastRedoAction();
            if(action != null) {
                this.basePanel.addUndoAction(
                        new MapAction(this.basePanel.getMapPanel().getTileAt(action.getX(), action.getY()),
                                action.getX(), action.getY())
                );
            }
        }
        if(action != null) {
            this.basePanel.getMapPanel().addTileAt(action.getTile(), action.getX(), action.getY());
            this.basePanel.getMapPanel().repaint();
        }
    }
}
