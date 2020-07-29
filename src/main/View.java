package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class View extends JFrame implements KeyListener{

    public JFrame jframe;
    public RenderPanel renderPanel;
    Controller controller;

    public View(Dimension frameSize, Dimension playArea, Controller controller){
        this.controller = controller;
        initComponents(frameSize, playArea);
    }

    public void initComponents(Dimension frameSize, Dimension playArea){
        jframe = new JFrame("Snakerüüüüüüüüüüüü");
        renderPanel = new RenderPanel(playArea);
        jframe.add(renderPanel);
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(frameSize);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void drawBoard(Block[][] blocks) {
        renderPanel.drawBoard(blocks);
        renderPanel.repaint();
    }

    public void drawColoredSquare(Point p, int scale, Color c){
        renderPanel.drawColoredSquare(p, scale, c);
    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    private void send(String direction) {
        controller.receive(direction);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                send("w");
                break;
            case 's':
                send("s");
                break;
            case 'a':
                send("a");
                break;
            case 'd':
                send("d");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void setBlockSizes(int i, int i1) {
        renderPanel.setBlockSizes(i, i1);
    }


    public void drawBlock(Block block) {
        renderPanel.drawColoredSquare(new Point(block.getX(), block.getY()), 40, block.color);

    }
}
