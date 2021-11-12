package game.gameLogic.cells;

import game.gameLogic.GameState;
import game.gameLogic.characters.Player;

public class EndCell extends Cell{

    public EndCell(){
        super();
    }

    @Override
    public void collectItem(Player player){
        if(player==null)
            return;

        player.increaseScore(10);
        GameState.state=GameState.WIN;

    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
