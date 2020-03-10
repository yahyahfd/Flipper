package moteur_physique;
public class Flip extends Border{
  private final Position originY;
  Vecteur vitesse;
  public Flip(Position posX,Position posY,double rebond){//rebond entre 0 et 1
     super(posX,posY,rebond);
     originY=new Position(posY.getX(),posY.getY());
     vitesse=new Vecteur(super.getNorm().getX(),super.getNorm().getY());
  }
  public void moveFlipUp(){//seul le bout de la bordure change de place et donc crée un effet de mouvement circulaire
    double x=Math.cos(-45)*(originY.getX()-super.getPosX().getX())-Math.sin(-45)*(originY.getY()-super.getPosX().getY());
    double y=Math.sin(-45)*(originY.getX()-super.getPosX().getX())+Math.cos(-45)*(originY.getY()-super.getPosX().getY());
    x+=super.getPosX().getX();
    y+=super.getPosX().getY();
    super.getPosY().setX(x);
    super.getPosY().setY(y);
    newUniNorm();
    vitesse.setX(super.getNorm().getX()*1.2);
    vitesse.setY(super.getNorm().getY()*1.2);
  }
  public void moveFlipDown(){
      double x=Math.cos(45)*(super.getPosY().getX()-super.getPosX().getX())-Math.sin(45)*(super.getPosY().getY()-super.getPosX().getY());
      double y=Math.sin(45)*(super.getPosY().getX()-super.getPosX().getX())+Math.cos(45)*(super.getPosY().getY()-super.getPosX().getY());
      x+=super.getPosX().getX();
      y+=super.getPosX().getY();
      super.getPosY().setX(x);
      super.getPosY().setY(y);
      newUniNorm();
      vitesse.setX(super.getNorm().getX());
      vitesse.setY(super.getNorm().getY());
  }
  public void newUniNorm(){
    Vecteur u=new Vecteur(super.getPosY().getX()-super.getPosX().getX(),super.getPosY().getY()-super.getPosX().getY());
    super.setUni(u.vectUnitaire());
    super.setNorm(super.getUni().vectNormUni());
  }
}
