package game.gameLogic;


import game.gameLogic.characters.Character;
import game.gameLogic.cells.Cell;

import java.util.ArrayList;


public  class Maze {

    private Cell[][] gred;
    private int width,hight;
    private static final int defauldSize=4;
    private ArrayList<Character> characters;

    public Maze(int x, int y){
        gred=new Cell[y][x];
        characters=new ArrayList<>();
        width=x;
        hight=y;
    }
    public static Maze create(){
        return new Maze(defauldSize,defauldSize);
    }
    static Maze create(int x){
        if(x<defauldSize)
            return null;
        return new Maze(x,x);
    }
    public static Maze create(int x, int y){
        if(x<defauldSize || y<defauldSize)
            return null;
        return new Maze(x,y);
    }

    public int getCellY(Cell c){
        for(int y=0;y<hight;y++)
            for (int x=0;x<width;x++)
                if(gred[y][x].equals(c))
                    return y;

        return -1;
    }

    public int getCellX(Cell c){
        for(int y=0;y<hight;y++)
            for (int x=0;x<width;x++)
                if(gred[y][x].equals(c))
                    return x;
        return -1;
    }

    public Cell getCell(int x, int y){
        if(x<width && x>=0 && y<hight && y>=0)
            return gred[y][x];
        else
            return null;
    }

    public void setCell(int x, int y, Cell c){
        if((x<width && y<hight) && c!=null)
            gred[y][x]=c;
    }
    public void addCharacter(Character character){
        if(character!=null)
            characters.add(character);
    }

    public void remoceCharacter(Character character){
        characters.remove(character);
    }


    public boolean hasCharacter(int x,int y){
        for(Character c: characters)
            if(x==c.getXPosition() && y==c.getYPosition())
                return true;

            return false;
    }

    public int getWidth(){
        return width;
    }

    public int getHight(){
        return hight;
    }
}
