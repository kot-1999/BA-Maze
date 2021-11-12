package game.services;

import game.entity.RaitingEntity;
import game.entity.ScoreEntity;
import game.storage.InfoForm;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;


@Service
@Transactional
public class ScoreServiceJPA implements ScoreService{

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void addScore(ScoreEntity scoreEntity) {
        entityManager.persist(scoreEntity);
    }

    @Override
    public List<ScoreEntity> getTopScores(String game) {
        List<ScoreEntity> result=new LinkedList<>();
        return entityManager.createQuery("select  s from ScoreEntity s  where s.game=:game and s.score = (select max(ss.score) from ScoreEntity ss where ss.name=s.name) order by s.score desc ").setParameter("game",game) .setMaxResults(10).getResultList();

    }

    @Override
    public int getHighestScore(String game, String player)  {
        try {
            return (int) entityManager.createQuery("select max(s.score) from ScoreEntity s where s.game=:game and s.name=:player").
                    setParameter("game", game).setParameter("player", player).getSingleResult();

        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("delete from ScoreEntity").executeUpdate();
    }
}
