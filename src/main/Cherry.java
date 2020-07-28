package main;

import java.awt.*;

public class Cherry extends Block {

    public Cherry(int x, int y){
        super(x,y, Color.RED);
    }

    public String toString(){
        return "Ich bin ein CherryBlock auf: " +x+y;
    }
}
