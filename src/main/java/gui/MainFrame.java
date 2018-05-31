package gui;

import core.RPMap;
import gui.actions.menuActions.QuitAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {
    public static void main(String[] args){
        MainFrame frame = new MainFrame("Untitled (*) - RP Map Editor");
    }

    public MainFrame(String title) throws HeadlessException {
        super(title);

        final BasePanel basePanel = new BasePanel();

        this.setJMenuBar(new CustomMenu(basePanel));
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.getContentPane().add(basePanel);

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
