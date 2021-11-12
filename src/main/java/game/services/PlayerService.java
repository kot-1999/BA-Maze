package game.services;

import game.entity.PlayerEntity;

public interface PlayerService {

    boolean isRegistered(PlayerEntity playerEntity) ;
    void validate(PlayerEntity playerEntity) throws PlayerExeption;
    void add(PlayerEntity playerEntity) throws PlayerExeption;
    PlayerEntity getPlayer(String name) throws PlayerExeption;



    PlayerEntity autorize(PlayerEntity playerEntity) throws PlayerExeption;


}
