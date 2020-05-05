package moteur_physique;
import java.util.*;
import java.util.ArrayList;
public class RandomShape extends moteurShape{
  private Moteur_Polygone q;
  private ArrayList<moteurEllipse> e=new ArrayList<moteurEllipse>();//on aura des demis cercle a chaque bordure pour arrondire les bords, si on veux un bord droit le cercle est donc null
  //Prenons un Quadrilatere ABCD le cercle d'indice 0 est celui entre AB etc
  public ArrayList<moteurEllipse> getE(){
    return e;
  }
  private int shapeScore;
  public int getShapeScore(){
    return this.shapeScore;
  }
  public void setShapeScore(int bs){
    this.shapeScore = bs;
  }
  public RandomShape(Moteur_Polygone q){
    this.q=q;
    this.shapeScore = q.getPolyScore();
    for(Position pos:q.getPos()){
      e.add(null);
    }
  }

  public void addCircle(double d,int border){
    if(border>q.getPos().size())return;
    if(d<q.getBorderLength(border)){//major et minor axe inversée si d > taille de la border
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
    ArrayList<Border> borders=new ArrayList<Border>();
    double dist=-1;
    for(moteurEllipse el:e){
      if(el!=null){
        Border be=el.isInTheShape(balle);
        if(be!=null)borders.add(be);
      }
    }
    Border be=q.isInTheShape(balle);
    if(be!=null)borders.add(be);
    Border b=null;
    for(Border border : borders){
      if(border.collision(balle)==true){
        if(dist==-1||dist>border.distance(balle)){
          dist=border.distance(balle);
          b=border;
          b.setScoringTrue();
          b.setBorderScore(this.getShapeScore());

        }
      }
    }
    return b;
  }

  public Border isSliding(Balle balle){
    return q.isSliding(balle);
  }
}
