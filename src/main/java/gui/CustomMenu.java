package gui;

import gui.actions.menuActions.ExportToImgAction;
import gui.actions.menuActions.NewMapAction;
import gui.actions.menuActions.OpenMapAction;
import gui.actions.menuActions.SaveMapAction;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class CustomMenu extends JMenuBar {
    // Menu components
    private JMenu fileMenu;
    private JMenuItem newFileItem;
    private JMenuItem openFileItem;
    private JMenuItem saveFileItem;
    private JMenuItem saveFileAsItem;
    private JMenuItem exportFileItem;
    private JMenuItem exitItem;

    private JMenu editMenu;
    private JMenuItem undoItem;
    private JMenuItem redoItem;

    private JMenu helpMenu;
    private JMenuItem aboutItem;
    private JMenuItem helpItem;

    private BasePanel basePanel;

    public CustomMenu(BasePanel basePanel) {
        super();

        this.basePanel = basePanel;

        //Building menu
        this.fileMenu = new JMenu("File");
        this.fileMenu.setMnemonic('f');

        this.newFileItem = new JMenuItem("New map");
        this.newFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        this.newFileItem.setMnemonic('n');
        this.newFileItem.addActionListener(new NewMapAction(this.basePanel));

        this.openFileItem = new JMenuItem("Open map file");
        this.openFileItem.setMnemonic('o');
        this.openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        this.openFileItem.addActionListener(new OpenMapAction(this.basePanel));

        this.saveFileItem = new JMenuItem("Save map");
        this.saveFileItem.setMnemonic('s');
        this.saveFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        this.saveFileItem.addActionListener(new SaveMapAction(this.basePanel, false));

        this.saveFileAsItem = new JMenuItem("Save map as...");
        this.saveFileAsItem.setMnemonic('a');
        this.saveFileAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        this.saveFileAsItem.addActionListener(new SaveMapAction(this.basePanel, true));

        this.exportFileItem = new JMenuItem("Export map as image");
        this.exportFileItem.setMnemonic('e');
        this.exportFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        this.exportFileItem.addActionListener(new ExportToImgAction(this.basePanel));

        this.exitItem = new JMenuItem("Quit");
        this.exitItem.setMnemonic('q');
        this.exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));

        this.editMenu = new JMenu("Edit");
        this.editMenu.setMnemonic('e');

        this.undoItem = new JMenuItem("Undo");
        this.undoItem.setMnemonic('u');
        this.undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        this.redoItem = new JMenuItem("Redo");
        this.redoItem.setMnemonic('r');
        this.redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));

        this.helpMenu = new JMenu("?");
        this.helpMenu.setMnemonic('?');

        this.helpItem = new JMenuItem("Help");
        this.helpItem.setMnemonic('h');
        this.helpItem.setAccelerator(KeyStroke.getKeyStroke("F1"));

        this.aboutItem = new JMenuItem("About");
        this.aboutItem.setMnemonic('a');

        this.fileMenu.add(newFileItem);
        this.fileMenu.add(openFileItem);
        this.fileMenu.addSeparator();
        this.fileMenu.add(saveFileItem);
        this.fileMenu.add(saveFileAsItem);
        this.fileMenu.add(exportFileItem);
        this.fileMenu.addSeparator();
        this.fileMenu.add(exitItem);

        this.editMenu.add(undoItem);
        this.editMenu.add(redoItem);

        this.helpMenu.add(helpItem);
        this.helpMenu.add(aboutItem);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);

    }
}
