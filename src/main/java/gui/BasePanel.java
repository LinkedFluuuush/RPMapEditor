package gui;

import gui.painters.DayLightMinimalistPainter;

import javax.swing.*;
import java.io.File;

public class BasePanel extends JLayeredPane {
    private ToolPanel toolPanel;
    private MapPanel mapPanel;

    private File savingFile;
    private boolean saved;

    public File getSavingFile() {
        return savingFile;
    }

    public void setSavingFile(File savingFile) {
        this.savingFile = savingFile;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public BasePanel() {
        super();
        this.setLayout(null);
        this.toolPanel = new ToolPanel();
        this.toolPanel.setBounds(20, 20, 120, 260);

        this.mapPanel = new MapPanel(DayLightMinimalistPainter.class);


        this.add(mapPanel, new Integer(0));
        this.add(toolPanel, new Integer(1));

        this.mapPanel.repaint();

        this.savingFile = null;
        this.saved = false;


    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
}
