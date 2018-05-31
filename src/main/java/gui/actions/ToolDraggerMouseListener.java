package gui.actions;

import gui.BasePanel;
import gui.ToolPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ToolDraggerMouseListener implements MouseMotionListener {
    private final ToolPanel toolPanel;

    public ToolDraggerMouseListener(ToolPanel toolPanel){
        this.toolPanel = toolPanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Container container = toolPanel.getParent();

        while(container.getClass() != BasePanel.class){
            container = container.getParent();
        }

        Point realMousePoint = container.getMousePosition();
        if(realMousePoint != null) {
            int realMouseX = realMousePoint.x;
            int realMouseY = realMousePoint.y;

            toolPanel.setBounds(realMouseX - (toolPanel.getWidth() / 2), realMouseY - 10,
                    toolPanel.getWidth(), toolPanel.getHeight());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
