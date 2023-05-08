public class Player {
    private String username;
    private String score;
    private Character champion;

    public Player(String username,String score,Character champion){
        this.username=username;
        this.score=score;
        this.champion=champion;
    }
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}
    public String getScore(){return this.score;}
    public void setScore(String score){this.score=score;}
    public Character getChampion(){return this.champion;}
    public void setChampion(Character champion){this.champion=champion;}
}
