package moteur_physique;
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
    this.t=16*0.01;
    a.setX(0);
    a.setY(5);
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
      vx=-v.scalaire(tmp.vitesse)*tmp.getRebond()*tmp.vitesse.getX()+v.scalaire(tmp.getUni())*tmp.getUni().getX()*tmp.getRebond();
      vy=-v.scalaire(tmp.vitesse)*tmp.vitesse.getY()*tmp.getRebond()+v.scalaire(tmp.getUni())*tmp.getUni().getY()*tmp.getRebond();
    }
    else{
      vx=-v.scalaire(b.getNorm())*b.getRebond()*b.getNorm().getX()+v.scalaire(b.getUni())*b.getUni().getX()*b.getRebond();
      vy=-v.scalaire(b.getNorm())*b.getNorm().getY()*b.getRebond()+v.scalaire(b.getUni())*b.getUni().getY()*b.getRebond();
    }
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public void setFutur(Position pos){
    this.v.setX((pos.getX()-this.pos.getX())/t);
    this.v.setY((pos.getY()-this.pos.getY())/t);
    this.pos.setY(pos.getY());
    this.pos.setX(pos.getX());
  }
}
