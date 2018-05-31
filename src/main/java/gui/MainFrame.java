package gui;

import core.RPMap;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static void main(String[] args){
        MainFrame frame = new MainFrame("Untitled (*) - RP Map Editor");
    }

    public MainFrame(String title) throws HeadlessException {
        super(title);

        BasePanel basePanel = new BasePanel();

        this.setJMenuBar(new CustomMenu(basePanel));
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.getContentPane().add(basePanel);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
