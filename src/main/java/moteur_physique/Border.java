package moteur_physique;
public class Border{//une bordure est considerer comme une ligne
  private Position posX;
  public Position getPosX(){
    return posX;
  }
  private Position posY;
  public Position getPosY(){
    return posY;
  }
  private Vecteur vect;
  public Vecteur getVect(){
    return this.vect;
  }
  private Vecteur unitaire;
  public Vecteur getUni(){
    return this.unitaire;
  }
  public void setUni(Vecteur u){
    unitaire=u;
  }
  private Vecteur normal;
  public Vecteur getNorm(){
    return this.normal;
  }
  public void setNorm(Vecteur n){
    normal=n;
  }
  private double distance;
  public double getDistance(){
    return distance;
  }
  private double rebond;//contient une variable de rebond entre 0 et 1
  public double getRebond(){
    return rebond;
  }
  public String toString(){
    return posX.toString()+"\n"+posY.toString();
  }
  private boolean scoring;
public void setScoringTrue(){
  this.scoring = true;
}
public boolean getScoring(){
  return this.scoring;
}
private int borderscore;
public int getBorderScore(){
  return this.borderscore;
}
public void setBorderScore(int bs){
  this.borderscore = bs;
}
  public Border(Position posX,Position posY,double rebond){
    this.posX=posX;
    this.posY=posY;
    this.distance=this.posX.distance(this.posY);
    this.vect = new Vecteur(posY.getX()-posX.getX(),posY.getY()-posX.getY());
    this.unitaire = this.vect.vectUnitaire();
    this.normal = this.unitaire.vectNormUni();
    if(rebond>=0&&rebond<=1)this.rebond=rebond;
    else this.rebond=0;
    this.scoring = false;
    this.borderscore = 0;

  }

  public Position intersection(Balle balle){
    double a=0;//equation de droite de la border y=ax+p
    double p=0;
    double aa=0;//equation de droite du mouvement de la balle y=aax+pp
    double pp=0;
    double x=0;
    double y=0;
    if(isSliding(balle)){
      return null;
    }
    if(posX.getY()==posY.getY()&&balle.futur().getY()==balle.getPos().getY()){//bordure parralele au mouvement donc aucune intersection
      return null;
    }
    if(posX.getX()==posY.getX()&&balle.futur().getX()==balle.getPos().getX()){//bordure parralele au mouvement donc aucune intersection
      return null;
    }
    //Border
    double [] eqBorder=posX.equationDroite(posY);
    //Balle
    double[] eqBalle=balle.getPos().equationDroite(balle.futur());
    if(eqBorder[2]==1){
      x=posX.getX();
      y=eqBalle[0]*x+eqBalle[1];//la border est verticale y=aa*x+pp
    }
    else if(eqBalle[2]==1){
      x=balle.getPos().getX();
      y=eqBorder[0]*x+eqBorder[1];//la balle a un mouvement verticale y=a*x+pp
    }
    else if(eqBorder[0]==eqBalle[0])return null;//deux droite parallelesa
    else{
      x=(eqBalle[1]-eqBorder[1])/(eqBorder[0]-eqBalle[0]);//cas normale x=(pp-p)/(a-aa)
      y=eqBorder[0]*x+eqBorder[1];
    }
    return new Position(x,y);
  }
  public boolean isOnTheSegment(Balle balle){//on regarde si le point d'intersection est sur le segment
    Position c=intersection(balle);
    if(c==null)return false;
    return c.distance(this.posX)+c.distance(this.posY)<=this.distance+0.1&&c.distance(this.posX)+c.distance(this.posY)>=this.distance-0.1;
  }
  public boolean isOnTheLine(Balle balle){
    if(!isOnTheSegment(balle))return false;
    Position c=intersection(balle);
    double dx=balle.futur().getX()-balle.getPos().getX();//positif si on descend
    double dy=balle.futur().getY()-balle.getPos().getY();//positif vers la droite
    if(dx>0&&dy>0&&balle.getPos().getX()<=c.getX()&&balle.getPos().getY()<c.getY()&&(c.getX()<=balle.futur().getX()||c.getY()<=balle.futur().getY())){
      return true;//mouvement vers le bas et vers la droite
    }
    if(dx<0&&dy>0&&balle.getPos().getX()>=c.getX()&&balle.getPos().getY()<c.getY()&&(c.getX()>=balle.futur().getX()||c.getY()<=balle.futur().getY())){
      return true;//mouvement vers le bas et vers la gauche
    }
    if(dx>0&&dy<0&&balle.getPos().getX()<=c.getX()&&balle.getPos().getY()>c.getY()&&(c.getX()<=balle.futur().getX()||c.getY()>=balle.futur().getY())){
      return true;//mouvement vers le haut et vers la droite
    }
    if(dx<0&&dy<0&&balle.getPos().getX()>c.getX()&&balle.getPos().getY()>=c.getY()&&(c.getX()>=balle.futur().getX()-1||c.getY()>=balle.futur().getY())){
      return true;//mouvement vers le haut et vers la gauche
    }
    if(dx==0&&dy>0&&balle.getPos().getY()<=c.getY()&&c.getY()<=balle.futur().getY())return true;
    if(dx==0&&dy<0&&balle.getPos().getY()>=c.getY()&&c.getY()>=balle.futur().getY())return true;
    if(dx>0&&dy==0&&balle.getPos().getX()<=c.getX()&&c.getX()<=balle.futur().getX())return true;
    if(dx<0&&dy==0&&balle.getPos().getX()>=c.getX()&&c.getX()>=balle.futur().getX())return true;
    return false;
  }
  public double angle(Vecteur v){
    return this.unitaire.angle(v);
  }
  public boolean horizontale(){
    return posX.getY()==posY.getY();
  }
  public boolean verticale(){
    return posX.getX()==posY.getX();
  }
  public boolean isSliding(Balle balle){
    if(verticale())return false;
    boolean b1=(balle.getV().getX()*unitaire.getY()-balle.getV().getY()*unitaire.getX()>-1&&balle.getV().getX()*unitaire.getY()-balle.getV().getY()*unitaire.getX()<1);//vitesse colineaire a la border
    if(b1&&isOnTop(balle)){
      return balle.getPos().distance(posX)+balle.getPos().distance(posY)<=distance+1&&balle.getPos().distance(posX)+balle.getPos().distance(posY)>=distance-1;//derniere verification qu'on est bien sur la border
    }
    return false;
  }
  public Border isCloser(Border border,Balle balle){
    if(this.intersection(balle).distance(balle.getPos())<border.intersection(balle).distance(balle.getPos())){
      return this;
    }
    return border;
  }
  public boolean isOnTop(Balle balle){
    double[] eqFlip=getPosX().equationDroite(getPosY());
    if(eqFlip[2]==1)return true;//barre verticale,on renvoie true quoi qu'il arrive
    if(balle.getPos().getY()<eqFlip[0]*balle.getPos().getX()+eqFlip[1]){//on verifie si on est au dessus de la balle
      return true;
    }
    return false;
  }
  public double stayOnTop(Balle balle,double y){
    double[] eqFlip=getPosX().equationDroite(getPosY());
    y=(eqFlip[0]*balle.getPos().getX()+eqFlip[1]-1);
    return y;
  }
  public boolean isSuccessive(Border b){
    return posX.isEqual(b.posX)||posX.isEqual(b.posY)||posY.isEqual(b.posY);
  }
}
