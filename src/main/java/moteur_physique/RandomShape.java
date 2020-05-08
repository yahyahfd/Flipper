package moteur_physique;
import java.util.*;
import java.util.ArrayList;
public class RandomShape extends moteurShape{
  private Moteur_Polygone q;
  private ArrayList<Moteur_Polygone_Inscribed> roundedBorder=new ArrayList<Moteur_Polygone_Inscribed>();//on aura des demis cercle a chaque bordure pour arrondire les bords, si on veux un bord droit le cercle est donc null
  public ArrayList<Moteur_Polygone_Inscribed> getRoundedBorder(){
    return roundedBorder;
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
    this.shapeScore=q.getPolyScore();
    for(Position pos:q.getPos()){
      roundedBorder.add(null);
    }
  }

  public void addRoundedBorder(double d,int border){
    if(border>q.getPos().size())return;
    roundedBorder.add(border,new Moteur_Polygone_Inscribed(q,border,10,q.getBorderLength(border)/2,d));
  }
  public double getArea(){
    double a=0;
    for (Moteur_Polygone_Inscribed el:roundedBorder) {
      a+=el.getArea()/2;
    }
    return q.getArea()+a;
  }
  public Border isInTheShape(Balle balle){
    ArrayList<Border> borders=new ArrayList<Border>();
    double dist=-1;
    for(Moteur_Polygone_Inscribed p:roundedBorder){
      if(p!=null){
        Border be=p.isInTheShape(balle);
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
