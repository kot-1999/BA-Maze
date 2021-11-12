package game.server.webService;

import game.entity.CommentEntity;
import game.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comment")
public class CommentServiceServerREST {

    @Autowired
    private CommentService commentService;

    @PostMapping( "/addComment")
    public void add(@RequestBody CommentEntity commentEntity){
        commentService.addComment(commentEntity);
    }

    @RequestMapping(value = "{game}/getComments")
    public List<CommentEntity> getComments(@PathVariable(name = "game") String game){
        return commentService.getComments(game);
    }
}
