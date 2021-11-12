package game.server.webService;


import game.entity.PlayerEntity;
import game.services.PlayerExeption;
import game.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerServiceServerREST {

    @Autowired
    private PlayerService playerService;



    @PostMapping( "/autorize")
    public PlayerEntity autorize(@RequestBody PlayerEntity playerEntity) throws PlayerExeption {
        return playerService.autorize(playerEntity);
    }


    @RequestMapping( "/isRegistered")
    public boolean isRegistered(@RequestBody PlayerEntity playerEntity)  {
        return playerService.isRegistered(playerEntity);
    }

    @PostMapping( "/validate")
    public void validate(@RequestBody PlayerEntity playerEntity) throws PlayerExeption {
        playerService.validate(playerEntity);
    }

    @PostMapping( "/add")
    public void add(@RequestBody PlayerEntity playerEntity) throws PlayerExeption {
        playerService.add(playerEntity);
    }

    @GetMapping(value = "/getPlayer/{player}")
    public PlayerEntity getPlayer(@PathVariable(name = "player") String name) throws PlayerExeption {
        return playerService.getPlayer(name);
    }
}
