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
  public Border isOnALine(Balle balle){
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
  public Border isSliding(Balle balle){
    for(Border border : this.borders){
      if(border.isSliding(balle)==true){
        return border;
      }
    }
    return null;
  }

  public static boolean endGamex(Balle balle){
    Border a1 = new Border(new Position(220,765),new Position(275,775),0.9);
    Border a2 = new Border(new Position(350,765),new Position(294,775),0.9);
    Border a3 = new Border(new Position(275,775),new Position(294,775),0.9);
    ArrayList<Border> endgame = new ArrayList<Border>();
    endgame.add(a1);
    endgame.add(a2);
    endgame.add(a3);
    for(Border txd : endgame){
      if(txd.collision(balle)==true){
        return true;
      }
    }
    return false;
  }
}
