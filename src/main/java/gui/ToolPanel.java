package gui;

import core.Tile;
import gui.actions.ChangeOrientationAction;
import gui.actions.ToolDraggerMouseListener;
import gui.actions.ToolSelectionAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ToolPanel extends JPanel {
    private Tile.TileType selectedTool;
    private Tile.TileOrientation toolOrientation;

    private JButton roomButton;
    private JButton doorButton;
    private JButton smallDirtButton;
    private JButton bigDirtButton;
    private JButton treeButton;
    private JButton waterButton;
    private JButton blurButton;
    private JButton windowButton;
    private JButton stairButton;

    private JButton orientationButton;

    public ToolPanel() {
        this.setSelectedTool(Tile.TileType.EMPTY);
        this.setToolOrientation(Tile.TileOrientation.TOP);

        this.setLayout(new GridLayout(13,1, 2, 2));

        this.roomButton = new JButton("Room / Walls");
        this.roomButton.setPreferredSize(new Dimension(120,20));
        this.roomButton.addActionListener(new ToolSelectionAction(this.roomButton, this, Tile.TileType.EMPTY));

        // Default selected button
        this.roomButton.setEnabled(false);

        this.doorButton = new JButton("Doors");
        this.doorButton.setPreferredSize(new Dimension(120,20));
        this.doorButton.addActionListener(new ToolSelectionAction(this.doorButton, this, Tile.TileType.DOOR));

        this.smallDirtButton = new JButton("Small dirt");
        this.smallDirtButton.setPreferredSize(new Dimension(120,20));
        this.smallDirtButton.addActionListener(new ToolSelectionAction(this.smallDirtButton, this, Tile.TileType.SMALLDIRT));

        this.bigDirtButton = new JButton("Big dirt");
        this.bigDirtButton.setPreferredSize(new Dimension(120,20));
        this.bigDirtButton.addActionListener(new ToolSelectionAction(this.bigDirtButton, this, Tile.TileType.BIGDIRT));

        this.treeButton = new JButton("Tree");
        this.treeButton.setPreferredSize(new Dimension(120,20));
        this.treeButton.addActionListener(new ToolSelectionAction(this.treeButton, this, Tile.TileType.TREE));

        this.waterButton = new JButton("Water");
        this.waterButton.setPreferredSize(new Dimension(120,20));
        this.waterButton.addActionListener(new ToolSelectionAction(this.waterButton, this, Tile.TileType.WATER));

        this.blurButton = new JButton("Blur");
        this.blurButton.setPreferredSize(new Dimension(120,20));
        this.blurButton.addActionListener(new ToolSelectionAction(this.blurButton, this, Tile.TileType.BLUR));

        this.windowButton = new JButton("Windows");
        this.windowButton.setPreferredSize(new Dimension(120,20));
        this.windowButton.addActionListener(new ToolSelectionAction(this.windowButton, this, Tile.TileType.WINDOW));

        this.stairButton = new JButton("Stairs");
        this.stairButton.setPreferredSize(new Dimension(120,20));
        this.stairButton.addActionListener(new ToolSelectionAction(this.stairButton, this, Tile.TileType.STAIR));

        this.orientationButton = new JButton("UP");
        this.orientationButton.setPreferredSize(new Dimension(50, 20));
        this.orientationButton.addActionListener(new ChangeOrientationAction(this.orientationButton, this));

        JLabel toolsLabel = new JLabel("Tools");
        toolsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toolsLabel.addMouseMotionListener(new ToolDraggerMouseListener(this));

        this.add(toolsLabel);
        this.add(this.roomButton);
        this.add(new JLabel("Objects"));
        this.add(this.doorButton);
        this.add(this.windowButton);
        this.add(this.stairButton);
        this.add(this.smallDirtButton);
        this.add(this.bigDirtButton);
        this.add(this.treeButton);
        this.add(this.waterButton);
        this.add(this.blurButton);
        this.add(new JLabel("Orientation"));
        this.add(this.orientationButton);

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
}
