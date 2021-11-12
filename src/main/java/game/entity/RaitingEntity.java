package game.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class RaitingEntity {

    @Id
    private String name;
    private int rate;
    private String game="Maze";
    private LocalDateTime ratedOn= LocalDateTime.now();

    public RaitingEntity(){}

    public RaitingEntity(String name){
        this.name=name;
    }

    public RaitingEntity(String name,int rate){
        this.name=name;
        this.rate=rate;
    }

    public RaitingEntity(String name,String game,int rate){
        this.name=name;
        this.rate=rate;
        this.game=game;
    }


    @Override
    public String toString() {
        return "RaitingEntity{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }

    public LocalDateTime getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(LocalDateTime ratedOn) {
        this.ratedOn = ratedOn;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
