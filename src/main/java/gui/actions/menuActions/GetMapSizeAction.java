package gui.actions.menuActions;

import core.RPMap;
import core.util.Pair;
import gui.BasePanel;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetMapSizeAction implements ActionListener {
    private final BasePanel basePanel;

    public GetMapSizeAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int sizeX = 0, sizeY = 0;
        RPMap map = this.basePanel.getMapPanel().getMap();
        map.cleanMap();
        Pair<Integer> mapSize = map.getMapSize();

        Container ctr = this.basePanel.getParent();

        while(ctr.getClass() != MainFrame.class){
            ctr = ctr.getParent();
        }

        MainFrame frame = (MainFrame) ctr;

        JOptionPane.showMessageDialog(frame,"Map size : " + (mapSize.getP1() + 2) + "x" + (mapSize.getP2() + 2));
    }
}
