package game.gameLogic.cellBuilder;

import game.gameLogic.Maze;

public abstract class CellBuilder {

    protected Maze maze;
    protected int endX=0;
    protected int endY=0;

    public abstract void build();
    public void setEndCellPosition(int x, int y) {
        endX=x;
        endY=y;
    }


    public void setMaze(Maze maze) {
        if(maze!=null)
            this.maze=maze;
    }
}
