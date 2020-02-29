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
  public boolean isOnALine(Balle balle){//la classe Balle n'est pas encore sur le git on utilisera donc une position pour l'instant
    for(Border border : this.borders){
      if(border.isOnTheLine(balle)==true){
        System.out.println("test");
        return border;
      }
    }
    return null;
  }
}
