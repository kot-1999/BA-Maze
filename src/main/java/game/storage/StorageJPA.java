package game.storage;

import game.services.PlayerExeption;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StorageJPA implements Storage{

    @PersistenceContext
    private EntityManager entityManager;


    public StorageJPA(){

    }

    public static StorageJPA connect(){
        StorageJPA result=new StorageJPA();
        return result;
    }

    @Override
    public InfoForm autorizePlayer(InfoForm infoForm) throws PlayerExeption {

        for (InfoForm s:(List<InfoForm>)entityManager.createQuery("select s from InfoForm s").getResultList()) {
            if(s.getName().equals(infoForm.getName())){

                if(s.getEmail().equals(infoForm.getEmail()))
                    return s;
                else
                    throw new PlayerExeption();
            }
        }

        entityManager.persist(infoForm);
        return infoForm;
    }

    @Override
    public void updateScore(InfoForm infoForm) {
        for (InfoForm s:(List<InfoForm>)entityManager.createQuery("select s from InfoForm s").getResultList()) {
            if(s.getName().equals(infoForm.getName())){
                if(infoForm.getScore()>s.getScore())
                    entityManager.merge(infoForm);
                break;

            }
        }


    }

    @Override
    public void insertComment(CommentForm commentForm) {
        entityManager.persist(commentForm);
    }

    @Override
    public List<InfoForm> getRaiting(int numberOfBestPlayers) {
        return entityManager.createQuery("select s from InfoForm s order by s.score desc").setMaxResults(numberOfBestPlayers).getResultList();

    }

    @Override
    public List<CommentForm> getComments() {
        return entityManager.createQuery("select s from CommentForm s").getResultList();
    }

    @Override
    public void refresh() {
        entityManager.createNativeQuery("delete from Info_Form").executeUpdate();
        entityManager.createNativeQuery(" delete from Comment_Form").executeUpdate();
    }
}
