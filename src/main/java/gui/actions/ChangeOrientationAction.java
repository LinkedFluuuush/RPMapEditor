package gui.actions;

import core.Tile;
import gui.ToolPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeOrientationAction implements ActionListener {
    private final ToolPanel toolPanel;
    private final JButton triggeringButton;
    public ChangeOrientationAction(JButton triggeringButton, ToolPanel toolPanel) {
        super();
        this.triggeringButton = triggeringButton;
        this.toolPanel = toolPanel;
    }

    public void actionPerformed(ActionEvent e) {
        switch (this.toolPanel.getToolOrientation()){
            case TOP:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.RIGHT);
                this.triggeringButton.setText("RIGHT");
                break;
            case RIGHT:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.BOTTOM);
                this.triggeringButton.setText("DOWN");
                break;
            case BOTTOM:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.LEFT);
                this.triggeringButton.setText("LEFT");
                break;
            default:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.TOP);
                this.triggeringButton.setText("UP");
                break;
        }
    }
}
