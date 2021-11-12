package game.services;

import game.entity.RaitingEntity;


public interface RatingService {
    void setRating(RaitingEntity raitingEntity) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
    void reset() throws RatingException;


//    void setRating(Rating rating) throws RatingException;
//    int getAverageRating(String game) throws RatingException;
//    int getRating(String game, String player) throws RatingException;
//    void reset() throws RatingException;
}
