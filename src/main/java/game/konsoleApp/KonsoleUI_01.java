package game.konsoleApp;

import game.gameLogic.Game;
import game.gameLogic.GameState;
import game.gameLogic.Maze;
import game.gameLogic.cells.Cell;
import game.gameLogic.cells.CellSide;
import game.gameLogic.cells.CellWithCoin;
import game.gameLogic.characters.Player;
import game.services.PlayerExeption;
import game.gameLogic.controller.Key;
import game.gameLogic.controller.SimplyGameController;
import game.gameLogic.cells.EndCell;
import game.gameLogic.levelBuilder.SimplyLevelBuilder;
import game.storage.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KonsoleUI_01 implements Game {

    public static GameState gameState=GameState.PLAYING;

    @Autowired
    private SimplyLevelBuilder slb;
    @Autowired
    private Player player;
    @Autowired
    private Storage storage;
    @Autowired
    private SimplyGameController sc;
    @Autowired
    private Maze maze;

    public  void play() {
        maze.addCharacter(player);

        setPlayer();


        while(GameState.state!=GameState.EXIT) {
            printMenu();
            Key key=Key.getKey();
            switch (key){
                case ENTER:
                    GameState.state=GameState.PLAYING;
                    playGame();
                    storage.updateScore(new InfoForm(player.getName(), player.getEmail(),player.getScore()));
                    break;
                case EXIT:
                    GameState.state=GameState.EXIT;
                    break;
                case R_KEY:
                    System.out.println("Best results:");
                    int i=1;
                    for (var v: storage.getRaiting(10)) {
                        System.out.println(i++ + ". "+v.getName()+" "+v.getScore());
                    }

                    break;
                case C_KEY:
                    do {
                        for (var v: storage.getComments()) {
                            System.out.println("PLAYER: "+v.getPlayer()+"\n\t"+v.getComment());
                        }

                        System.out.println("<f> to add comment | <c> exit to menu");
                        key=Key.getKey();
                        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
                        if(key==Key.ENTER) {
                            try {
                                storage.insertComment(new CommentForm(player.getName(),buf.readLine()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }while (key!=Key.C_KEY);
                    break;
            }
        }

    }

    private void playGame(){
        printMaze();
        for (Key k; GameState.state == GameState.PLAYING; ) {
            while (GameState.state == GameState.PLAYING) {
                k = Key.getKey();
                if (k == Key.NOTAKEY)
                    continue;

                switch (k) {
                    case EXIT:
                        GameState.state = GameState.EXIT;
                        break;
                    case ENTER:
                        maze.getCell(player.getXPosition(), player.getYPosition()).collectItem(player);
                        break;
                    case C_KEY:
                        GameState.state = GameState.END;
                        break;
                    default:
                        sc.step(k);
                        break;
                }
                printMaze();
            }
            if (GameState.state == GameState.WIN) {
                maze = slb.buildNext();
                maze.addCharacter(player);
                player.setPosition(0, 0);
                sc.setMaze(maze);
                GameState.state = GameState.PLAYING;
                printMaze();
            }
        }
    }

    private void setPlayer(){
        player.controlledSetName();
        player.controlledSetEmail();
        try {
            InfoForm infoForm=new InfoForm(player.getName(), player.getEmail(), player.getHighestScore());
            infoForm= storage.autorizePlayer(infoForm);
            player.setHighesScore(infoForm.getScore());
        } catch (PlayerExeption e) {
            System.out.println("Wrong name or email");
            setPlayer();
        }
    }

    private void printMenu(){
        System.out.println("Press: | <f> to play | <r> to see players raiting | <c> to see comments");
    }

    private void printMaze(){
        System.out.println("LEVEL: "+slb.getLevel()+"| SCORE: "+ player.getScore()+"| HIGHEST SCORE: "+player.getHighestScore());
        for(int y = 0; y< maze.getHight(); y++) {
            Cell cell;
            for (int x = 0; x < maze.getWidth(); x++) {

                cell= maze.getCell(x, y);
                if(cell.hasWall(CellSide.UP))
                    System.out.print("###");
                else
                    System.out.print("#  ");
            }
            System.out.println("#");
            for (int x = 0; x < maze.getWidth(); x++) {
                cell=maze.getCell(x, y);
                if(cell.hasWall(CellSide.LEFT))
                    System.out.print("#");
                else
                    System.out.print(" ");
                if(maze.hasCharacter(x,y))
                    System.out.print("OO");
                else if(cell instanceof EndCell)
                    System.out.print("XX");
                else if(cell instanceof CellWithCoin && cell.hasItem())
                    System.out.print("CC");
                else System.out.print("  ");
            }

            System.out.println("#");
        }
        for (int x = 0; x < maze.getWidth(); x++)
            System.out.print("###");
        System.out.println("#");
        System.out.println("Press <w> <a> <s> <d> to play | <f> to enter | <c> to exit to menu | <q> to exit");
    }


}
