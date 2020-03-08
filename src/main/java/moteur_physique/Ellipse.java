package moteur_physique;
public class Ellipse extends Shape{
  private double major;//par convention major sera le diametre de l'axe majeur et non le rayon
  public double getMajor(){
    return major;
  }
  private double minor;
  public double getMinor(){
    return minor;
  }
  private Position pos;
  public Position getPos(){
    return pos;
  }
  private double rotate;//l'ellipse n'est pas "parralele" a l'axe des abcisses
  //l'angle est par rapport a l'axe des abcisses
  public double getRotate(){
    return rotate;
  }
  public Ellipse(double rotate,double rebond,double major,double minor,Position pos){
    this.rotate=rotate;
    this.rebond=rebond;
    this.major=major;
    this.minor=minor;
    this.pos=pos;
  }
  public double getArea(){
    return (major/2)*(minor/2)*3;
  }
  public boolean isInTheShape(Position p){
    return Math.pow(p.getX()-pos.getX(),2)/Math.pow(major/2,2)+Math.pow(p.getY()-pos.getY(),2)/Math.pow(minor/2,2)<=1;
    //true si plus petit que 1
  }
}
