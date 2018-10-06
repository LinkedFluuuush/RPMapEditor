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
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitAction implements ActionListener {
    private final BasePanel basePanel;

    public QuitAction (BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container ctr = this.basePanel.getParent();

        while(ctr.getClass() != MainFrame.class){
            ctr = ctr.getParent();
        }

        MainFrame frame = (MainFrame) ctr;

        int n = JOptionPane.YES_OPTION;

        if(!this.basePanel.isSaved()){
            n = JOptionPane.showConfirmDialog(
                    frame,
                    "Your map isn't saved. Do you want to save it ?",
                    "Save file ?",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                SaveMapAction saveMapAction = new SaveMapAction(this.basePanel, false);
                saveMapAction.actionPerformed(null);
            }
        }

        if(n != JOptionPane.CANCEL_OPTION) {
            frame.dispose();
            System.exit(0);
        }
    }
}
