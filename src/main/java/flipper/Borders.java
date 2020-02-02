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
  public boolean isOnALine(Position pos){//la classe Balle n'est pas encore sur le git on utilisera donc une position pour l'instant
    for(Border border : this.borders){
      if(border.isOnTheLine(pos)==true)return true;
    }
    return false;
  }
}
