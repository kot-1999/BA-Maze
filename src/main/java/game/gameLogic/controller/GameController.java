package game.gameLogic.controller;

import game.gameLogic.Maze;
import org.jetbrains.annotations.NotNull;
import game.gameLogic.characters.Character;

public abstract class GameController {
    protected Maze maze;
    protected Character character;

    GameController(@NotNull Maze maze, @NotNull Character character){
        this.maze=maze;
        this.character=character;
    }

    public void setMaze(@NotNull Maze maze){
        this.maze=maze;
    }
    public abstract void step(Key key);
}
