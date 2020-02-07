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
  private double v;//correspond a la vitesse initiale
  private double a;//correspond a l'acceleration, pour l'instant c'est seulement la gravite
  private double t;
  public Balle(Position pos,double r,double m){
    this.pos=pos;
    this.r=r;
    this.m=m;
    this.v=0;
    this.a=10;
    this.t=17*Math.pow(10,-2);
  }
  public Position gravity(){
    this.v=v+this.a*t;
    double y=pos.getY()+v*t;
    if(y>=800)y=0;
    pos=new Position(pos.getX(),y);
    return pos;
  }
}
