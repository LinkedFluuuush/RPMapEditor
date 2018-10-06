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

import core.Tile;
import gui.ToolPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeOrientationAction implements ActionListener {
    private final ToolPanel toolPanel;
    private final JButton triggeringButton;
    public ChangeOrientationAction(JButton triggeringButton, ToolPanel toolPanel) {
        super();
        this.triggeringButton = triggeringButton;
        this.toolPanel = toolPanel;
    }

    public void actionPerformed(ActionEvent e) {
        switch (this.toolPanel.getToolOrientation()){
            case TOP:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.RIGHT);
                this.triggeringButton.setText("RIGHT");
                break;
            case RIGHT:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.BOTTOM);
                this.triggeringButton.setText("DOWN");
                break;
            case BOTTOM:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.LEFT);
                this.triggeringButton.setText("LEFT");
                break;
            default:
                this.toolPanel.setToolOrientation(Tile.TileOrientation.TOP);
                this.triggeringButton.setText("UP");
                break;
        }
    }
}
