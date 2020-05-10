package moteur_physique;
public class Launcher extends Border{
  private final double originY;
  private double vitesse;
  private boolean top=false;//ce boolean nous dit quand le launcher a atteint sa hauteur maximale
  private boolean down=false;//nous dis quand on est tout en bas
  private boolean moving=false;//nous dis si on bouge
  public boolean getTop(){
    return top;
  }
  public boolean getDown(){
    return down;
  }
  public boolean getMoving(){
    return moving;
  }
  public double getVitesse(){
    return vitesse;
  }
  public Launcher(Position posX,Position posY,double rebond){//rebond entre 0 et 1
     super(posX,posY,rebond);
     originY=posY.getY();
  }
  public boolean isBeetwen(Balle balle){
    if(originY>super.getPosX().getX()){
      if(balle.getPos().getX()>super.getPosY().getX()||balle.getPos().getX()<super.getPosX().getX())return false;
    }else{
      if(balle.getPos().getX()<super.getPosY().getX()||balle.getPos().getX()>super.getPosX().getX())return false;
    }
    return true;
  }
  public boolean isOnTop(Balle balle){
    if(!isBeetwen(balle))return false;
    return super.getPosY().getY()-balle.getPos().getY()<=balle.getR()+2&&super.getPosY().getY()-balle.getPos().getY()>=balle.getR();
  }

  public void moveLauncherUp(){
    if(super.getPosY().getY()>originY-120){
      super.getPosY().setY(super.getPosY().getY()-40);
      super.getPosX().setY(super.getPosX().getY()-40);
      vitesse+=235;//v=d/t ou t=0.17s et d=20 donc v=11.7
      top=false;
      down=false;
      moving=true;
    }else{
      down=false;
      top=true;
      moving=false;
    }
  }
  public void moveLauncherDown(){
    if(super.getPosY().getY()<originY){
      super.getPosY().setY(super.getPosY().getY()+10);
      super.getPosX().setY(super.getPosX().getY()+10);
      vitesse=0;
      down=true;
      moving=true;
    }else{
      top=false;
      down=false;
      moving=false;
    }
  }

}
