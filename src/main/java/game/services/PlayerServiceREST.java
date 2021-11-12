package game.services;

import game.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;





public class PlayerServiceREST implements PlayerService{
    private String url="http://localhost:8080/api/player";
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public boolean isRegistered(PlayerEntity playerEntity)  {
        throw new UnsupportedOperationException("Not supported method");
    }

    @Override
    public void validate(PlayerEntity playerEntity) throws PlayerExeption {
        throw new UnsupportedOperationException("Not supported method");
    }

    @Override
    public void add(PlayerEntity playerEntity) throws PlayerExeption {
        throw new UnsupportedOperationException("Not supported method");
    }

    @Override
    public PlayerEntity getPlayer(String name) throws PlayerExeption {
        throw new UnsupportedOperationException("Not supported method");
    }

    @Override
    public PlayerEntity autorize(PlayerEntity playerEntity) throws PlayerExeption {
        try {
            playerEntity= restTemplate.postForEntity(url + "/autorize", playerEntity, PlayerEntity.class).getBody();
        }catch (Exception e){
            throw new PlayerExeption();
        }
        return playerEntity;
    }
}
