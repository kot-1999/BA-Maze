package game.services;

import game.entity.PlayerEntity;
import game.entity.RaitingEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class PlayerServiceJPA implements PlayerService{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isRegistered(PlayerEntity playerEntity)  {
        try{
            return getPlayer(playerEntity.getName())!=null;
        }catch (PlayerExeption e){
            return false;
        }
    }

    @Override
    public void validate(PlayerEntity playerEntity) throws PlayerExeption {
        try {
            PlayerEntity temp = getPlayer(playerEntity.getName());
            if(temp.getPassword().equals(playerEntity.getPassword())==false)
                throw new PlayerExeption();
        }catch (Exception e){
            throw new PlayerExeption();
        }

    }

    @Override
    public void add(PlayerEntity playerEntity) throws PlayerExeption {
        try{
            entityManager.persist(playerEntity);
        }catch (Exception e){
            throw new PlayerExeption();
        }
    }

    @Override
    public PlayerEntity getPlayer(String name) throws PlayerExeption {
        try {
            return (PlayerEntity) entityManager.createQuery("select s from PlayerEntity s where s.name=:name").setParameter("name", name).getSingleResult();
        }catch (Exception e){
            throw new PlayerExeption();
        }
    }

    @Override
    public PlayerEntity autorize(PlayerEntity playerEntity) throws PlayerExeption {
        for (PlayerEntity s:(List<PlayerEntity>)entityManager.createQuery("select s from PlayerEntity s").getResultList()) {
            if(s.getName().equals(playerEntity.getName())){
                if(s.getEmail().equals(playerEntity.getEmail()))
                    return s;
                else
                    throw new PlayerExeption();
            }
        }

        entityManager.persist(playerEntity);
        return playerEntity;
    }
}
