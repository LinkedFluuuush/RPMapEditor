package gui.actions;

import gui.ToolPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMoveMapAction implements ActionListener {
    private ToolPanel toolPanel;
    private JButton triggeringButton;

    public SelectMoveMapAction(ToolPanel toolPanel, JButton triggeringButton){
        this.toolPanel = toolPanel;
        this.triggeringButton = triggeringButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.toolPanel.setSelectedTool(null);
        this.toolPanel.setMovePanel(true);

        for(Component c : this.toolPanel.getComponents()){
            if(!c.isEnabled()){
                c.setEnabled(true);
                break;
            }
        }

        this.triggeringButton.setEnabled(false);
    }
}
