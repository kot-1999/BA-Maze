package game.gameLogic.cells;

import game.gameLogic.characters.Player;

public class CellWithCoin extends Cell{
    private boolean coin=true;

    public CellWithCoin(){
        super();
    }




    public boolean hasItem(){
        return coin;
    }

    @Override
    public void collectItem(Player player){
        if(coin==true && player!=null){
            player.increaseScore(1);
            coin=false;
        }
    }

}
