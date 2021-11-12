package game.gameLogic.controller;

import game.gameLogic.characters.Character;
import game.gameLogic.Maze;
import game.gameLogic.cells.CellSide;
import game.gameLogic.characters.Player;
import org.jetbrains.annotations.NotNull;
public class SimplyGameController extends GameController {

    public SimplyGameController(@NotNull Maze maze, @NotNull Character character) {
        super(maze, character);
    }




    @Override
    public void step(Key key) {
        int x=character.getXPosition();
        int y=character.getYPosition();
        switch (key){
            case UP:
                if(maze.getCell(x,y).hasWall(CellSide.UP)==false)
                    y--;
                break;
            case LEFT:
                if(maze.getCell(x,y).hasWall(CellSide.LEFT)==false)
                    x--;
                break;
            case DOWN:
                if(maze.getCell(x,y).hasWall(CellSide.BOTTOM)==false)
                    y++;
                break;
            case RIGHT:
                if(maze.getCell(x,y).hasWall(CellSide.RIGHT)==false)
                    x++;
                break;
            case ENTER:
                if(character instanceof Player)
                maze.getCell(x,y).collectItem((Player)character);
                break;

        }
        character.setPosition(x,y);
    }
}
