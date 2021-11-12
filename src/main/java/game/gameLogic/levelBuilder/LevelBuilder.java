package game.gameLogic.levelBuilder;

import game.gameLogic.GameState;
import game.gameLogic.Maze;
import game.gameLogic.characters.Player;
import game.gameLogic.controller.GameController;
import game.gameLogic.coridorBuilder.BacktrackingCoridorBuilder;
import game.gameLogic.coridorBuilder.CoridorBuilder;
import game.gameLogic.coridorBuilder.HuntAndKillCoridorBuilder;
import org.jetbrains.annotations.NotNull;

public abstract class LevelBuilder {
    protected int level=0;
    protected int startX=8;
    protected int startY=10;
    protected CoridorBuilder coridorBuilder =new BacktrackingCoridorBuilder();
    public abstract Maze buildNext();
    public abstract int getLevel();

    public static Maze generateNewLevel(@NotNull LevelBuilder levelBuilder, @NotNull Player player, @NotNull GameController gameController){
        Maze maze=levelBuilder.buildNext();
        player.setPosition(0,0);
        gameController.setMaze(maze);
        GameState.state=GameState.PLAYING;
        return maze;
    }

    public void setCoridorBuilder(@NotNull CoridorBuilder coridorBuilder){
        this.coridorBuilder=coridorBuilder;
    }
}
