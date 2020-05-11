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

  public Joueur(){
    this.score = 0;
    this.pseudo = "Guest";
  }

  public void setPseudo(String n){
    this.pseudo=n;
  }

  public void setScore(int s){
    this.score=s;
  }
}
