package game.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

public class ScoreEntity {


    @Id
    @GeneratedValue
    private int Id;
    private String name;
    private String game;
    private LocalDateTime playedOn = LocalDateTime.now();
    private int score;
    private LocalDateTime date = LocalDateTime.now();


    public ScoreEntity(String name, int score,String game){
        this.name=name;
        this.score=score;
        this.game=game;
    }
    public ScoreEntity(){}



    @Override
    public String toString() {
        return "ScoreEntity{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDateTime getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(LocalDateTime playedOn) {
        this.playedOn = playedOn;
    }
}

