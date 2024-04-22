package Core;

public class Player {

    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }
    

    public Player (String name, int score) {
        this.name=name;
        this.score=score;
    }

    @Override
    public String toString()
    {
       return ("Name: " + name + " || " + "Score: " + score);
    }

    
}
