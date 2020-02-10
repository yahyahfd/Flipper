package flipper;
public class Ellipse extends Shape{
  double major;//par convention major sera le diametre de l'axe majeur et non le rayon
  double minor;
  Position center;
  public Ellipse(double major,double minor,Position center){
    this.major=major;
    this.minor=minor;
    this.center=center;
  }
  public double getArea(){
    return (major/2)*(minor/2)*3;
  }
  public boolean isInTheShape(Position pos){
    return Math.pow(pos.getX()-center.getX(),2)/Math.pow(major/2,2)+Math.pow(pos.getY()-center.getY(),2)/Math.pow(minor/2,2)<=1;
    //true si plus petit que 1
  }
}
