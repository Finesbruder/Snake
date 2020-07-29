package main;

import java.awt.*;
import java.util.*;

public class Board {

    Snake snake;
    Cherry cherry;
    Controller controller;
    Color endColor;
    int boardHeight;
    int boardWidth;
    HashMap<Point, Block> memory;


    public Board(int height, int width, Controller controller) {

        boardHeight = height;
        boardWidth = width;
        this.controller = controller;
        memory = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                memory.put(new Point(j, i), new Block(j, i, Color.BLACK));
            }
        }
        endColor = new Color(0, 0, 0);
        spawnSnake();
        spawnCherry();
    }

    public void updateBoardState() {
        if (!snake.isDead()) {
            if (!cherryEaten()) {
                Block snakeTailEnd = snake.getTailEnd();
                snakeTailEnd = new Block(snakeTailEnd.getX(), snakeTailEnd.getY(), Color.BLACK);
                memory.put(new Point((int) snakeTailEnd.getX(), (int) snakeTailEnd.getY()), snakeTailEnd);
                snake.move();
            } else {
                snake.growAndMove();
                spawnCherry();
            }
            try {
                Block[] snakeBlocks = snake.getBody();
                for (int i = 0; i < snakeBlocks.length; i++) {
                    memory.put(new Point((int) snakeBlocks[i].getX(), (int) snakeBlocks[i].getY()), snakeBlocks[i]);
                }
            } catch (Exception e) {
                snake.kill();
            }
        }
    }

    public boolean cherryEaten() {
        Block snakeHead = snake.getHead();
        return (cherry.getX() == snakeHead.getX() && cherry.getY() == snakeHead.getY());
    }

    public Snake getSnake() {
        return snake;
    }

    public void spawnSnake() {
        snake = new Snake(new SnakeBlock(boardWidth / 2, 0), controller);
        memory.put(new Point(boardWidth / 2, 0), snake.getHead());
    }

    public Iterator<Map.Entry<Point, Block>> getUpdatedBlocks() {
        return memory.entrySet().iterator();
    }

    public void spawnCherry() {
        Random generator = new Random();
        int randX = generator.nextInt(boardWidth);
        int randY = generator.nextInt(boardHeight);

        cherry = new Cherry(randX, randY);
        memory.put(new Point(randX, randY), cherry);
    }
}
