public class Balle{
  private Position pos;
  public Position getPos(){
    return pos;
  }
  private double r;//en cm
  public double getR(){
    return r;
  }
  private double m;//en kg
  public double getM(){
    return m;
  }
  public Balle(Position pos,double r,double m){
    this.pos=pos;
    this.r=r;
    this.m=m;
  }
  
}
