package game.server.controller;



import game.entity.RaitingEntity;
import game.entity.ScoreEntity;
import game.gameLogic.GameState;
import game.gameLogic.Maze;
import game.gameLogic.characters.Player;
import game.gameLogic.controller.GameController;
import game.gameLogic.controller.Key;
import game.gameLogic.controller.SimplyGameController;
import game.gameLogic.coridorBuilder.BacktrackingCoridorBuilder;
import game.gameLogic.coridorBuilder.CoridorBuilder;
import game.gameLogic.coridorBuilder.HuntAndKillCoridorBuilder;
import game.gameLogic.levelBuilder.LevelBuilder;
import game.gameLogic.levelBuilder.SimplyLevelBuilder;
import game.services.CommentService;
import game.services.PlayerService;
import game.services.RatingService;
import game.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/maze")
public class MazeController {
    private LevelBuilder levelBuilder=new SimplyLevelBuilder();
    private Maze maze=levelBuilder.buildNext();
    private Player player=null;

    private GameController gameController=null;
    @Autowired
    private UserController userController;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private PlayerService playerService;

    @RequestMapping
    public String maze(@RequestParam(required = false)String key,Model model){

        player= userController.getPlayer();
        if(gameController==null)
            gameController=new SimplyGameController(maze,player);
        String result="maze";
        if(key!=null)
            result=move(key,model,player);

        printMaze(model);
        return result;
    }


    private String move(String key, Model model, Player player) {
        gameController.step(Key.convertKey(key));
        if(GameState.state==GameState.WIN){
            if(player.getName().equals("Player"))
                return "autorization";
            maze=levelBuilder.generateNewLevel(levelBuilder,player,gameController);
            scoreService.addScore(new ScoreEntity(player.getName(),player.getScore(),"Maze"));
        }
        return "maze";
    }
    private void printMaze(Model model){
        model.addAttribute("level","Level: "+levelBuilder.getLevel());
        model.addAttribute("score","Score: "+player.getScore());
        model.addAttribute("highestScore","Highest Score: "+scoreService.getHighestScore("Maze",player.getName()));
        model.addAttribute("htmlMenu",TemplateShaplones.getMenu());
        model.addAttribute("htmlField", TemplateShaplones.getFieldHtml(maze,player));
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/restart")
    public String restart(Model model){

        levelBuilder=new SimplyLevelBuilder();
        if(player!=null) {
            player.setScore(0);
            maze = LevelBuilder.generateNewLevel(levelBuilder, player, gameController);
        }
        return "redirect:/"+maze(null,model);
    }

    @RequestMapping("/topPlayers")
    public String topPlayers(Model model){
        player= userController.getPlayer();
        model.addAttribute("topPlayers",scoreService.getTopScores("Maze"));
        return "topPlayers";
    }

    @RequestMapping("/backtracking")
    public String backtracking(Model model){
        return changeCoridorBuildingAlgorythm(new BacktrackingCoridorBuilder(),model);
    }

    @RequestMapping("/huntAndKill")
    public String huntAndKill(Model model){
        return changeCoridorBuildingAlgorythm(new HuntAndKillCoridorBuilder(),model);
    }



    private String changeCoridorBuildingAlgorythm(CoridorBuilder coridorBuilder,Model model){
        levelBuilder=new SimplyLevelBuilder();
        levelBuilder.setCoridorBuilder(coridorBuilder);
        if(player!=null) {
            player.setScore(0);
            maze = LevelBuilder.generateNewLevel(levelBuilder, player, gameController);
        }
        return "redirect:/"+maze(null,model);
    }

    @RequestMapping("/rate")
    public String rate(String rating,Model model){
        player= userController.getPlayer();
        if(player==null || player.getName().equals("Player"))
            return "autorization";

        ratingService.setRating(new RaitingEntity(player.getName(),"Maze",Integer.parseInt(rating)));

        return "redirect:/";
    }

    public String getRate(){
        return TemplateShaplones.rating(ratingService,player);
    }

}
