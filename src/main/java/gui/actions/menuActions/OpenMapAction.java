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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import core.RPMap;
import core.util.IntegerPairDeserializer;
import core.util.Pair;
import gui.BasePanel;
import gui.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OpenMapAction implements ActionListener {
    private final BasePanel basePanel;

    public OpenMapAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = null;

        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }

                String extension = f.getName().substring(f.getName().lastIndexOf('.') + 1).toLowerCase();
                return extension.equals("rpmap");
            }

            @Override
            public String getDescription() {
                return "RPMap - *.rpMap";
            }
        });
        fc.setSelectedFile(this.basePanel.getSavingFile());

        Container ctr = basePanel.getParent();
        while (ctr.getClass() != MainFrame.class) {
            ctr = ctr.getParent();
        }

        MainFrame frame = (MainFrame) ctr;

        int fileSelectionResult;
        String extension;
        boolean again;

        do {
            again = false;
            fileSelectionResult = fc.showDialog(frame, "Open map");
            if (fileSelectionResult == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                extension = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
                if(!(extension.equals("rpmap"))){
                    JOptionPane.showMessageDialog(null, "Please select a rpMap file.", "Error", JOptionPane.ERROR_MESSAGE);
                    again = true;
                }
            }
        } while(again);

        if(fileSelectionResult == JFileChooser.CANCEL_OPTION){
            file = null;
        }

        if(file != null){
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                SimpleModule module = new SimpleModule();
                module.addKeyDeserializer(Pair.class, new IntegerPairDeserializer());
                mapper.registerModule(module);
                RPMap map = mapper.readValue(file, RPMap.class);
                this.basePanel.setSaved(true);
                this.basePanel.setSavingFile(file);

                frame.setTitle(file.getName().substring(0, file.getName().lastIndexOf('.')) + " - RP Map Editor");

                this.basePanel.getMapPanel().setMap(map);
                this.basePanel.getMapPanel().repaint();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "There was an error opening the map. The file is probably corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
