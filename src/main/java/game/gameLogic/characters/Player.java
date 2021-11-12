package game.gameLogic.characters;

//import javax.persistence.Entity;
//import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Entity
public class Player extends Character {
    private int score=0;

    private int highestScore=0;
    //@Id
    private String name=null;

    private String email=null;

    public Player(String name) {
        super(0, 0);
        this.name = name;
    }

    private ArrayList<String> comments=new ArrayList<>();
    private int index=0;
    public Player(){
        super(0,0);
    }
    public Player(int x){
        super(x,x);
    }
    public Player(int x, int y){
        super(x,y);

    }

    public static final Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NAME = Pattern.compile("^[A-Z0-9._%+-]", Pattern.CASE_INSENSITIVE);

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL.matcher(emailStr);
        return matcher.find();
    }

    private  boolean validateName(String emailStr) {
        Matcher matcher = VALID_NAME.matcher(emailStr);
        return matcher.find();
    }

    private BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void controlledSetName(){
        do{
            System.out.print("Enter your name: ");
            try{
                name=buf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(validateName(name)!=true);
    }

    public void controlledSetEmail(){
        do{
            System.out.print("Enter your email: ");

            try{
                email=buf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }while(validateEmail(email)!=true);
    }

    public void addComment(String comment){
        if(comment!=null)
            comments.add(comment);
    }

    public String getNextComment(){
        return comments.get(index++);
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
    public void refreshScore(){
        this.score=0;
    }
    public void increaseScore(int increment){
        score+=increment;
    }

    public void setHighesScore(int highesScore){
        this.highestScore=highesScore;
    }

    public int getHighestScore() {
        return highestScore;
    }
    public  void setScore(int score){
        this.score=score;
    }
    public int getScore(){
        return score;
    }

}

