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
        jframe = new JFrame("Snakerüüüüüüüüüüüü");
        renderPanel = new RenderPanel(playArea);
        renderPanel.setLayout(new GridLayout(20, 20,5,5));

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


}
