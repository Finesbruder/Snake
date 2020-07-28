package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {


    View view;
    Board board;
    Timer timer;
    Snake snake;
    final Dimension frameSize;
    final Dimension playArea;

    public Controller(){
        frameSize = new Dimension(800,800);
        playArea = new Dimension(800,800);
        board = new Board(20,20, playArea.width/20, playArea.height/20, this);
        snake = board.getSnake();


        view = new View(frameSize, playArea,this);
        view.setBlockSizes(playArea.width/20, playArea.height/20);
        view.drawBoard(board.getBoard());
        timer = new Timer(200, this);
        timer.start();
    }

    public static void main(String[] args){
        new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.drawBoard(board.getBoard());
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
