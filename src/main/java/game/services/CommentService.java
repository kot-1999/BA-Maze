package game.services;

import game.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    void addComment(CommentEntity commentEntity) throws CommentException;
    List<CommentEntity> getComments(String game) throws CommentException;
    void reset() throws CommentException;


}
