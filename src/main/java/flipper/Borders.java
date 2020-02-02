import java.util.ArrayList;
//cette clase sera plutot dans le datamodel et n'a pas grand chose a voir avec le moteur graphique
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
