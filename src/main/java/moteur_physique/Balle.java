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
  private Position oldpos;
  public Position getOldPos(){
    return oldpos;
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
    oldpos=pos;
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
    if(b instanceof Flip){
      Flip tmp=(Flip)b;
      vx=-v.scalaire(tmp.getV())*tmp.getRebond()*tmp.getV().getX()+v.scalaire(tmp.getUni())*tmp.getUni().getX()*tmp.getRebond();
      vy=-v.scalaire(tmp.getV())*tmp.getV().getY()*tmp.getRebond()+v.scalaire(tmp.getUni())*tmp.getUni().getY()*tmp.getRebond();
    }
    else{
      vx=-v.scalaire(b.getNorm())*b.getRebond()*b.getNorm().getX()+v.scalaire(b.getUni())*b.getUni().getX()*b.getRebond();
      vy=-v.scalaire(b.getNorm())*b.getNorm().getY()*b.getRebond()+v.scalaire(b.getUni())*b.getUni().getY()*b.getRebond();
    }
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
    if(b.horizontale()){
      vx=v.getX()*b.getRebond();
      vy=0;
    }
    x=pos.getX()+vx*t;
    y=pos.getY()+vy*t;
    if(b instanceof Flip)sliding=true;
    else sliding=false;
    return new Position(x,y);
  }
  public void setFutur(Position pos){
    if(pos==null)return;
    oldpos=this.pos;
    this.v.setX((pos.getX()-this.pos.getX())/t);
    this.v.setY((pos.getY()-this.pos.getY())/t);
    this.pos.setY(pos.getY());
    this.pos.setX(pos.getX());
  }
  public Position collisionFlip(Flip flip){
    double vy;
    double vx;
    if(flip.getUp()==true){
      if(flip.getV().getY()>0){
        vy=-flip.getV().getY()*50+v.getY();
        vx=-flip.getV().getX()*50+v.getX();
      }else{
        vy=flip.getV().getY()*50+v.getY();
        vx=flip.getV().getX()*50+v.getX();
      }
    }
    else{
      return collision(flip);
    }
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
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
