package moteur_physique;
public class Flip extends Border{
  private final Position originY;
  private boolean up=false;
  public boolean getUp(){
    return up;
  }
  public void setUp(boolean up){
    this.up=up;
  }
  public Position getOriginY(){
    return originY;
  }
  private Vecteur vitesse;
  public Vecteur getV(){
    return vitesse;
  }
  public Flip(Position posX,Position posY,double rebond){//rebond entre 0 et 1
     super(posX,posY,rebond);
     originY=new Position(posY.getX(),posY.getY());
     vitesse=new Vecteur(super.getNorm().getX(),super.getNorm().getY());
  }
  public void moveFlipUp(){//seul le bout de la bordure change de place et donc crée un effet de mouvement circulaire
    double a=30;
    a=Math.toRadians(30);
    double sign=1;
    if(originY.getX()<super.getPosX().getX())sign=-1*sign;
    if(super.getPosY().getY()>Math.sin(-a*sign)*(originY.getX()-super.getPosX().getX())+Math.cos(-a*sign)*(originY.getY()-super.getPosX().getY())+super.getPosX().getY()){
      double x=Math.cos(-0.15*sign)*(super.getPosY().getX()-super.getPosX().getX())-Math.sin(-0.15*sign)*(super.getPosY().getY()-super.getPosX().getY());
      double y=Math.sin(-0.15*sign)*(super.getPosY().getX()-super.getPosX().getX())+Math.cos(-0.15*sign)*(super.getPosY().getY()-super.getPosX().getY());
      x+=super.getPosX().getX();
      y+=super.getPosX().getY();
      super.getPosY().setX(x);
      super.getPosY().setY(y);
      newUniNorm();
      vitesse.setX(super.getNorm().getX()*1.2);
      vitesse.setY(super.getNorm().getY()*1.2);
      up=true;
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
      vitesse.setX(super.getNorm().getX());
      vitesse.setY(super.getNorm().getY());
      up=false;
    }
  }
  public boolean isAlwaysOnTop(Balle balle){//la balle ne passe pas miraculeusement en dessous du flip ce qui peux arriver lorsque les deux bouge
    double[] eqFlip=super.getPosX().equationDroite(super.getPosY());
    if(eqFlip[2]==1)return true;
    if(balle.futur().getY()<eqFlip[0]*balle.futur().getX()+eqFlip[1]){
      return true;
    }
    return false;
  }
  public boolean isOntheFlip(Balle balle){
    if(!isOnTheSegment(balle))return false;
    Position c=intersection(balle);
    if(!isAlwaysOnTop(balle)){
      if(balle.getSliding()==false){
        balle.getPos().setX(c.getX());
        balle.getPos().setY(c.getY());
      }
      return true;
    }return false;
  }
  public void newUniNorm(){
    Vecteur u=new Vecteur(super.getPosY().getX()-super.getPosX().getX(),super.getPosY().getY()-super.getPosX().getY());
    super.setUni(u.vectUnitaire());
    super.setNorm(super.getUni().vectNormUni());
  }
}
