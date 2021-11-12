package game.server.webService;

import game.services.PlayerExeption;
import game.storage.CommentForm;
import game.storage.InfoForm;
import game.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/game")
public class StorageServiceServerREST {
    @Autowired
    private Storage storage;


    @RequestMapping( "/player")
    public InfoForm autorizePlayer(@RequestBody InfoForm infoForm) throws PlayerExeption {
        return  storage.autorizePlayer(infoForm);
    }

    @PostMapping( "/updatescore")
    public void updateScore(@RequestBody InfoForm infoForm){
        storage.updateScore(infoForm);
    }

    @PostMapping( "/insertcomment")
    void insertComment(@RequestBody CommentForm commentForm){
        storage.insertComment(commentForm);
    }

    //http://localhost:8080/api/score/game
    @RequestMapping(value = "/raiting/{id}")
    public List<InfoForm> getRaiting(@PathVariable(name = "id") int num) {
        return storage.getRaiting(num);
    }

    @RequestMapping(value = "/comments")
    List<CommentForm> getComments(){
        return storage.getComments();
    }





}
