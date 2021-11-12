package game;
import game.gameLogic.Game;
import game.gameLogic.Maze;
import game.gameLogic.characters.Player;
import game.gameLogic.controller.SimplyGameController;
import game.gameLogic.levelBuilder.SimplyLevelBuilder;
import game.konsoleApp.KonsoleUI_02;
import game.services.*;
import game.storage.StorageREST;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "games.server.*"))
public class SpringClient {
    public static void main(String[] args){
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
//        SpringApplication.run(SpringClient.class);
    }

    @Bean
    public Game game(){
        return new KonsoleUI_02();
    }

    @Bean
    public CommandLineRunner runner(Game game){
        return  args -> game.play();
    }

    @Bean
    public Player player(){
        return new Player();
    }

    @Bean
    public SimplyLevelBuilder simplyLevelBuilder(){
        return new SimplyLevelBuilder();
    }


    @Bean
    public StorageREST storage(){
        return StorageREST.connect();
    }

    @Bean
    public SimplyGameController simplyController(){
        return new SimplyGameController(maze(),player());
    }

    @Bean
    @Scope("prototype")
    public Maze maze(){
        return simplyLevelBuilder().buildNext();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Primary
    public ScoreService scoreService(){
        return new ScoreServiceREST();
    }

    @Bean
    @Primary
    public RatingService ratingService(){
        return new RatingServiceREST();
    }

    @Bean
    @Primary
    public CommentService commentService(){
        return new CommentServiceREST();
    }

    @Bean
    @Primary
    public PlayerService playerService(){
        return new PlayerServiceREST();
    }
}
