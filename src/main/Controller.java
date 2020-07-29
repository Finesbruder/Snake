package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;

public class Controller implements ActionListener {


    View view;
    Board board;
    Timer timer;
    Snake snake;
    final Dimension FRAMESIZE;
    final Dimension PLAYAREA;
    final int SCALE;
    final int SIDELENGTH;
    final int PLAYSIZE;

    public Controller(int playSize, int blocksPerSide){
        //SETTING UP FRAME
        PLAYSIZE = playSize;
        FRAMESIZE = new Dimension(PLAYSIZE,PLAYSIZE);
        PLAYAREA = new Dimension(PLAYSIZE,PLAYSIZE);
        SIDELENGTH = blocksPerSide;
        SCALE = PLAYSIZE/SIDELENGTH;

        //SETTING UP GAME BACKEND
        board = new Board(SIDELENGTH,SIDELENGTH, this);
        snake = board.getSnake();

        //SETTING UP GUI
        view = new View(FRAMESIZE, PLAYAREA,this);

        //STARTS GAME
        timer = new Timer(150, this);
        timer.start();
    }

    public static void main(String[] args){
        new Controller(800,80);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Map.Entry<Point, Block>> iter = board.getUpdatedBlocks();
        while(iter.hasNext()){
            Block block = iter.next().getValue();
            view.drawColoredSquare(new Point(block.getX(),block.getY()), SCALE, block.color);
            iter.remove();
        }
        view.renderPanel.repaint();
        board.updateBoardState();
    }

    public void receive(String dir) {
        Direction direction = Direction.getCodedDirection(dir);
        snake.updateDirection(direction);
    }

    public void speedUpTimer() {
        timer.setDelay((int) (timer.getDelay()-timer.getDelay()*0.05));
    }
}
