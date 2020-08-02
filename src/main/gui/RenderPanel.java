package main.gui;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class RenderPanel extends JPanel {

    Dimension playArea;
    BufferedImage screen;
    WritableRaster screenRaster;
    BufferedImage image;

    public RenderPanel(Dimension playAreaDimension) {
        this.setPreferredSize(playAreaDimension);
        this.playArea = playAreaDimension;
        setLayout(null);
        initScreen();
        image = requestImage();

    }

    public void initScreen() {
        screen = new BufferedImage(playArea.width, playArea.height, BufferedImage.TYPE_INT_RGB);
        screenRaster = screen.getRaster();
    }


    public void drawColoredSquare(Point point, int scale, Color color) {
        int[] pixels = new int[scale * scale];
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color.getRGB();
        }
        screenRaster.setDataElements((int) point.getX() * scale, (int) point.getY() * scale, scale, scale, pixels);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(screen, 0, 0, null);
    }

    public BufferedImage requestImage() {

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\Florian\\Desktop\\download.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public void drawApple(Point point, int scale) {

        BufferedImage apple = image.getSubimage(0, 0, 48, 151);
        Raster raster = apple.getRaster();
        int[] pixels = new int[raster.getHeight() * raster.getWidth()];
        System.out.println(pixels.length);
        raster.getPixel(0, 0, pixels);

        screenRaster.setDataElements((int) point.getX() * scale, (int) point.getY() * scale, scale, scale, pixels);
    }
}


