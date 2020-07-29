package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board {

    Block[][] boardBlocks;
    Snake snake;
    int blockHeight;
    int blockWidth;
    Cherry cherry;
    Controller controller;
    Color endColor;
    int boardHeight;
    int boardWidth;
    ArrayList<Block> blocksToUpdate;


    public Board(int v, int h, int blockWidth, int blockHeight, Controller controller){

        boardHeight = v;
        boardWidth = h;
        blocksToUpdate = new ArrayList<>();
        this.blockHeight = blockHeight;
        this.controller = controller;
        this.blockWidth = blockWidth;
        endColor = new Color(0,0,0);
        boardBlocks = new Block[h][v];
        for(int i = 0; i<boardBlocks.length; i++){
            for(int j = 0; j<boardBlocks[i].length; j++){
                boardBlocks[i][j] = new Block(i,j, endColor);
            }
        }
        spawnSnake();
        spawnCherry();
    }

    public void updateBoardState(){
        if(!snake.isDead()){
            Block snakeTailEnd = snake.getTailEnd();
            if(!cherryEaten()){
                boardBlocks[snakeTailEnd.getX()][snakeTailEnd.getY()] = new Block(snakeTailEnd.getX(), snakeTailEnd.getY(), Color.GREEN);
                snake.move();
            }else{
                snake.growAndMove();
                spawnCherry();
            }
                try{
                    Block[] snakeBlocks = snake.getBody();
                    for(int i = 0; i<snakeBlocks.length; i++){
                        boardBlocks[snakeBlocks[i].getX()][snakeBlocks[i].getY()] = snakeBlocks[i];
                    }
                }catch (Exception e){
                    snake.kill();
                }
            }
        else{
                endScreen();
        }
    }

    private void endScreen() {
        for(int i = 0; i<boardBlocks.length; i++){
            Random ran = new Random();
            endColor = new Color(i*30%256, ran.nextInt(256), ran.nextInt(256));
            for(int j = 0; j<boardBlocks[i].length;j++){
                boardBlocks[i][j] = new Block(i,j, endColor);
            }
        }
    }

    public boolean cherryEaten(){
        Block snakeHead = snake.getHead();
        return (cherry.getX() == snakeHead.getX() && cherry.getY() == snakeHead.getY());
    }

    public Snake getSnake() {
        return snake;
    }

    public void spawnSnake(){
        snake = new Snake(new SnakeBlock(boardBlocks.length/2, 0), controller);
        boardBlocks[boardBlocks.length/2][0] = snake.getHead();
        blocksToUpdate.add(boardBlocks[boardBlocks.length/2][0]);
    }

    public Iterator<Block> getUpdatedBlocks(){
        return blocksToUpdate.iterator();
    }

    public void spawnCherry(){
        Random generator = new Random();
        int randX = generator.nextInt(boardBlocks.length);
        int randY = generator.nextInt(boardBlocks[0].length);
        cherry = new Cherry(randX, randY);
        boardBlocks[randX][randY] = cherry;
        blocksToUpdate.add(cherry);
    }
}
