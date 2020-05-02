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
  public Position intersection(Balle balle){//calcul decris sur https://www.analyzemath.com/Calculators/find_points_of_intersection_of_ellipse_and_line_calculator.html
    //y=mx+p
    //(x−h)²/a²+(y-k)²/b²=1
    double[] eqBalle=balle.getPos().equationDroite(balle.futur());
    double[] eqEllipse=equationEllipse();
    double a=eqEllipse[0];
    double b=eqEllipse[1];
    double h=eqEllipse[2];
    double k=eqEllipse[3];
    double m=eqBalle[0];
    double p=eqBalle[1];
    if(eqBalle[2]==0){//cas droite non vertical
      double aa=Math.pow(b,2)+(Math.pow(a,2)*Math.pow(m,2));
      double bb=(-2*h*Math.pow(b,2))+(2*m*Math.pow(a,2)*p)-(2*m*Math.pow(a,2)*k);
      double cc=(Math.pow(b,2)*Math.pow(h,2))+(Math.pow(a,2)*Math.pow(k,2))+(Math.pow(a,2)*Math.pow(p,2))-(2*Math.pow(a,2)*p*k)-(Math.pow(a,2)*Math.pow(b,2));
      double delta=Math.pow(bb,2)-4*aa*cc;
      if(delta<0)return null;
      double x1=(-bb-Math.sqrt(delta))/(2*aa);
      double x2=(-bb+Math.sqrt(delta))/(2*aa);
      double y1=m*x1+p;
      double y2=m*x2+p;
      Position px=new Position(x1,y1);
      Position py=new Position(x2,y2);
      return balle.getPos().isCloser(new Position(x1,y1),new Position(x2,y2));
    }else{//cas droite verticale
      double x=balle.getPos().getX(); //on a deja le x et donc on peut le remplacer dans l'equation de l'ellipse
      //(x−h)²/a²+(y-k)²/b²=1
      //(y-k)²/b²=b-(x−h)²/a²
      //(y-k)²=b²-(b²(x−h)²)/a²
      //y²-2yk+k²-b²-(b²(x−h)²)/a²=0
      //aa=1
      //bb=-2k
      //cc=k²-b²-(b²(x−h)²)/a²
      double aa=1;
      double bb=-2*k;
      double cc=Math.pow(k,2)-((Math.pow(b,2)-(Math.pow(b,2)*(Math.pow(x-h,2))/Math.pow(a,2))));
      double delta=Math.pow(bb,2)-4*aa*cc;
      if(delta<0)return null;
      double y1=(-bb-Math.sqrt(delta))/(2*aa);
      double y2=(-bb+Math.sqrt(delta))/(2*aa);
      return balle.getPos().isCloser(new Position(x,y1),new Position(x,y2));
    }
  }
  public double[] equationEllipse(){
    double[] eq=new double[4];//{a,b,h,k} (x−h)²/a²+(y-k)²/b²=1
    eq[0]=major;
    eq[1]=minor;
    eq[2]=pos.getX();
    eq[3]=pos.getY();
    return eq;
  }
  public Border isSliding(Balle balle){
    return null;
  }
  public Border isInTheShape(Balle balle){
    Position p=intersection(balle);
    if(p==null)return null;
    Vecteur t2 = new Vecteur(p.getX()-pos.getX(),p.getY()-pos.getY());
    Vecteur t3 = t2.vectNormUni();
    double x1=p.getX()-(t3.getX()*major);
    double x2=p.getX()+(t3.getX()*major);
    double y1=p.getY()-(t3.getY()*minor);
    double y2=p.getY()+(t3.getY()*minor);
    Border t4 = new Border(new Position(x1,y1),new Position(x2,y2),rebond);
    t4.setScoringTrue();
    t4.setBorderScore(this.getEllScore());
    if(t4.collision(balle)==true)return t4;//si notre balle entre en collision avec la tengante alors elle entre forcement en collision avec l'ellipse
    return null;
  }
  public boolean isColliding(Position tmp){
    return tmp.getX()<= pos.getX()+major/2 && tmp.getX()>= pos.getX()-major/2 && tmp.getY()<= pos.getY()+minor/2 && tmp.getY()>= pos.getY()-minor/2;
    //returns true uniquement si notre balle et sur/dans l'ellipse (la situation dans est évidemment impossible).
  }
}
