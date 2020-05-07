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
}
