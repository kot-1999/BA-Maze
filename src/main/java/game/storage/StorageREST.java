package game.storage;

import game.services.PlayerExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;



public class StorageREST implements Storage{
    private String url="http://localhost:8080/api/game";
    @Autowired
    private RestTemplate restTemplate;

    private StorageREST(){}
    public static StorageREST connect(){
        return new StorageREST();
    }

    @Override
    public InfoForm autorizePlayer(InfoForm infoForm) throws PlayerExeption {
        try{
            infoForm = restTemplate.postForEntity(url+"/player",infoForm,InfoForm.class).getBody();
        }catch (Exception e){
            throw new PlayerExeption();
        }
        return infoForm;
    }

    @Override
    public void updateScore(InfoForm infoForm) {
        restTemplate.postForEntity(url+"/updatescore",infoForm,InfoForm.class);
    }

    @Override
    public void insertComment(CommentForm commentForm) {
        restTemplate.postForEntity(url+"/insertcomment",commentForm,CommentForm.class);
    }

    @Override
    public List<InfoForm> getRaiting(int numberOfBestPlayers) {
        return Arrays.asList(restTemplate.getForEntity(url+"/raiting/"+numberOfBestPlayers,InfoForm[].class).getBody());
    }

    @Override
    public List<CommentForm> getComments() {
        return Arrays.asList(restTemplate.getForEntity(url+"/comments",CommentForm[].class).getBody());
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported method");
    }
}
