package flipper;
import moteur_physique.*;
import java.util.ArrayList;
public class Borders{
  public Borders(){}
  private ArrayList<Border> borders=new ArrayList<Border>();
  public void addBorder(Border b){
    borders.add(b);
  }
  public ArrayList<Border> getBorders(){
    return borders;
  }
  /**
   *  Regarde pour toute les border si il y a collision avec la balle et prend la shape la plus proche
   * @return Border
   */
  public Border isOnALine(Balle balle){//la classe Balle n'est pas encore sur le git on utilisera donc une position pour l'instant
    double dist=-1;
    Border b=null;
    for(Border border : this.borders){
      if(border.collision(balle)==true){
        if(dist==-1||dist>border.distance(balle)){
          dist=border.distance(balle);
          b=border;
        }
      }
    }
    return b;
  }
  public boolean endGamex(Balle balle){// Teste si la partie est finie (zone en bas des flips touchée)
    for(Border txd : this.borders){
      if(txd.collision(balle)==true){
        return true;
      }
    }
    return false;
  }
}
