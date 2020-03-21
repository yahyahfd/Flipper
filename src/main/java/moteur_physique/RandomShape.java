package moteur_physique;
import java.util.*;
public class RandomShape extends moteurShape{
  private Quadrilatere q;//une forme random a comme base un Quadrilatere
  private ArrayList<moteurEllipse> e=new ArrayList<moteurEllipse>();//on aura des demis cercle a chaque extremite pour arrondire les bords, si on veux un bord droit le cercle est donc null
  //Prenons un Quadrilatere ABCD le cercle d'indice 0 est celui entre AB etc
  public ArrayList<moteurEllipse> getE(){
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
      e.add(border,new moteurEllipse(q.getSlopeOfBorder(border),q.getRebond(),q.getBorderLength(border),d,q.getBorderCenter(border)));
    }
    else{
      e.add(border,new moteurEllipse(q.getSlopeOfBorder(border),q.getRebond(),d,q.getBorderLength(border),q.getBorderCenter(border)));
    }
  }
  public double getArea(){
    double a=0;
    for (moteurEllipse el:e) {
      a+=el.getArea()/2;
    }
    return q.getArea()+a;
  }
  public boolean isInTheShape(Position pos){
    if(q.isInTheShape(pos))return true;
    for (moteurEllipse el :e ){
      if(el.isInTheShape(pos))return true;
    }
    return false;
  }

  public ArrayList<moteurEllipse> allEllipse(){
    ArrayList<moteurEllipse> res = new ArrayList<moteurEllipse>();
    for(moteurEllipse t : e){
      if(t!=null){
        res.add(t);
      }
    }
    return res;
  }

  public Border isInTheShape(Balle balle){
    for(moteurEllipse t : this.allEllipse()){
      Position t1 = t.isInTheShape(balle);
      if(t1!=null){
        Vecteur t2 = new Vecteur(t1.getX()-t.getPos().getX(),t1.getY()-t.getPos().getY());
        Vecteur t3 = t2.vectNormUni();
        return new Border(new Position(t1.getX()-t3.getX(),t1.getY()-t3.getY()),new Position(t1.getX()+t3.getX(),t1.getY()+t3.getY()),q.getRebond());
      }
    }
    return q.isInTheShape(balle);
  }
}
