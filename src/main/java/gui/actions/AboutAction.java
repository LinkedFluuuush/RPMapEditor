package gui.actions;

import gui.BasePanel;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAction implements ActionListener {
    private final BasePanel basePanel;

    public AboutAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Container ctr = this.basePanel.getParent();

        while(ctr.getClass() != MainFrame.class){
            ctr = ctr.getParent();
        }

        MainFrame frame = (MainFrame) ctr;

        JOptionPane.showMessageDialog(frame,"RPMapEditor - Copyright LinkedFluuuush 2018");
    }
}
