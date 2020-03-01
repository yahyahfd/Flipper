package flipper;
import java.util.ArrayList;
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
  private Vecteur v;
  private Vecteur a;//correspond a l'acceleration, pour l'instant c'est seulement la gravite
  private double t;//intervalle de temps(equivalent a 60fps)
  private double g=10;//constante de gravité
  public Balle(Position pos,double r,double m){
    this.pos=pos;
    this.r=r;
    this.m=m;
    this.v=new Vecteur(0,0);
    this.a=new Vecteur(0,0);
    this.t=17*Math.pow(10,-2);
    a.setY(g);
  }
  public void gravity(){
    a.setY(g);
    this.v.setY(v.getY()+a.getY()*t);
    if(pos.getY()>=800)pos.setY(0);
    else pos.setY(pos.getY()+v.getY()*t);
  }

  public ArrayList<Position> hitbox(int precision){
    double angle = 2*Math.PI/precision;
    ArrayList<Position> points = new ArrayList<Position>();
    for(int i=1;i<precision;i++){
      points.add(new Position(Math.cos(angle*i), Math.sin(angle*i)));
    }
    return points;
  }
  
  public Position futur(){
    double vy=v.getY()+a.getY()*t;
    double vx=v.getX()+a.getX()*t;
    double x=pos.getX()+v.getX()*t;
    double y=pos.getY()+v.getY()*t;
    return new Position(x,y);
  }
  public void setFutur(Position pos){
    pos.setY(pos.getY()+v.getY()*t);
    pos.setX(pos.getX()+v.getX()*t);
  }
}
