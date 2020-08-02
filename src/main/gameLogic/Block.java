package main.gameLogic;

import java.awt.*;

public class Block {


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int x;
    int y;
    Color color;

    public Block(int x, int y, Color c) {
        color = c;
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x2, int y2) {
        this.x = x + x2;
        this.y = y + y2;
    }

    public String toString() {
        return "Ich bin ein Block auf: " + x + y;
    }


    public Color getColor() {
        return color;
    }
}
