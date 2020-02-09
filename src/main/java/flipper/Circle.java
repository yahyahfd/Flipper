package flipper;
public class Circle extends Shape{
  //Cette classe est differente de balle, c'est un objet fixe considerer comme une bordure
  private double r;
  public Circle(double r,double rebond,Position pos){
    this.pos=pos;
    this.rebond=rebond;
    this.r=r;
  }
  public double getArea(){
    return r*r*3;//on simplifie pi a 3
  }
  public boolean isInTheShape(Position p){
    return this.pos.distance(p)<r;//Pythagore
  }
}
