package flipper;
import java.util.ArrayList;
public class Borders{
  public Borders(){}
  private static ArrayList<Border> borders=new ArrayList<Border>();
  public void addBorder(Border b){
    borders.add(b);
  }
  public ArrayList<Border> getBorders(){
    return borders;
  }
  public Border isOnALine(Balle balle){//la classe Balle n'est pas encore sur le git on utilisera donc une position pour l'instant
    double dist=-1;
    Border b=null;
    for(Border border : this.borders){
      if(border.isOnTheLine(balle)==true){
        if(dist==-1||dist>balle.getPos().distance(border.intersection(balle))){
          dist=balle.getPos().distance(border.intersection(balle));
          b=border;
        }
      }
    }
    return b;
  }
}
