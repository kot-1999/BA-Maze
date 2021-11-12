package game.services;

import game.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

import java.util.List;

public class CommentServiceREST implements CommentService{

    private String url="http://localhost:8080/api/comment";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addComment(CommentEntity commentEntity) throws CommentException{
        restTemplate.postForEntity(url+"/addComment",commentEntity,CommentEntity.class);
    }

    @Override
    public List<CommentEntity> getComments(String game) throws CommentException{
        return Arrays.asList(restTemplate.getForEntity(url+"/"+game+"/getComments",CommentEntity[].class).getBody());

    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported method");
    }
}
