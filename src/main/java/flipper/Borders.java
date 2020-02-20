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

  public static ArrayList<Border> touchedLines(Position pos){
      ArrayList<Border> b = new ArrayList<Border>();
      for(Border border : borders){
        //On teste ici si la position ne dépasse pas une bordure
        //Ajouter plus tard une règle qui fait qu'on ne puisse pas aller au négatif
        if(border.getPosX().getY()<pos.getY() || border.getPosX().getX()<pos.getX()){
          b.add(border);
          System.out.println("Border Y:"+border.getPosX().getY() + "Balle Y:"+ pos.getY());
        }
      }
      return b;
  }


}
