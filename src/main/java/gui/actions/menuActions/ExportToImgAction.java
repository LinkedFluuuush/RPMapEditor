package gui.actions.menuActions;

import core.RPMap;
import core.util.Pair;
import gui.BasePanel;
import gui.painters.MapPainter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportToImgAction implements ActionListener {
    private BasePanel basePanel;

    public ExportToImgAction(BasePanel basePanel){
        this.basePanel = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RPMap map = basePanel.getMapPanel().getMap();

        Pair<Integer> upLeftCorner = map.getUpLeftCorner();
        Pair<Integer> mapSize = map.getMapSize();

        System.out.println(mapSize);

        BufferedImage finalImage = new BufferedImage((mapSize.getP1() + 2)*30, (mapSize.getP2() + 2)*30,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = finalImage.createGraphics();

        MapPainter painter = basePanel.getMapPanel().getPainter();

        painter.paintMap(map, (mapSize.getP1() + 2)*30, (mapSize.getP2() + 2)*30, upLeftCorner.getP1() - 1, upLeftCorner.getP2() - 1, true, g);

        File outputfile = new File("image.jpg");
        try {
            ImageIO.write(finalImage, "jpg", outputfile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }
}
