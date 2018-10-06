/*
   Copyright 2018 Jean-Baptiste Louvet

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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
