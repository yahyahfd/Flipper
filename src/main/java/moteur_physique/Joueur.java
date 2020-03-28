package moteur_physique;
public class Joueur{
  private String pseudo;
  public String getPseudo(){
    return this.pseudo;
  }
  private int score;
  public int getScore(){
    return this.score;
  }
  public void addScore(int s){
    this.score += s;
  }

  public Joueur(String ps){
    this.pseudo = ps;
    this.score = 0;
  }
}
