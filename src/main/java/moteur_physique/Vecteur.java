package moteur_physique;
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
    if(y<0){y=-y;x=-x;}
    double a=Math.acos(scalaire(v)/(this.norme()*v.norme()));
    return a;
  }
  public double norme(){
    return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
  }

  public Vecteur vectUnitaire(){
    if(this.norme()==0)return new Vecteur(0,0);
    return new Vecteur(this.x/this.norme(),this.y/this.norme());
  }

  public Vecteur vectNormUni(){
    return new Vecteur(-this.vectUnitaire().y,this.vectUnitaire().x);
  }
  public boolean colineaire(Vecteur v){
    return v.getX()*vectUnitaire().getY()>=v.getY()*vectUnitaire().getX()-0.01&&v.getX()*vectUnitaire().getY()<=v.getY()*vectUnitaire().getX()+0.01;
  }
  public String toString(){
    return "Vecteur : ("+x+","+y+")";
  }
}
