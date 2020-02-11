package flipper;
import java.util.*;
public class RandomShape extends Shape{
  private Quadrilatere q;//une forme random a comme base un Quadrilatere
  private ArrayList<Ellipse> e=new ArrayList<Ellipse>();//on aura des demis cercle a chaque extremite pour arrondire les bords, si on veux un bord droit le cercle est donc null
  //Prenons un Quadrilatere ABCD le cercle d'indice 0 est celui entre AB etc
  public ArrayList<Ellipse> getE(){
    return e;
  }
  public RandomShape(Quadrilatere q){
    this.q=q;
    e.add(null);
    e.add(null);
    e.add(null);
    e.add(null);
  }
  public void addCircle(double d,int border){
    if(border>3)return;
    if(d<q.getBorderLength(border)){
      e.add(border,new Ellipse(q.getSlopeOfBorder(border),q.getRebond(),q.getBorderLength(border),d,q.getBorderCenter(border)));
    }
    else{
      e.add(border,new Ellipse(q.getSlopeOfBorder(border),q.getRebond(),d,q.getBorderLength(border),q.getBorderCenter(border)));
    }
  }
  public double getArea(){
    double a=0;
    for (Ellipse el:e) {
      a+=el.getArea()/2;
    }
    return q.getArea()+a;
  }
  public boolean isInTheShape(Position pos){
    if(q.isInTheShape(pos))return true;
    for (Ellipse el :e ){
      if(el.isInTheShape(pos))return true;
    }
    return false;
  }
}
