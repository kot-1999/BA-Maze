package game.server.webService;

import game.entity.RaitingEntity;
import game.services.RatingException;
import game.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class RatingServiceServerREST {

    @Autowired
    private RatingService ratingService;

    @PostMapping( "/setRating")
    void setRating(@RequestBody RaitingEntity raitingEntity){
        ratingService.setRating(raitingEntity);
    }

    @GetMapping( "{game}/{player}/getRating")
    int getRating(@PathVariable(name = "game") String game,@PathVariable(name = "player") String player) throws RatingException {
        return ratingService.getRating(game,player);
    }

    @GetMapping( "{game}/getAverageRating")
    int getAverageRating(@PathVariable(name = "game") String game) throws RatingException {
        return ratingService.getAverageRating(game);
    }
}
