import java.util.ArrayList;
public class Collision{

  public ArrayList<Border> colBorder(Borders b1, Balle b2){
    ArrayList<Border> stock = new ArrayList<Border>();
    for(Border a : b1.getBorders()){
      if(a.isOnTheLine(b2.getPos())){
        stock.add(a);
      }
    }
    return stock;
  }

  //http://ressources.univ-lemans.fr/AccesLibre/UM/Pedago/physique/02/meca/rebonds.html
}
