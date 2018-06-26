package gui.actions;

import core.Tile;
import gui.ToolPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolSelectionAction implements ActionListener {
    private final Tile.TileType typeSelection;
    private final ToolPanel toolPanel;
    private final JButton triggeringButton;

    public ToolSelectionAction(JButton triggeringButton, ToolPanel toolPanel, Tile.TileType typeSelection) {
        super();
        this.triggeringButton = triggeringButton;
        this.toolPanel = toolPanel;
        this.typeSelection = typeSelection;
    }

    public void actionPerformed(ActionEvent e) {
        toolPanel.setSelectedTool(this.typeSelection);
        toolPanel.setMovePanel(false);

        for(Component c : this.toolPanel.getComponents()){
            if(!c.isEnabled()){
                c.setEnabled(true);
                break;
            }
        }

        this.triggeringButton.setEnabled(false);
    }
}
