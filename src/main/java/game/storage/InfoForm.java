package game.storage;



import javax.persistence.*;

@Entity


public class InfoForm {
    @Id
//    @Column(name="player")
    private String name;
    private String email;

    private int score;

    public InfoForm(String name,String email,int highestScore){

        this.name= name;
        this.email=email;
        this.score= highestScore;
    }

    public InfoForm() {

    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getScore(){
        return score;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setScore(int highestScore){
        this.score=highestScore;
    }

    @Override
    public String toString() {
        return "InfoForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                '}';
    }
}
