package flipper;
import moteur_physique.*;
import java.util.ArrayList;
public class Shapes{
  public Shapes(){}
  private static ArrayList<moteurShape> shapes=new ArrayList<moteurShape>();
  public void addShape(moteurShape s){
    shapes.add(s);
  }
  public ArrayList<moteurShape> getShapes(){
    return shapes;
  }
  public Border isOnALine(Balle balle){//la classe Balle n'est pas encore sur le git on utilisera donc une position pour l'instant
    double dist=-1;
    Border b=null;
    for(moteurShape shape : this.shapes){
      if(shape.isInTheShape(balle)!=null){
        if(dist==-1||dist>=shape.isInTheShape(balle).distance(balle)){
          dist=shape.isInTheShape(balle).distance(balle);
          b=shape.isInTheShape(balle);
        }
      }
    }
    return b;
  }
}
