package flipper;
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
  public void futur(){
    this.v.setY(v.getY()+a.getY()*t);
    this.v.setX(v.getX()+a.getX()*t);
    pos.setY(pos.getY()+v.getY()*t);
    pos.setX(pos.getX()+v.getX()*t);
  }
}
