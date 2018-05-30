package gui.actions.menuActions;

import core.RPMap;
import core.util.Pair;
import gui.BasePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ExportToImgAction implements ActionListener {
    private BasePanel basePanel;

    public ExportToImgAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RPMap map = basePanel.getMapPanel().getMap();

        Pair<Integer> startCoords = map.getUpLeftCorner();
        Pair<Integer> mapSize = map.getMapSize();

        BufferedImage finalImage = new BufferedImage((mapSize.getP1() + 2)*30, (mapSize.getP2() + 2)*30,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = finalImage.createGraphics();


    }
}
