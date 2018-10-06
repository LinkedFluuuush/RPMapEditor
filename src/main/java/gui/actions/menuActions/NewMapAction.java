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
