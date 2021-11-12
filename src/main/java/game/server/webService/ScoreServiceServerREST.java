package game.server.webService;


import game.entity.ScoreEntity;
import game.services.ScoreException;
import game.services.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceServerREST {
    @Autowired
    private ScoreService scoreService;



    @PostMapping( "/addScore")
    public void addScore(@RequestBody ScoreEntity scoreEntity){
        scoreService.addScore(scoreEntity);
    }

    @GetMapping(value = "/{game}/getTopScores")
    public List<ScoreEntity> getTopScores(@PathVariable(name = "game") String game){
        return scoreService.getTopScores(game);
    }

    @GetMapping(value = "/{game}/{player}/getHighestScore")
    public int getHighestScore(@PathVariable(name = "game") String game,@PathVariable(name = "player") String player) throws ScoreException {
        return scoreService.getHighestScore(game,player);
    }


}
