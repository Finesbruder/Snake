package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RenderPanel extends JPanel {

    Dimension playArea;
    Block[][] blocksToDraw;
    BlockPanel[][] blockPanels;
    int blockWidth;
    int blockHeight;
    BufferedImage screen;
    WritableRaster screenRaster;

    public RenderPanel(Dimension playAreaDimension){

        this.setPreferredSize(playAreaDimension);
        //setLayout(new GridLayout(20, 20,5,5));

        setLayout(null);
        this.playArea = playAreaDimension;
        initScreen();
        //for(int j = 0; j<20; j++){
        //    for(int i = 0; i<20; i++){
        //        blockPanels[i][j] = (new BlockPanel(playArea.height/20,playArea.height/20));
                //add(blockPanels[i][j]);
            //}
        //}
    }

    public void initScreen(){
        screen = new BufferedImage(playArea.width, playArea.height, BufferedImage.TYPE_INT_RGB);
        screenRaster = screen.getRaster();
    }


    public void drawColoredSquare(Point point, int scale, Color color){
        int[] pixels = new int[scale*scale];
        for(int i = 0; i<pixels.length; i++){
            pixels[i] = 200;//color.getRGB();
        }
        screenRaster.setDataElements((int) point.getX()*scale,(int) point.getY()*scale, scale,scale, pixels);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(screen, 0, 0,null);
    /*
        for(int i = 0; i<blocksToDraw.length; i++){
            for(int j = 0; j<blocksToDraw[i].length; j++){

                    if(firstRun){
                        blockPanels[i][j].setBlock(blocksToDraw[i][j]);
                        firstRun = false;
                    }

                    blockPanels[i][j].setBackground(blocksToDraw[i][j].color);
                }

        }

     */

    }

    public Image requestImage() {

        Image image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\Florian\\Desktop\\Snake sprite sheet_0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }



    public void drawBoard(Block[][] blocks) {
        blocksToDraw = blocks;
    }

    public void setBlockSizes(int i, int i1) {
        this.blockWidth = i;
        this.blockHeight = i1;
    }

}
