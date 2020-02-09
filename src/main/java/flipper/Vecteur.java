package flipper;
public class Vecteur{
  private double x;
  public double getX(){
    return x;
  }
  public void setX(double x){
    this.x=x;
  }
  private double y;
  public void setY(double y){
    this.y=y;
  }
  public double getY(){
    return y;
  }
  public Vecteur(double x,double y){
    this.x=x;
    this.y=y;
  }
  public double scalaire(Vecteur v){
    return x*v.x+y*v.y;
  }
  public double angle(Vecteur v){
    return Math.acos(scalaire(v)/this.norme()*v.norme());
  }
  public double norme(){
    return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
  }
}
