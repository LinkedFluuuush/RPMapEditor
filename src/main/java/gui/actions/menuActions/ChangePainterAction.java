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
