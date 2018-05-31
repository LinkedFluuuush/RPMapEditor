package gui.actions.menuActions;

import gui.BasePanel;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitAction implements ActionListener {
    private final BasePanel basePanel;

    public QuitAction (BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container ctr = this.basePanel.getParent();

        while(ctr.getClass() != MainFrame.class){
            ctr = ctr.getParent();
        }

        MainFrame frame = (MainFrame) ctr;

        if(!this.basePanel.isSaved()){
            int n = JOptionPane.showConfirmDialog(
                    frame,
                    "Your map isn't saved. Do you want to save it ?",
                    "Save file ?",
                    JOptionPane.YES_NO_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                SaveMapAction saveMapAction = new SaveMapAction(this.basePanel, false);
                saveMapAction.actionPerformed(null);
            }
        }

        frame.dispose();
        System.exit(0);
    }
}
