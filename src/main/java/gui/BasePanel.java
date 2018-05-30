package gui;

import javax.swing.*;

public class BasePanel extends JLayeredPane {
    private ToolPanel toolPanel;
    private MapPanel mapPanel;

    public BasePanel() {
        super();
        this.setLayout(null);
        this.toolPanel = new ToolPanel();
        this.toolPanel.setBounds(20, 20, 120, 260);

        this.mapPanel = new MapPanel();


        this.add(mapPanel, new Integer(0));
        this.add(toolPanel, new Integer(1));

        this.mapPanel.repaint();
    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
}
