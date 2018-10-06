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
import gui.util.MapAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoRedoAction implements ActionListener {
    private final BasePanel basePanel;
    private final boolean undo;

    public UndoRedoAction(BasePanel basePanel, boolean undo){
        this.basePanel = basePanel;
        this.undo = undo;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        MapAction action;
        if(this.undo){
            action = this.basePanel.getLastUndoAction();
            if(action != null) {
                this.basePanel.addRedoAction(
                        new MapAction(this.basePanel.getMapPanel().getTileAt(action.getX(), action.getY()),
                                action.getX(), action.getY())
                );
            }
        } else {
            action = this.basePanel.getLastRedoAction();
            if(action != null) {
                this.basePanel.addUndoAction(
                        new MapAction(this.basePanel.getMapPanel().getTileAt(action.getX(), action.getY()),
                                action.getX(), action.getY())
                );
            }
        }
        if(action != null) {
            this.basePanel.getMapPanel().addTileAt(action.getTile(), action.getX(), action.getY());
            this.basePanel.getMapPanel().repaint();
        }
    }
}
