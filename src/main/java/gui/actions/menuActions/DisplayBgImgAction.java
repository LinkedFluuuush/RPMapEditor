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
package gui.actions.menuActions;

import gui.BasePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayBgImgAction implements ActionListener {
    private final BasePanel basePanel;
    private final JMenuItem toggleItem;

    public DisplayBgImgAction(BasePanel basePanel, JMenuItem toggleItem) {
        this.basePanel = basePanel;
        this.toggleItem = toggleItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isActive = this.basePanel.getMapPanel().isDrawImage();
        if (isActive){
            this.basePanel.getMapPanel().setDrawImage(false);
            this.toggleItem.setText(this.toggleItem.getText().substring(0, this.toggleItem.getText().length() - 2));
        } else {
            this.basePanel.getMapPanel().setDrawImage(true);
            this.toggleItem.setText(this.toggleItem.getText()+ " \u2713");
        }

        this.basePanel.getMapPanel().repaint();
    }
}
