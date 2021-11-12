package game.gameLogic.characters;

public abstract class Character {
    private int x,y;
    private int score=0;
    Character(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setPosition(int x,int y) {
        this.x=x;
        this.y=y;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public int getScore(){
        return score;
    }
    public void incrementScore(){
        score++;
    }


}
