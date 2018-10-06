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

import core.Tile;
import gui.actions.ChangeOrientationAction;
import gui.actions.SelectMoveMapAction;
import gui.actions.ToolDraggerMouseListener;
import gui.actions.ToolSelectionAction;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel {
    private Tile.TileType selectedTool;
    private Tile.TileOrientation toolOrientation;
    private boolean movePanel;

    public ToolPanel() {
        this.setSelectedTool(Tile.TileType.EMPTY);
        this.setToolOrientation(Tile.TileOrientation.TOP);

        this.setLayout(new GridLayout(14,1, 2, 2));

        JButton roomButton = new JButton("Room / Walls");
        roomButton.setPreferredSize(new Dimension(120,20));
        roomButton.addActionListener(new ToolSelectionAction(roomButton, this, Tile.TileType.EMPTY));

        // Default selected button
        roomButton.setEnabled(false);

        JButton doorButton = new JButton("Doors");
        doorButton.setPreferredSize(new Dimension(120,20));
        doorButton.addActionListener(new ToolSelectionAction(doorButton, this, Tile.TileType.DOOR));

        JButton smallDirtButton = new JButton("Small dirt");
        smallDirtButton.setPreferredSize(new Dimension(120,20));
        smallDirtButton.addActionListener(new ToolSelectionAction(smallDirtButton, this, Tile.TileType.SMALLDIRT));

        JButton bigDirtButton = new JButton("Big dirt");
        bigDirtButton.setPreferredSize(new Dimension(120,20));
        bigDirtButton.addActionListener(new ToolSelectionAction(bigDirtButton, this, Tile.TileType.BIGDIRT));

        JButton treeButton = new JButton("Tree");
        treeButton.setPreferredSize(new Dimension(120,20));
        treeButton.addActionListener(new ToolSelectionAction(treeButton, this, Tile.TileType.TREE));

        JButton waterButton = new JButton("Water");
        waterButton.setPreferredSize(new Dimension(120,20));
        waterButton.addActionListener(new ToolSelectionAction(waterButton, this, Tile.TileType.WATER));

        JButton blurButton = new JButton("Blur");
        blurButton.setPreferredSize(new Dimension(120,20));
        blurButton.addActionListener(new ToolSelectionAction(blurButton, this, Tile.TileType.BLUR));

        JButton windowButton = new JButton("Windows");
        windowButton.setPreferredSize(new Dimension(120,20));
        windowButton.addActionListener(new ToolSelectionAction(windowButton, this, Tile.TileType.WINDOW));

        JButton stairButton = new JButton("Stairs");
        stairButton.setPreferredSize(new Dimension(120,20));
        stairButton.addActionListener(new ToolSelectionAction(stairButton, this, Tile.TileType.STAIR));

        JButton orientationButton = new JButton("UP");
        orientationButton.setPreferredSize(new Dimension(50, 20));
        orientationButton.addActionListener(new ChangeOrientationAction(orientationButton, this));

        JButton moveMapButton = new JButton("Scroll map");
        moveMapButton.setPreferredSize(new Dimension(50, 20));
        moveMapButton.addActionListener(new SelectMoveMapAction(this, moveMapButton));

        JLabel toolsLabel = new JLabel("Tools");
        toolsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toolsLabel.addMouseMotionListener(new ToolDraggerMouseListener(this));

        this.add(toolsLabel);
        this.add(moveMapButton);
        this.add(roomButton);
        this.add(new JLabel("Objects"));
        this.add(doorButton);
        this.add(windowButton);
        this.add(stairButton);
        this.add(smallDirtButton);
        this.add(bigDirtButton);
        this.add(treeButton);
        this.add(waterButton);
        this.add(blurButton);
        this.add(new JLabel("Orientation"));
        this.add(orientationButton);


        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        this.setBackground(Color.WHITE);
    }

    public Tile.TileType getSelectedTool() {
        return selectedTool;
    }

    public void setSelectedTool(Tile.TileType selectedTool) {
        this.selectedTool = selectedTool;
    }

    public Tile.TileOrientation getToolOrientation() {
        return toolOrientation;
    }

    public void setToolOrientation(Tile.TileOrientation toolOrientation) {
        this.toolOrientation = toolOrientation;
    }

    public boolean isMovePanel() {
        return movePanel;
    }

    public void setMovePanel(boolean movePanel) {
        this.movePanel = movePanel;
    }
}
