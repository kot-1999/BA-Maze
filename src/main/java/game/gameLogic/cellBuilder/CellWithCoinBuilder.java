package game.gameLogic.cellBuilder;

import game.gameLogic.cells.CellWithCoin;
import game.gameLogic.cells.CommonCell;
import game.gameLogic.cells.EndCell;

import java.util.Random;

public class CellWithCoinBuilder extends CellBuilder {
    private int coinsNumber=5;



    @Override
    public void build() {
        if(maze==null)
            return;
        int width= maze.getWidth();
        int hight=maze.getHight();
        int coins=coinsNumber;
        if((endX>=0 && endX<width-1 && endY>=0 && endY<hight-1))
            endY=endX=0;
        Random r =new Random();
        while(coins>0){
            int x= r.nextInt(maze.getWidth());
            int y=r.nextInt(maze.getHight());
            maze.setCell(x,y,new CellWithCoin());
            coins--;
        }
        maze.setCell(endX,endY,new EndCell());
        for(int x=0;x<width;x++)
            for (int y=0;y<hight;y++)
                if(maze.getCell(x,y)==null)
                    maze.setCell(x,y,new CommonCell());
    }

    public void setCoinsNumber(int coinsNumber){
        this.coinsNumber=coinsNumber;
    }

}
