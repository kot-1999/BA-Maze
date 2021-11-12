package game.server;

import game.server.controller.UserController;
import game.services.*;
import game.storage.Storage;
import game.storage.StorageJPA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@Configuration
@EntityScan("game")
public class Server {

    public static void main(String[] args){
        SpringApplication.run(Server.class);
    }


    @Bean
    public Storage storage(){
        return StorageJPA.connect();
    }

    @Bean
    public ScoreServiceJPA scoreServiceJPA(){
        return new ScoreServiceJPA();
    }

    @Bean
    public RatingService raitingService(){
        return new RatingServiceJPA();
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }

    @Bean
    public PlayerService playerService(){
        return new PlayerServiceJPA();
    }
}
