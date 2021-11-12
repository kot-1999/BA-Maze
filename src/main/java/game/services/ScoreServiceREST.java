package game.services;

import game.entity.RaitingEntity;
import game.entity.ScoreEntity;
import game.storage.InfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceREST implements ScoreService{

    private String url="http://localhost:8080/api/score";
    @Autowired
    private RestTemplate restTemplate;



    @Override
    public void addScore(ScoreEntity scoreEntity) {
        restTemplate.postForEntity(url+"/addScore",scoreEntity,ScoreEntity.class);
    }

    @Override
    public List<ScoreEntity> getTopScores(String game) {
        return Arrays.asList(restTemplate.getForEntity(url+"/"+game+"/getTopScores", ScoreEntity[].class).getBody());
    }

    @Override
    public int getHighestScore(String game, String player) throws ScoreException {
        return restTemplate.getForEntity(url+"/"+game+"/"+player+"/getHighestScore", Integer.class).getBody();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported method");
    }
}
