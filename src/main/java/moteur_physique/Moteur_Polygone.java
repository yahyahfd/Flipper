package moteur_physique;
import java.util.ArrayList;
public class Moteur_Polygone extends moteurShape{
  //Nous utiliserons que des Quadrilatere inscriptible (les quatres points font partie d'un meme cercle)
  private ArrayList<Position> pos=new ArrayList<Position>();
  public void addPos(Position p){
    pos.add(p);
  }
  public ArrayList<Position> getPos(){
    return pos;
  }
  public Moteur_Polygone(double rebond, ArrayList<Position> pos){
    this.rebond=rebond;
    this.pos=pos;
  }
  public Moteur_Polygone(double rebond){
    this.rebond=rebond;
  }
  public Border isInTheShape(Balle balle){
    ArrayList<Border> borders=new ArrayList<Border>();
    for(int i=0;i<pos.size()-1;i++){
      borders.add(new Border(pos.get(i),pos.get(i+1),rebond));
    }
    borders.add(new Border(pos.get(pos.size()-1),pos.get(0),rebond));
    Border b=null;
    double dist=-1;
    for(Border border : borders){
      if(border.isOnTheLine(balle)==true){
        if(dist==-1||dist>balle.getPos().distance(border.intersection(balle))){
          dist=balle.getPos().distance(border.intersection(balle));
          b=border;
        }
      }
    }
    return b;
  }

  public Border isSliding(Balle balle){
    ArrayList<Border> borders=new ArrayList<Border>();
    for(int i=0;i<pos.size()-1;i++){
      borders.add(new Border(pos.get(i),pos.get(i+1),rebond));
    }
    borders.add(new Border(pos.get(pos.size()-1),pos.get(0),rebond));
    for(Border border : borders){
      if(border.isSliding(balle)==true){
        return border;
      }
    }
    return null;
  }

  public Double[] getAllPosition(){//Double[] est utilise par javafx
    Double[] d=new Double[pos.size()*2];
    int index=0;
    for(Position p:pos){
      d[index]=Double.valueOf(p.getX());
      index+=1;
      d[index]=Double.valueOf(p.getY());
      index+=1;
    }
    return d;
  }

  public double getSlopeOfBorder(int border){
    if(border>pos.size()-1)return 2;//-1<=slope<=1 donc 2 veux simplement dire pas possible
    if(border==pos.size()-1){
      return (pos.get(0).getY()-pos.get(border).getY())/(pos.get(0).getX()-pos.get(border).getX());
    }
    return (pos.get(border+1).getY()-pos.get(border).getY())/(pos.get(border+1).getX()-pos.get(border).getX());
  }
  public Position getBorderCenter(int border){
    if(border>pos.size()-1)return null;
    if(border==pos.size()-1){
      return new Position((pos.get(0).getX()+pos.get(border).getX())/2,(pos.get(0).getY()+pos.get(border).getY())/2);
    }
    return new Position((pos.get(border+1).getX()+pos.get(border).getX())/2,(pos.get(border+1).getY()+pos.get(border).getY())/2);
  }

  public double getArea(){//simple formule qui marche pour tout les polygones
    double sum=0;
    for(int i=0;i<pos.size()-1;i++){
      sum=Math.abs(sum+(pos.get(i).getX()*pos.get(i+1).getY())-(pos.get(i).getY()*pos.get(i+1).getX()));
    }
    sum= Math.abs(sum+(pos.get(0).getX()*pos.get(pos.size()-1).getY())-(pos.get(0).getY()*pos.get(pos.size()-1).getX()));
    return Math.abs(sum/2);
  }
  public double getBorderLength(int border){
    if(border>pos.size()-1)return 0;
    if(border==pos.size()-1){
      return pos.get(0).distance(pos.get(border));
    }
    return pos.get(border+1).distance(pos.get(border));
  }
}
