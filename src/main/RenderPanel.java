package main;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    Dimension playArea;
    Block[][] blocksToDraw;
    BlockPanel[][] blockPanels;
    int blockWidth;
    int blockHeight;
    boolean firstRun = true;

    public RenderPanel(Dimension playAreaDimension){

        this.setPreferredSize(playAreaDimension);
        this.playArea = playAreaDimension;
        blockPanels = new BlockPanel[20][20];
        setBackground(Color.GRAY);
        for(int j = 0; j<20; j++){
            for(int i = 0; i<20; i++){
                blockPanels[i][j] = (new BlockPanel(playArea.height/20,playArea.height/20));
                add(blockPanels[i][j]);
            }
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i = 0; i<blocksToDraw.length; i++){
            for(int j = 0; j<blocksToDraw[i].length; j++){

                    if(firstRun){
                        blockPanels[i][j].setBlock(blocksToDraw[i][j]);
                        firstRun = false;
                    }

                    blockPanels[i][j].setBackground(blocksToDraw[i][j].color);
                }

                }

            }



    public void drawBoard(Block[][] blocks) {
        blocksToDraw = blocks;
    }

    public void setBlockSizes(int i, int i1) {
        this.blockWidth = i;
        this.blockHeight = i1;
    }

}
