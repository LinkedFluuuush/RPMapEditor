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

import gui.actions.menuActions.QuitAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {
    public static void main(String[] args){
        new MainFrame("Untitled (*) - RP Map Editor");
    }

    private MainFrame(String title) throws HeadlessException {
        super(title);

        final BasePanel basePanel = new BasePanel();
        final CustomMenu customMenu = new CustomMenu(basePanel);

        this.setJMenuBar(customMenu);
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.getContentPane().add(basePanel);
        customMenu.updateMenu();

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                QuitAction quitAction = new QuitAction(basePanel);
                quitAction.actionPerformed(null);
            }
        };
        this.addWindowListener(exitListener);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
