package main;


public class Snake {

    SnakeBlock head;
    SnakeBlock[] bodyBlocks;
    Direction direction;
    Controller controller;
    boolean dead = false;

    public Snake(SnakeBlock head, Controller controller){
        bodyBlocks = new SnakeBlock[1];
        bodyBlocks[0] = head;
        this.head = bodyBlocks[0];
        direction = Direction.DOWN;
        this.controller = controller;
    }

    public void move(){
        for(int i = bodyBlocks.length-1; i>0; i--){
            bodyBlocks[i].x = bodyBlocks[i-1].getX();
            bodyBlocks[i].y = bodyBlocks[i-1].getY();
        }

            switch (direction){
                case UP:
                    head.setPosition(0, -1);
                    break;
                case DOWN:
                    head.setPosition(0,1);
                    break;
                case RIGHT:
                    head.setPosition(1,0);
                    break;
                case LEFT:
                    head.setPosition(-1,0);
                    break;
            }


        for(int i = 1; i<bodyBlocks.length; i++){
            if(head.getX() == bodyBlocks[i].getX() && head.getY() == bodyBlocks[i].getY()){
                kill();
            }
        }

    }
    public boolean isDead(){
        return dead;
    }

    public Block getHead(){
        return this.bodyBlocks[0];
    }

    public Block getTailEnd(){
        return this.bodyBlocks[bodyBlocks.length-1];
    }

    public void updateDirection(Direction direction) {
        if(bodyBlocks.length>1){
            if(!(Direction.isItOpposite(this.direction, direction))){
                this.direction = direction;
            }
        }else{
            this.direction = direction;
        }
    }

    public void addBodyBlock(SnakeBlock block) {
        SnakeBlock[] blocks = new SnakeBlock[bodyBlocks.length+1];
        System.arraycopy(bodyBlocks,0,blocks,0,bodyBlocks.length);
        blocks[blocks.length-1] = block;
        bodyBlocks = blocks;
    }

    public Block[] getBody() {
        return this.bodyBlocks;
    }

    public void growAndMove() {
        int oldX = getTailEnd().getX();
        int oldY = getTailEnd().getY();
        move();
        addBodyBlock(new SnakeBlock(oldX, oldY));
        controller.speedUpTimer();

    }

    public void kill() {
        this.dead = true;
        System.out.println("you suck at snake");
    }
}
