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
    roundedBorder.add(border,new Moteur_Polygone_Inscribed(q,border,20,q.getBorderLength(border)/2,d));
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
    Border ba=q.isInTheShape(balle);
    if(ba!=null){
      int index=q.getBorderIndex(ba);
      if(ba!=null&&roundedBorder.get(index)==null)borders.add(ba);
    }
    Border b=null;
    for(Border border : borders){
      if(dist==-1||dist>border.distance(balle)){
        dist=border.distance(balle);
        b=border;
        b.setBorderScore(this.getShapeScore());
      }
    }
    return b;
  }
}
