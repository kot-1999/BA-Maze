package game.services;

import game.entity.ScoreEntity;

import java.util.List;

public interface ScoreService {
    void addScore(ScoreEntity scoreEntity) throws ScoreException;
    List<ScoreEntity> getTopScores(String game) throws ScoreException;
    int getHighestScore(String game,String player) throws ScoreException;
    void reset() throws ScoreException;

}
