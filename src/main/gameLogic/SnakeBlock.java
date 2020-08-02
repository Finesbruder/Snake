package main.gameLogic;


import java.awt.*;

public class SnakeBlock extends Block {

    public SnakeBlock(int x, int y){
        super(x,y, Color.GREEN);
    }

    public String toString(){
        return "Ich bin ein SnakeBlock auf " + x + y;
    }
}
