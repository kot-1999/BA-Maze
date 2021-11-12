package game.gameLogic.cells;

import java.util.Random;

public enum CellSide{
    UP,LEFT,BOTTOM,RIGHT,
    //not a side
    NAW;

    public CellSide getOposite(){
        switch (this){
            case UP:
                return BOTTOM;
            case BOTTOM:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return NAW;
        }
    }

    static public CellSide getRandomSide(){
        Random r =new Random();
        int rand= r.nextInt(4);
        switch (rand){
            case 0:
                return UP;
            case 1:
                return LEFT;
            case 2:
                return BOTTOM;
            default :
                return RIGHT;
        }
    }
}
