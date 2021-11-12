package game.gameLogic.coridorBuilder;

import game.gameLogic.Maze;

public abstract class CoridorBuilder {

    protected Maze maze=null;

    public void setMaze(Maze maze){
        if(maze!=null)
            this.maze=maze;
    }

    public abstract void build();

}
