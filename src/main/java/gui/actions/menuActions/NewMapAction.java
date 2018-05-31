package gui.actions.menuActions;

import core.RPMap;
import gui.BasePanel;
import gui.MapPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMapAction implements ActionListener {
    private final BasePanel basePanel;

    public NewMapAction (BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapPanel mapPanel = basePanel.getMapPanel();
        mapPanel.setMap(new RPMap());
        mapPanel.repaint();
    }
}
