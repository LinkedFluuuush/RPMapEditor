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
