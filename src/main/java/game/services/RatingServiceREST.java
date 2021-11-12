package game.services;

import game.entity.RaitingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RatingServiceREST implements RatingService {

    private String url="http://localhost:8080/api/rating";
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void setRating(RaitingEntity raitingEntity) {
        restTemplate.postForEntity(url+"/setRating",raitingEntity,RaitingEntity.class);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        try{
            return restTemplate.getForEntity(url+"/"+game+"/getAverageRating",RaitingEntity.class).getBody().getRate();
        }catch (Exception e){
            throw new RatingException(e.getMessage());
        }
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        try{
            return restTemplate.getForEntity(url+"/"+game+"/"+player+"/getRating",Integer.class).getBody();
        }catch (Exception e){
            throw new RatingException("Not rated");
        }

    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported method");
    }
}
