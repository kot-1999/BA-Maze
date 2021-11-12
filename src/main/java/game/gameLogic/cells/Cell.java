package game.gameLogic.cells;

import game.gameLogic.characters.Player;

import java.util.HashSet;

public abstract class Cell {
    private final HashSet<CellSide> sides;

    //package protected
    Cell(){
        sides=new HashSet<>();
        sides.add(CellSide.UP);
        sides.add(CellSide.LEFT);
        sides.add(CellSide.BOTTOM);
        sides.add(CellSide.RIGHT);
    }

    public void deleteWall(CellSide cs){
        sides.remove(cs);
    }

    public boolean hasWall(CellSide cs){
        return sides.contains(cs);
    }


    public boolean hasItem(){
        return false;
    }
    public abstract void collectItem(Player player);

}
