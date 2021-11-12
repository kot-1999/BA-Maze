package game.server.controller;


import game.entity.PlayerEntity;
import game.entity.ScoreEntity;
import game.gameLogic.characters.Player;
import game.services.PlayerExeption;
import game.services.PlayerService;
import game.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Access;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserController {
    private Player player=new Player("Player");
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/autorization")
    public String autorization(Model model){
        return "autorization";
    }

    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        PlayerEntity playerEntity=new PlayerEntity(name,password,null,null);
        if(playerService.isRegistered(playerEntity)==false)
            return "/autorization";
        try {
            playerService.validate(playerEntity);
        }catch (PlayerExeption e){
            return "/autorization";
        }

        player.setName(playerEntity.getName());
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(String name,String password,String email,String avatarUrl, Model model){
        PlayerEntity playerEntity=new PlayerEntity(name,password,email,avatarUrl);

        if(playerService.isRegistered(playerEntity)==true)
            return "/autorization";
        try {
            playerService.add(playerEntity);
        }catch (PlayerExeption e){
            return "/autorization";
        }

        player.setName(playerEntity.getName());
        scoreService.addScore(new ScoreEntity(playerEntity.getName(),0,"Maze"));
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String login(){
        player.setName("Player");
        player.setPosition(0,0);
        player.setScore(0);
        player.setHighesScore(0);
        return "redirect:/";
    }
    public boolean isLogged(){
        if(player!=null && player.getName().equals("Player")==false)
            return true;
        return false;
    }
    public Player getPlayer(){
        return player;
    }
}
