package main;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;


    public static Direction getCodedDirection(String directionString){
        switch (directionString){
            case "w":
                return UP;
            case "s":
                return DOWN;
            case "a":
                return LEFT;
            case "d":
                return RIGHT;
        }
        return DOWN;
    }

    public static boolean isItOpposite(Direction currDir, Direction newDir){
        switch (currDir){
            case UP:
                return newDir == DOWN;
            case DOWN:
                return newDir == UP;
            case LEFT:
                return newDir == RIGHT;
            case RIGHT:
                return newDir == LEFT;
        }

        return false;
    }
}
