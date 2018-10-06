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
import core.util.Pair;
import gui.BasePanel;
import gui.MainFrame;
import gui.painters.MapPainter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportToImgAction implements ActionListener {
    private final BasePanel basePanel;

    public ExportToImgAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RPMap map = basePanel.getMapPanel().getMap();
        map.cleanMap();

        Pair<Integer> upLeftCorner = map.getUpLeftCorner();
        Pair<Integer> mapSize = map.getMapSize();

        Container frame = basePanel.getParent();
        while (frame.getClass() != MainFrame.class) {
            frame = frame.getParent();
        }


        if(!mapSize.equals(new Pair<>(0, 0))) {
            BufferedImage finalImage = new BufferedImage((mapSize.getP1() + 2) * 30, (mapSize.getP2() + 2) * 30,
                    BufferedImage.TYPE_INT_RGB);


            Graphics2D g = finalImage.createGraphics();

            MapPainter painter = basePanel.getMapPanel().getPainter();

            painter.setWorkingMode(false);
            painter.paintMap(map, (mapSize.getP1() + 2) * 30, (mapSize.getP2() + 2) * 30, upLeftCorner.getP1() - 1, upLeftCorner.getP2() - 1, true, g);
            painter.setWorkingMode(true);

            final JFileChooser fc = new JFileChooser() {
                @Override
                public void approveSelection() {
                    if (getSelectedFile().exists()) {
                        int n = JOptionPane.showConfirmDialog(
                                this,
                                "Do you want to overwrite this file?",
                                "Confirm Overwrite",
                                JOptionPane.YES_NO_OPTION);

                        if (n == JOptionPane.YES_OPTION)
                            super.approveSelection();
                    } else
                        super.approveSelection();
                }
            };
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }

                    String extension = f.getName().substring(f.getName().lastIndexOf('.') + 1).toLowerCase();
                    return extension.equals("tiff") ||
                            extension.equals("tif") ||
                            extension.equals("gif") ||
                            extension.equals("jpeg") ||
                            extension.equals("jpg") ||
                            extension.equals("png");
                }

                @Override
                public String getDescription() {
                    return "Image types only";
                }
            });

            if (this.basePanel.getSavingFile() != null) {
                String defaultPath = this.basePanel.getSavingFile().getAbsolutePath();
                defaultPath = defaultPath.substring(0, defaultPath.lastIndexOf('.')) + ".png";
                File defaultFile = new File(defaultPath);

                fc.setSelectedFile(defaultFile);
            }

            File file = null;
            int fileSelectionResult;
            String extension = "";
            boolean again;

            do {
                again = false;
                fileSelectionResult = fc.showDialog(frame, "Export image");
                if (fileSelectionResult == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    extension = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
                    if (!(extension.equals("tiff") ||
                            extension.equals("tif") ||
                            extension.equals("gif") ||
                            extension.equals("jpeg") ||
                            extension.equals("jpg") ||
                            extension.equals("png"))) {
                        JOptionPane.showMessageDialog(null, "Please select an image format between TIFF, TIF, GIF, JPEG, JPG or PNG.", "Error", JOptionPane.ERROR_MESSAGE);
                        again = true;
                    }
                }
            } while (again);

            if (file != null) {
                try {
                    ImageIO.write(finalImage, extension, file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    "Nothing to export here !",
                    "Empty map",
                    JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
