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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResizeBgImgAction implements ActionListener {
    private final BasePanel basePanel;

    public ResizeBgImgAction(BasePanel basePanel) {
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Width :"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Height :"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "What are your background's tiles size ?", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            this.basePanel.getMapPanel().setImgCaseWidth(Integer.parseInt(xField.getText()));
            this.basePanel.getMapPanel().setImgCaseHeight(Integer.parseInt(yField.getText()));

            this.basePanel.getMapPanel().repaint();
        }
    }
}
