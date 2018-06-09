package gui.actions.menuActions;

import core.RPMap;
import gui.BasePanel;
import gui.CustomMenu;
import gui.painters.MapPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ChangePainterAction implements ActionListener {
    private final BasePanel basePanel;
    private final Class<? extends MapPainter> painterClazz;
    private final JMenu customMenu;

    public ChangePainterAction(BasePanel basePanel, Class<? extends MapPainter> painterClazz, JMenu customMenu) {
        this.basePanel = basePanel;
        this.painterClazz = painterClazz;
        this.customMenu = customMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Constructor<? extends MapPainter> constructor = painterClazz.getConstructor(RPMap.class);
            this.basePanel.getMapPanel().setPainter(constructor.newInstance(this.basePanel.getMapPanel().getMap()));
            this.basePanel.getMapPanel().repaint();

            Component c = this.customMenu.getParent();
            while (!c.getClass().equals(CustomMenu.class)){
                c = c.getParent();
            }

            CustomMenu customMenu = (CustomMenu) c;
            customMenu.updateMenu();
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }
}
