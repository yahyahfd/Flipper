package flipper;
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
  private Vecteur normal;
  public Vecteur getNorm(){
    return this.normal;
  }
  private double distance;
  private double rebond;//contient une variable de rebond entre 0 et 1
  public double getRebond(){
    return rebond;
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
  }
  public Position intersection(Balle balle){
    double a=0;
    double p=0;
    double aa=0;
    double pp=0;
    double x=0;
    double y=0;
    boolean bv=false;
    boolean bbv=false;
    if(posX.getY()==posY.getY()&&balle.futur().getY()==balle.getPos().getY()){//bordure parralele au mouvement donc aucune intersection
      return null;
    }
    if(posX.getX()==posY.getX()&&balle.futur().getX()==balle.getPos().getX()){//bordure parralele au mouvement donc aucune intersection
      return null;
    }
    //Border
    if(posX.getY()==posY.getY()){//cas d'une bordure horizontale
      a=0;
      p=posY.getY();
    }
    else if(posX.getX()==posY.getX()){//cas d'une bordure verticale
      x=posX.getX();
      bv=true;
    }
    else{
      a=(posY.getY()-posX.getY())/(posY.getX()-posX.getX());//a=(yb-ya)/(xb-xa)
      p=posX.getY()-(posX.getX()*a);//p=y-ax
    }
    //Balle
    if(balle.futur().getY()==balle.getPos().getY()){//cas d'une balle avec mouvement horizontale
      aa=0;
      pp=balle.futur().getY();
    }
    else if(balle.futur().getX()==balle.getPos().getX()){//cas d'une balle avec mouvement verticale
      x=balle.getPos().getX();
      bbv=true;
    }
    else{
      aa=(balle.futur().getY()-balle.getPos().getY())/(balle.futur().getX()-balle.getPos().getX());
      pp=balle.getPos().getY()-balle.getPos().getX()*aa;
    }
    if(bv==true){
      y=aa*x+pp;//la border est verticale
    }
    else if(bbv==true){
      y=a*x+p;//la balle a un mouvement verticale
    }
    else if(a==aa)return null;//deux droite paralleles
    else{
      x=(pp-p)/(a-aa);//cas normale
      y=a*x+p;
    }
    return new Position(x,y);
  }
  public boolean isOnTheSegment(Balle balle){//on regarde si le point d'intersection est sur le segment
    Position c=intersection(balle);
    if(c==null)return false;
    return c.distance(this.posX)+c.distance(this.posY)<=this.distance+5&&c.distance(this.posX)+c.distance(this.posY)>=this.distance-5;
  }
  public boolean isOnTheLine(Balle balle){
    if(!isOnTheSegment(balle))return false;
    Position c=intersection(balle);
    double dx=balle.futur().getX()-balle.getPos().getX();//positif si on descend
    double dy=balle.futur().getY()-balle.getPos().getY();//positif vers la droite
    if(dx>0&&dy>0&&balle.getPos().getX()<=c.getX()&&balle.getPos().getY()<c.getY()&&(c.getX()<=balle.futur().getX()||c.getY()<=balle.futur().getY())){
      return true;
    }
    if(dx<0&&dy>0&&balle.getPos().getX()>=c.getX()&&balle.getPos().getY()<c.getY()&&(c.getX()>=balle.futur().getX()||c.getY()<=balle.futur().getY())){
      return true;
    }
    if(dx>0&&dy<0&&balle.getPos().getX()<=c.getX()&&balle.getPos().getY()>c.getY()&&(c.getX()<=balle.futur().getX()||c.getY()>=balle.futur().getY())){
      return true;
    }
    if(dx<0&&dy<0&&balle.getPos().getX()>c.getX()&&balle.getPos().getY()>=c.getY()&&(c.getX()>=balle.futur().getX()||c.getY()>=balle.futur().getY())){
      return true;
    }
    if(dx==0&&dy>0&&balle.getPos().getY()<=c.getY()&&c.getY()<=balle.futur().getY())return true;
    if(dx==0&&dy<0&&balle.getPos().getY()>=c.getY()&&c.getY()>=balle.futur().getY())return true;
    if(dx>0&&dy==0&&balle.getPos().getX()<=c.getX()&&c.getX()<=balle.futur().getX())return true;
    if(dx<0&&dy==0&&balle.getPos().getX()>=c.getX()&&c.getX()>=balle.futur().getX())return true;
    return false;
  }
}
