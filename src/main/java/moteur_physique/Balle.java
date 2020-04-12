package moteur_physique;
import java.util.ArrayList;

public class Balle{
  private boolean sliding=false;
  public boolean getSliding(){
    return sliding;
  }
  public void setSliding(boolean b){
    sliding=b;
  }
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
  public Vecteur getV(){
    return this.v;
  }
  private Vecteur a;//correspond a l'acceleration, pour l'instant c'est seulement la gravite
  private double t;//intervalle de temps(equivalent a 60fps)
  private double g=10;//constante de gravité
  public Balle(Position pos,double r,double m){
    this.pos=pos;
    this.r=r;
    this.m=m;
    this.v=new Vecteur(0,0);
    this.a=new Vecteur(0,0);
    this.t=16*0.01;
    a.setX(0);
    a.setY(10);
  }
  public void gravity(){
    a.setY(g);
    this.v.setY(v.getY()+a.getY()*t);
    if(pos.getY()>=800)pos.setY(0);
    else pos.setY(pos.getY()+v.getY()*t);
  }
  public Position futur(){
    double vy=v.getY()+a.getY()*t;
    double vx=v.getX()+a.getX()*t;
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position collision(Border b){
    double vx;
    double vy;
    vy=-v.scalaire(b.getNorm())*b.getNorm().getY()*b.getRebond()+v.scalaire(b.getUni())*b.getUni().getY()*b.getRebond();
    vx=-v.scalaire(b.getNorm())*b.getRebond()*b.getNorm().getX()+v.scalaire(b.getUni())*b.getUni().getX()*b.getRebond();
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position sliding(Border b){
    double a=b.angle(new Vecteur(1,0));
    double x;
    double y;
    double vx;
    double vy;
    vx=(v.getX()+Math.cos(a)*g*t);
    vy=(v.getY()+Math.sin(a)*g*t);
    if(b.horizontale()&&!b.isOnTop(this)){
      vy=v.getY()+this.a.getY()*t;
    }
    if(b.horizontale()){
      vx=v.getX()*b.getRebond();
      vy=0;
    }
    pos.setY(b.stayOnTop(this,pos.getY()));
    x=pos.getX()+vx*t;
    y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position slidingColliding(Border b,Border c){
    double a=b.angle(new Vecteur(1,0));
    double x;
    double y;
    double vx;
    double vy;
    vx=(v.getX()+Math.cos(a)*g*t);
    vy=(v.getY()+Math.sin(a)*g*t);
    vx=-vx*c.getRebond();
    vy=-vy*c.getRebond();
    if(b.horizontale()&&!b.isOnTop(this)){
      vy=v.getY()+this.a.getY()*t;
    }
    if(b.horizontale()){
      vx=v.getX()*b.getRebond();
      vy=0;
    }
    x=pos.getX()+vx*t;
    y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public void setFutur(Position pos){
    if(pos==null)return;
    this.v.setX((pos.getX()-this.pos.getX())/t);
    this.v.setY((pos.getY()-this.pos.getY())/t);
    this.pos.setY(pos.getY());
    this.pos.setX(pos.getX());
  }
  public Position collisionFlip(Flip flip){
    if(flip.getUp()==true){
      double vy;
      double vx;
      vx=flip.getV(this).getX()+v.getX();
      vy=flip.getV(this).getY()+v.getY();
      if(vy*t>-10)vy=-58;
      double x=pos.getX()+vx*t;
      double y=pos.getY()+vy*t;
      return new Position(x,y);
    }else
     return null;
  }
  public ArrayList<Position> hitbox(int precision){
  double angle = 2*Math.PI/precision;
  ArrayList<Position> points = new ArrayList<Position>();
  for(int i=1;i<precision;i++){
    points.add(new Position(Math.cos(angle*i), Math.sin(angle*i)));
  }
  return points;
}

}
