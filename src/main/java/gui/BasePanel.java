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
package gui;

import gui.painters.realPainter.DayLightMinimalistPainter;
import gui.painters.realPainter.NightLightMinimalistPainter;
import gui.util.MapAction;

import javax.swing.*;
import java.io.File;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BasePanel extends JLayeredPane {
    private final ToolPanel toolPanel;
    private final MapPanel mapPanel;

    private File savingFile;
    private boolean saved;
    private final Stack<MapAction> undoActions;
    private final Stack<MapAction> redoActions;

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
        this.toolPanel.setBounds(20, 20, 120, 300);

        this.mapPanel = new MapPanel(DayLightMinimalistPainter.class);


        this.add(mapPanel, Integer.valueOf(0));
        this.add(toolPanel, Integer.valueOf(1));

        this.mapPanel.repaint();

        this.savingFile = null;
        this.saved = false;
        this.undoActions = new Stack<>();
        this.redoActions = new Stack<>();

    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void addUndoAction(MapAction mapAction){
        if(mapAction != null) {
            try {
                if (!this.undoActions.lastElement().equals(mapAction)) {
                    this.undoActions.push(mapAction);
                }
            } catch (NoSuchElementException e){
                this.undoActions.push(mapAction);
            }
        }
    }

    public MapAction getLastUndoAction(){
        try{
            return this.undoActions.pop();
        } catch (EmptyStackException e){
            return null;
        }
    }

    public void addRedoAction(MapAction mapAction){
        if(mapAction != null){
            try{
                if(!this.redoActions.lastElement().equals(mapAction)){
                    this.redoActions.push(mapAction);
                }
            } catch (NoSuchElementException e){
                this.redoActions.push(mapAction);
            }
        }
    }

    public MapAction getLastRedoAction(){
        try {
            return this.redoActions.pop();
        } catch(EmptyStackException e){
            return null;
        }
    }

    public void clearRedoAction(){
        this.redoActions.clear();
    }
}
