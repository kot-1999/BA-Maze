package game.gameLogic.coridorBuilder;

import game.gameLogic.cells.Cell;
import game.gameLogic.cells.CellSide;
import java.util.ArrayList;

public class BacktrackingCoridorBuilder extends CoridorBuilder {

    private ArrayList<Cell> visitedCells ;



    @Override
    public void build() {
        visitedCells = new ArrayList<>();
        if(maze==null)
            return;
        Cell currCell= maze.getCell(0,0);
        visitedCells.add(currCell);

        while (visitedCells.size()< maze.getHight()* maze.getWidth()){
            for(int i = visitedCells.size()-2; i>=0 && !hasNeighbour(currCell); i--)
                currCell=visitedCells.get(i);
            CellSide side;
            Cell neighbour;
            int x;
            int y;
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
            currCell.deleteWall(side);
            neighbour.deleteWall(side.getOposite());
            currCell=neighbour;
            visitedCells.add(currCell);
        }

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
