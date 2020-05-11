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
  /**
   *  Regarde pour toute les shapes si il y a collision avec la balle et prend la shape la plus proche
   * @return Border
   */
  public Border isOnALine(Balle balle){
    Border b=null;
    double dist=-1;
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
