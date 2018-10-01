package gui;

import gui.actions.AboutAction;
import gui.actions.menuActions.*;
import gui.painters.MapPainter;
import gui.painters.realPainter.DayLightMinimalistPainter;
import gui.painters.realPainter.NightLightMinimalistPainter;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class CustomMenu extends JMenuBar {
    private final BasePanel basePanel;

    private final List<Class<? extends MapPainter>> painterList = new LinkedList<>();

    CustomMenu(BasePanel basePanel) {
        super();

        this.basePanel = basePanel;

        painterList.add(DayLightMinimalistPainter.class);
        painterList.add(NightLightMinimalistPainter.class);

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
        undoItem.addActionListener(new UndoRedoAction(basePanel, true));

        JMenuItem redoItem = new JMenuItem("Redo");
        redoItem.setMnemonic('r');
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        redoItem.addActionListener(new UndoRedoAction(basePanel, false));

        JMenu toolsMenu = new JMenu("Tools");
        toolsMenu.setMnemonic('t');

        JMenuItem toggleBg = new JMenuItem("Display background image");
        toggleBg.setMnemonic('d');
        toggleBg.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        toggleBg.addActionListener(new DisplayBgImgAction(basePanel, toggleBg));
        toggleBg.setEnabled(false);

        JMenuItem resizeBgImgItem = new JMenuItem("Resize background image");
        resizeBgImgItem.setMnemonic('r');
        //undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        resizeBgImgItem.addActionListener(new ResizeBgImgAction(basePanel));

        JMenuItem addBgImgItem = new JMenuItem("Add background image");
        addBgImgItem.setMnemonic('a');
        //undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        addBgImgItem.addActionListener(new AddBgImgAction(basePanel, toggleBg, resizeBgImgItem));



        JMenu helpMenu = new JMenu("?");
        helpMenu.setMnemonic('?');

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setMnemonic('h');
        helpItem.setAccelerator(KeyStroke.getKeyStroke("F1"));

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic('a');
        aboutItem.addActionListener(new AboutAction(basePanel));

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
        editMenu.addSeparator();

        JMenuItem stylingItem;
        String painterName;
        Method m;

        for(Class<? extends MapPainter> clazz : painterList){
            try {
                m = clazz.getDeclaredMethod("getPainterName");
                painterName = (String) m.invoke(null);
            } catch (Exception e) {
                painterName = clazz.getName();
            }

            stylingItem = new JMenuItem(painterName);
            stylingItem.addActionListener(new ChangePainterAction(basePanel, clazz, editMenu));
            editMenu.add(stylingItem);
        }

        toolsMenu.add(addBgImgItem);
        toolsMenu.add(resizeBgImgItem);
        toolsMenu.add(toggleBg);

        helpMenu.add(helpItem);
        helpMenu.add(aboutItem);

        this.add(fileMenu);
        this.add(editMenu);
        this.add(toolsMenu);
        this.add(helpMenu);
    }

    public void updateMenu(){
        int menuSize = this.getMenuCount();

        for(int i = 0 ; i < menuSize ; i++){
            JMenu menu = this.getMenu(i);
            if(menu != null) {
                int subMenuSize = menu.getItemCount();
                for (int j = 0; j < subMenuSize; j++) {
                    JMenuItem item = menu.getItem(j);

                    if (item != null){
                        Class<? extends MapPainter> currentPainter = this.basePanel.getMapPanel().getPainter().getClass();
                        Method m;
                        String painterName;
                        try {
                            m = currentPainter.getDeclaredMethod("getPainterName");
                            painterName = (String) m.invoke(null);
                        } catch (Exception e){
                            painterName = currentPainter.getName();
                        }

                        if(item.getText().endsWith(" \u2713")){
                            item.setText(item.getText().substring(0, item.getText().length() - 2));
                        }

                        if(item.getText().equals(painterName)){
                            item.setText(item.getText() + " \u2713");
                        }
                    }
                }
            }
        }

        this.repaint();
    }
}
