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

  public Vecteur getV(){
    return v;
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
  public Position futur(){
    double vx = v.getX()+a.getX()*t;
    double vy = v.getY()+a.getY()*t;
    double y = pos.getY()+vy*t;
    double x = pos.getX()+vx*t;
    this.v.setY(vy);
    this.v.setX(vx);
    ArrayList<Border> stock = Borders.touchedLines(new Position(x,y));
    for(Border b : stock){
      if(b.getPosX().getX()<x){
        x=b.getPosX().getX();
      }
      if(b.getPosX().getY()<y){
        y=b.getPosX().getY();
      }
    }
    return new Position(x,y);
  }

  public Position collision(double E){
    double vx = v.getX()+a.getX()*t;
    double vy = E*(Math.sqrt(2*10*this.pos.getY()));
    double y = pos.getY()-vy*t;
    double x = pos.getX()+vx*t;
    this.v.setY(-vy);
    this.v.setX(vx);
    return new Position(x,y);
  }

  public void setFutur(Position p){
    this.pos.setX(p.getX());
    this.pos.setY(p.getY());
  }
}
