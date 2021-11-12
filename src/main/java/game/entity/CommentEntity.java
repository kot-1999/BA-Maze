package game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CommentEntity {


    @Id
    @GeneratedValue
    private Long id;
    private String player;
    private String game="Maze";
    private String comment;
    private LocalDateTime commentedOn= LocalDateTime.now();

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public CommentEntity(){}

    public CommentEntity(String player, String comment){
        this.player=player;
        this.comment=comment;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", player='" + player + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public LocalDateTime getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(LocalDateTime commentedOn) {
        this.commentedOn = commentedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
