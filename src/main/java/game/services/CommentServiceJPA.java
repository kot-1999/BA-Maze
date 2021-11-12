package game.services;

import game.entity.CommentEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CommentServiceJPA implements CommentService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addComment(CommentEntity commentEntity) throws CommentException{
        entityManager.persist(commentEntity);

    }

    @Override
    public List<CommentEntity> getComments(String game) throws CommentException{
        return entityManager.createQuery("select s from CommentEntity s where s.game=:game").setParameter("game", game).getResultList();

    }

    @Override
    public void reset() {
        entityManager.createNativeQuery("delete from CommentEntity").executeUpdate();
    }
}
