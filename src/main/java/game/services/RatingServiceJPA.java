package game.services;

import game.entity.RaitingEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void setRating(RaitingEntity raitingEntity) {
//        try {
//            entityManager.persist(raitingEntity);
//        }catch (Exception e){
            entityManager.merge(raitingEntity);
//        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {

        return (int)Float.parseFloat(entityManager.createQuery("select avg(r.rate) from RaitingEntity r where r.game=:game").setParameter("game", game).getSingleResult().toString());
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        RaitingEntity result;
        try {
            result=(RaitingEntity)entityManager.createQuery("select s from RaitingEntity s where s.game=:game and s.name=:player").
                    setParameter("game", game).setParameter("player", player).getSingleResult();
        }catch (Exception e){
            throw  new RatingException("Not rated");
        }
        return result.getRate();
    }

    @Override
    public void reset() {
        entityManager.createNativeQuery(" delete from RaitingEntity").executeUpdate();

    }
}
