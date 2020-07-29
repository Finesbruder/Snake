package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Controller implements ActionListener {


    View view;
    Board board;
    Timer timer;
    Snake snake;
    final Dimension frameSize;
    final Dimension playArea;

    public Controller(){
        //SETTING UP FRAME
        frameSize = new Dimension(800,800);
        playArea = new Dimension(800,800);

        //SETTING UP GAME BACKEND
        board = new Board(20,20, playArea.width/20, playArea.height/20, this);
        snake = board.getSnake();

        //SETTING UP GUI
        view = new View(frameSize, playArea,this);

        //STARTS GAME
        timer = new Timer(200, this);
        timer.start();
    }

    public static void main(String[] args){
        new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Block> iter = board.getUpdatedBlocks();
        while(iter.hasNext()){
            Block block = iter.next();
            view.drawColoredSquare(new Point(block.getX(),block.getY()), 40, block.color);
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
        timer.setDelay(timer.getDelay()-10);
    }
}
