package game.gameLogic.coridorBuilder;

import game.gameLogic.cells.Cell;
import game.gameLogic.cells.CellSide;

import java.util.ArrayList;

public class HuntAndKillCoridorBuilder extends CoridorBuilder{
    private final ArrayList<Cell> visitedCells ;

    public HuntAndKillCoridorBuilder(){
        visitedCells = new ArrayList<>();
    }
    private int x,y;
    private CellSide side;
    @Override
    public void build() {
        if(maze==null)
            return;
        Cell currCell= maze.getCell(0,0);


        while (true){
            if(!hasNeighbour(currCell)) {
                visitedCells.add(currCell);
                currCell = getCurr();
            }

            if (currCell==null)
                break;
            Cell neighbour = getNotVisitedNeighbour(currCell);

            System.out.println("curr"+maze.getCellY(currCell)+" "+maze.getCellX(currCell));
            System.out.println("neig"+maze.getCellY(neighbour)+" "+maze.getCellX(neighbour));
            currCell.deleteWall(side);
            neighbour.deleteWall(side.getOposite());
            visitedCells.add(currCell);
            currCell=neighbour;

        }


    }
    private Cell getNotVisitedNeighbour(Cell currCell){
        Cell neighbour;

        do {
            x = maze.getCellX(currCell);
            y = maze.getCellY(currCell);
            side = CellSide.getRandomSide();
            switch (side) {
                case UP -> y--;
                case LEFT -> x--;
                case BOTTOM -> y++;
                case RIGHT -> x++;
            }
            neighbour = maze.getCell(x, y);
        } while (neighbour == null || visitedCells.contains(neighbour));
        return neighbour;
    }



    private Cell getCurr() {
        for(int y=0;y<maze.getHight() ;y++) {
            for (int x = 0; x < maze.getWidth() ; x++)
                if(visitedCells.contains(maze.getCell(x,y)) && hasNeighbour(maze.getCell(x,y))) {
                    this.x=x;
                    this.y=y;
                    return maze.getCell(x, y);
                }

        }
        return null;

    }

    private boolean hasNeighbour(Cell c){
        int x=maze.getCellX(c);
        int y=maze.getCellY(c);

        if(maze.getCell(x+1,y)!=null && !visitedCells.contains(maze.getCell(x + 1, y)) && maze.getCell(x+1,y).hasWall(CellSide.RIGHT))
            return true;
        if(maze.getCell(x-1,y)!=null && !visitedCells.contains(maze.getCell(x - 1, y)) && maze.getCell(x-1,y).hasWall(CellSide.LEFT))
            return true;
        if(maze.getCell(x,y+1)!=null && !visitedCells.contains(maze.getCell(x,y+1)) && maze.getCell(x,y+1).hasWall(CellSide.UP))
            return true;
        return maze.getCell(x, y - 1) != null && !visitedCells.contains(maze.getCell(x, y - 1)) && maze.getCell(x, y - 1).hasWall(CellSide.BOTTOM);
    }
}
