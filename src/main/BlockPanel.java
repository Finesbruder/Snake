package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class BlockPanel extends JPanel {


    Block block;

    public BlockPanel(int blockwidth, int blockheight){
        this(blockwidth,blockheight,null);
    }
    public BlockPanel(int blockwidth, int blockheight, Block block){
        setSize(blockwidth,blockheight);
        setBackground(Color.BLACK);
        this.block = block;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(block instanceof SnakeBlock){
            Image img = requestImage();
            g.drawImage(img,0,0, 40,40,null);
        }

    }

    private Image requestImage() {

        Image image = null;
        try {
            image = ImageIO.read(new URL("C:\\Users\\Florian\\Desktop\\Snake sprite sheet_0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
