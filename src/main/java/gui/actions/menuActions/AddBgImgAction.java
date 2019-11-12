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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AddBgImgAction implements ActionListener {
    private final BasePanel basePanel;
    private final JMenuItem toggleItem;
    private final JMenuItem resizeBgImgItem;

    public AddBgImgAction(BasePanel basePanel, JMenuItem toggleItem, JMenuItem resizeBgImgItem) {
        this.basePanel = basePanel;
        this.toggleItem = toggleItem;
        this.resizeBgImgItem = resizeBgImgItem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = null;

        final JFileChooser fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);

        FileFilter imageFilter = new FileNameExtensionFilter(
                "Image files", ImageIO.getReaderFileSuffixes());

        fc.addChoosableFileFilter(imageFilter);

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
            fileSelectionResult = fc.showDialog(frame, "Select background");
            if (fileSelectionResult == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                extension = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();


                if(!(Arrays.asList(ImageIO.getReaderFileSuffixes()).contains(extension))){
                    JOptionPane.showMessageDialog(null, "Please select an image file.", "Error", JOptionPane.ERROR_MESSAGE);
                    again = true;
                }
            }
        } while(again);

        if(fileSelectionResult == JFileChooser.CANCEL_OPTION){
            file = null;
        }

        if(file != null){
            try {

                this.basePanel.getMapPanel().setBgImage(ImageIO.read(file));
                this.basePanel.getMapPanel().setDrawImage(true);
                this.toggleItem.setText(this.toggleItem.getText()+ " \u2713");
                this.toggleItem.setEnabled(true);

                ActionEvent event = new ActionEvent(this.resizeBgImgItem, ActionEvent.ACTION_PERFORMED, "Anything", System.currentTimeMillis(), 0);

                for (ActionListener listener : this.resizeBgImgItem.getActionListeners()) {
                    listener.actionPerformed(event);
                }

                this.basePanel.getMapPanel().repaint();
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "There was an error opening the image. The file is probably corrupted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
