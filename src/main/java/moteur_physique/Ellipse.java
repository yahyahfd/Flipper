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

  public Position isInTheShape(Balle balle){
    //y=ax+b : Calculons a et b:
    double a = (balle.getPos().getY()-balle.futur().getY())/(balle.getPos().getX()-balle.futur().getX());
    double b = balle.getPos().getY()*balle.getV().getX()-balle.getV().getY()*balle.getPos().getX();

    double c = pos.getX();
    double d = pos.getY();
    double r = major/2;
    double q = minor/2;

    //ax²+bx+c
    double z1 = Math.pow(q,2)+Math.pow(a,2)*Math.pow(r,2); //a
    double z2 = 2*a*b*Math.pow(r,2)-2*c*Math.pow(q,2)-2*a*d*Math.pow(r,2); //b
    double z3 = Math.pow(c,2)*Math.pow(q,2)+Math.pow(r,2)*(Math.pow(b,2)+Math.pow(d,2)-2*b*d-Math.pow(q,2)); //c
    double tmp = Math.pow(z2,2)-4*z1*z3; //delta
    if(tmp<0){
      return null;
    }else{ //les racines
      double resx1 = (-z2-Math.sqrt(tmp))/2*z1; // le x du premier point d'intersection
      double resy1 = a*resx1+b; //le y du premier point d'intersection
      double resx2 = (-z2+Math.sqrt(tmp))/2*z1; // le x du deuxième point d'intersection
      double resy2 = a*resx2+b; //le y du deuxième point d'intersection
      //on calcule les deux positions correspondantes:
      Position res1 = new Position(resx1,resy1);
      Position res2 = new Position(resx2,resy2);
      //on cherche lequel des deux points d'intersection est atteint en premier (dans le cas où delta(tmp) est nul, res1=res2)
      return balle.getPos().isCloser(res1,res2);
    }
  }
}
