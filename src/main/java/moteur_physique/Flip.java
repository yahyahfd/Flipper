package moteur_physique;
public class Flip extends Border{
  private final Position originY;
  private boolean up=false;
  private double vitesseAngulaire=0;//On prend just w, R sera pris en compte que lors de la collision
  public boolean getUp(){
    return up;
  }
  public void setUp(boolean up){
    this.up=up;
  }
  public Position getOriginY(){
    return originY;
  }
  /**
   *  Renvoie le vecteur vitesse du flip en utilisant sa vitesse angulaire
   * @return Vecteur
   */
  public Vecteur getV(Balle balle){
    double nx=super.getNorm().getX();
    double ny=super.getNorm().getY();
    if(super.getNorm().getY()>0){
      nx=-nx;
      ny=-ny;
    }
    Position c=balle.getPos().closestToPoint(super.getPosX(),super.getPosY());
    double vx=(vitesseAngulaire/0.16)*super.getPosX().distance(c)*nx;
    double vy=(vitesseAngulaire/0.16)*super.getPosX().distance(c)*ny;
    return new Vecteur(vx,vy);
  }
  public Flip(Position posX,Position posY,double rebond){//rebond entre 0 et 1
     super(posX,posY,rebond);
     originY=new Position(posY.getX(),posY.getY());
  }
  public void moveFlipUp(){//seul le bout de la bordure change de place et donc crée un effet de mouvement circulaire
    double a=30;
    a=Math.toRadians(30);
    double sign=1;
    if(originY.getX()<super.getPosX().getX())sign=-1*sign;//sign determine le sens de rotation du flip determiner par la position du pivot posX
    if(super.getPosY().getY()>Math.sin(-a*sign)*(originY.getX()-super.getPosX().getX())+Math.cos(-a*sign)*(originY.getY()-super.getPosX().getY())+super.getPosX().getY()){
      double x=Math.cos(-0.15*sign)*(super.getPosY().getX()-super.getPosX().getX())-Math.sin(-0.15*sign)*(super.getPosY().getY()-super.getPosX().getY());
      double y=Math.sin(-0.15*sign)*(super.getPosY().getX()-super.getPosX().getX())+Math.cos(-0.15*sign)*(super.getPosY().getY()-super.getPosX().getY());
      x+=super.getPosX().getX();
      y+=super.getPosX().getY();
      super.getPosY().setX(x);
      super.getPosY().setY(y);
      newUniNorm();
      vitesseAngulaire+=0.15;
      up=true;
    }else{
      up=false;
    }
  }
  public void moveFlipDown(){
    double sign=1;
    if(originY.getX()<super.getPosX().getX())sign=-1*sign;
    if((super.getPosY().getY()<originY.getY()&&sign==1)||(super.getPosY().getY()<originY.getY()&&sign==-1)){
      double x=Math.cos(0.035*sign)*(super.getPosY().getX()-super.getPosX().getX())-Math.sin(0.035*sign)*(super.getPosY().getY()-super.getPosX().getY());
      double y=Math.sin(0.035*sign)*(super.getPosY().getX()-super.getPosX().getX())+Math.cos(0.035*sign)*(super.getPosY().getY()-super.getPosX().getY());
      x+=super.getPosX().getX();
      y+=super.getPosX().getY();
      super.getPosY().setX(x);
      super.getPosY().setY(y);
      newUniNorm();
      vitesseAngulaire=0;
      up=false;
    }
  }
  public void newUniNorm(){
    Vecteur u=new Vecteur(super.getPosY().getX()-super.getPosX().getX(),super.getPosY().getY()-super.getPosX().getY());
    super.setUni(u.vectUnitaire());
    super.setNorm(super.getUni().vectNormUni());
  }
}
