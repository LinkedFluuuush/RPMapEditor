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
    private BasePanel basePanel;

    public ExportToImgAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RPMap map = basePanel.getMapPanel().getMap();
        map.cleanMap();

        Pair<Integer> upLeftCorner = map.getUpLeftCorner();
        Pair<Integer> mapSize = map.getMapSize();

        BufferedImage finalImage = new BufferedImage((mapSize.getP1() + 2)*30, (mapSize.getP2() + 2)*30,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = finalImage.createGraphics();

        MapPainter painter = basePanel.getMapPanel().getPainter();

        painter.paintMap(map, (mapSize.getP1() + 2)*30, (mapSize.getP2() + 2)*30, upLeftCorner.getP1() - 1, upLeftCorner.getP2() - 1, true, g);


        final JFileChooser fc = new JFileChooser(){
            @Override
            public void approveSelection()
            {
                if (getSelectedFile().exists())
                {
                    int n = JOptionPane.showConfirmDialog(
                            this,
                            "Do you want to overwrite this file?",
                            "Confirm Overwrite",
                            JOptionPane.YES_NO_OPTION);

                    if (n == JOptionPane.YES_OPTION)
                        super.approveSelection();
                }
                else
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
                if (extension != null) {
                    if (extension.equals("tiff") ||
                            extension.equals("tif") ||
                            extension.equals("gif") ||
                            extension.equals("jpeg") ||
                            extension.equals("jpg") ||
                            extension.equals("png")) {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public String getDescription() {
                return "Image types only";
            }
        });

        Container frame = basePanel.getParent();
        while(frame.getClass() != MainFrame.class){
            frame = frame.getParent();
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
                if(!(extension.equals("tiff") ||
                        extension.equals("tif") ||
                        extension.equals("gif") ||
                        extension.equals("jpeg") ||
                        extension.equals("jpg") ||
                        extension.equals("png"))){
                    JOptionPane.showMessageDialog(null, "Please select an image format between TIFF, TIF, GIF, JPEG, JPG or PNG.", "Error", JOptionPane.ERROR_MESSAGE);
                    again = true;
                }
            }
        } while(again);

        if(file != null) {
            try {
                ImageIO.write(finalImage, extension, file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
