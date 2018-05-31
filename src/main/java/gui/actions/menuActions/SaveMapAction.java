package gui.actions.menuActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.RPMap;
import gui.BasePanel;
import gui.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SaveMapAction implements ActionListener {
    private final boolean saveAs;
    private final BasePanel basePanel;

    public SaveMapAction(BasePanel basePanel, boolean saveAs) {
        this.basePanel = basePanel;
        this.saveAs = saveAs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RPMap map = this.basePanel.getMapPanel().getMap();
        map.cleanMap();

        File file = basePanel.getSavingFile();

        if(file == null || saveAs){
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
                    return extension.equals("rpmap");
                }

                @Override
                public String getDescription() {
                    return "RPMap - *.rpMap";
                }
            });

            Container frame = basePanel.getParent();
            while(frame.getClass() != MainFrame.class){
                frame = frame.getParent();
            }

            int fileSelectionResult;
            String extension;
            boolean again;

            do {
                again = false;
                fileSelectionResult = fc.showDialog(frame, "Save map");
                if (fileSelectionResult == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    extension = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
                    if(file.getName().lastIndexOf('.') == -1){
                        file = new File(file.getAbsolutePath() + ".rpMap");
                    } else if(!(extension.equals("rpmap"))){
                        JOptionPane.showMessageDialog(null, "Please select a rpMap file.", "Error", JOptionPane.ERROR_MESSAGE);
                        again = true;
                    }
                }
            } while(again);

            if(fileSelectionResult == JFileChooser.CANCEL_OPTION){
                file = null;
            }

        }


        if(file != null) {
            ObjectMapper mapper = new ObjectMapper();

            try {
                mapper.writeValue(file, map);
                this.basePanel.setSaved(true);
                this.basePanel.setSavingFile(file);
                Container ctr = basePanel.getParent();
                while (ctr.getClass() != MainFrame.class) {
                    ctr = ctr.getParent();
                }
                MainFrame frame = (MainFrame) ctr;

                frame.setTitle(file.getName().substring(0, file.getName().lastIndexOf('.')) + " - RP Map Editor");
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "There was an error saving the map.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
