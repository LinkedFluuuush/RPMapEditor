package gui;

import gui.actions.menuActions.*;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class CustomMenu extends JMenuBar {

    public CustomMenu(BasePanel basePanel) {
        super();
        //Building menu
        // Menu components
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('f');

        JMenuItem newFileItem = new JMenuItem("New map");
        newFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newFileItem.setMnemonic('n');
        newFileItem.addActionListener(new NewMapAction(basePanel));

        JMenuItem openFileItem = new JMenuItem("Open map file");
        openFileItem.setMnemonic('o');
        openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        openFileItem.addActionListener(new OpenMapAction(basePanel));

        JMenuItem saveFileItem = new JMenuItem("Save map");
        saveFileItem.setMnemonic('s');
        saveFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveFileItem.addActionListener(new SaveMapAction(basePanel, false));

        JMenuItem saveFileAsItem = new JMenuItem("Save map as...");
        saveFileAsItem.setMnemonic('a');
        saveFileAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        saveFileAsItem.addActionListener(new SaveMapAction(basePanel, true));

        JMenuItem exportFileItem = new JMenuItem("Export map as image");
        exportFileItem.setMnemonic('e');
        exportFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        exportFileItem.addActionListener(new ExportToImgAction(basePanel));

        JMenuItem exitItem = new JMenuItem("Quit");
        exitItem.setMnemonic('q');
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(new QuitAction(basePanel));

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('e');

        JMenuItem undoItem = new JMenuItem("Undo");
        undoItem.setMnemonic('u');
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setMnemonic('r');
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));

        JMenu helpMenu = new JMenu("?");
        helpMenu.setMnemonic('?');

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setMnemonic('h');
        helpItem.setAccelerator(KeyStroke.getKeyStroke("F1"));

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic('a');

        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.addSeparator();
        fileMenu.add(saveFileItem);
        fileMenu.add(saveFileAsItem);
        fileMenu.add(exportFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        editMenu.add(undoItem);
        editMenu.add(redoItem);

        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);

    }
}
