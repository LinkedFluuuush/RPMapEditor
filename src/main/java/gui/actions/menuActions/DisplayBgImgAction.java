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
