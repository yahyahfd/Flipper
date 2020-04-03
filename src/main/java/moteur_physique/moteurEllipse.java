package moteur_physique;
public class moteurEllipse extends moteurShape{
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
  private int ellScore;
  public int getEllScore(){
    return this.ellScore;
  }
  public void setEllScore(int bs){
    this.ellScore = bs;
  }
  public moteurEllipse(double rotate,double rebond,double major,double minor,Position pos){
    this.rotate=rotate;
    this.rebond=rebond;
    this.major=major;
    this.minor=minor;
    this.pos=pos;
  }

  public moteurEllipse(double rotate,double rebond,double major,double minor,Position pos,int esco){
    this.rotate=rotate;
    this.rebond=rebond;
    this.major=major;
    this.minor=minor;
    this.pos=pos;
    this.ellScore=esco;
  }
  public double getArea(){
    return (major/2)*(minor/2)*3;
  }
  public boolean isInTheShape(Position p){
    return Math.pow(p.getX()-pos.getX(),2)/Math.pow(major/2,2)+Math.pow(p.getY()-pos.getY(),2)/Math.pow(minor/2,2)<=1;
    //true si plus petit que 1
  }

  public Position intersection(Balle balle){
    //y=ax+b : Calculons a et b:
    double tmp1 = -balle.getPos().getY()+balle.futur().getY();
    double tmp2 = -balle.getPos().getX()+balle.futur().getX();
    double a = tmp1/tmp2;
    if(tmp2==0){
      a=0;
    }
    double b = balle.getPos().getY()-a*balle.getPos().getX();
    double c = pos.getX();
    double d = pos.getY();
    double r = major/2;
    double q = minor/2;

    //ax²+bx+c
    double z1 = Math.pow(q,2)+Math.pow(a*r,2); //a
    double z2 = 2*(Math.pow(r,2)*a*(b-d)-c*Math.pow(q,2)); //b
    double z3 = Math.pow(q*c,2)+Math.pow(r*b,2)+Math.pow(r*d,2)-Math.pow(r*q,2)-2*Math.pow(r,2)*b*d; //c
    double tmp = Math.pow(z2,2)-4*z1*z3; //delta
    if(tmp<0){
      return null;
    }else{ //les racines
      double resx1 = (-z2-Math.sqrt(tmp))/(2*z1); // le x du premier point d'intersection
      double resy1 = a*resx1+b; //le y du premier point d'intersection
      double resx2 = (-z2+Math.sqrt(tmp))/(2*z1); // le x du deuxième point d'intersection
      double resy2 = a*resx2+b; //le y du deuxième point d'intersection
      //on calcule les deux positions correspondantes:
      Position res1 = new Position(resx1,resy1);
      Position res2 = new Position(resx2,resy2);
      //on cherche lequel des deux points d'intersection est atteint en premier (dans le cas où delta(tmp) est nul, res1=res2)
      Position resf = balle.getPos().isCloser(res1,res2);
      if(this.isColliding(balle.futur())){
        return resf;
      }else{
        return null;
      }
    }
  }
  public Border isSliding(Balle balle){
    return null;
  }
  public Border isInTheShape(Balle balle){
    Position p=intersection(balle);
    if(p==null)return null;
    Vecteur t2 = new Vecteur(p.getX()-pos.getX(),p.getY()-pos.getY());
    Vecteur t3 = t2.vectNormUni();
    Border t4 = new Border(new Position(p.getX()-t3.getX(),p.getY()-t3.getY()),new Position(p.getX()+t3.getX(),p.getY()+t3.getY()),rebond);
    t4.setScoringTrue();
    t4.setBorderScore(this.getEllScore());
    return t4;
  }
  public boolean isColliding(Position tmp){
    return tmp.getX()<= pos.getX()+major/2 && tmp.getX()>= pos.getX()-major/2 && tmp.getY()<= pos.getY()+minor/2 && tmp.getY()>= pos.getY()-minor/2;
    //returns true uniquement si notre balle et sur/dans l'ellipse (la situation dans est évidemment impossible).
  }
}
