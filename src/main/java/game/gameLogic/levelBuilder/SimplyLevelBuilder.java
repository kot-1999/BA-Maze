package game.gameLogic.levelBuilder;

import game.gameLogic.Maze;

import game.gameLogic.cellBuilder.CellWithCoinBuilder;

public class SimplyLevelBuilder extends LevelBuilder{


    @Override
    public Maze buildNext() {
        Maze result=new Maze(startX,startY);
        CellWithCoinBuilder mcb=new CellWithCoinBuilder();
        mcb.setCoinsNumber(4*(level+1));
        mcb.setMaze(result);
        mcb.setEndCellPosition(startX-1,startY-1);
        mcb.build();


        coridorBuilder.setMaze(result);
        coridorBuilder.build();

        startY+=2;
        startX++;
        level++;
        System.out.println(level+" "+startX+" "+startY);
        return result;
    }

    @Override
    public int getLevel() {
        return level;
    }
}
