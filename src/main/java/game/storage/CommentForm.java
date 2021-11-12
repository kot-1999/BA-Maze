package game.storage;


import javax.persistence.*;

@Entity
public class CommentForm {


    @Id
    @GeneratedValue
    private Long id;
    private String player;
    private String comment;


    public CommentForm(String name, String comment){
        this.player =name;
        this.comment=comment;
    }

    public CommentForm() {

    }

    public String getPlayer(){
        return player;
    }
    public String getComment(){
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setPlayer(String name) {
        this.player = name;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "name='" + player + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
