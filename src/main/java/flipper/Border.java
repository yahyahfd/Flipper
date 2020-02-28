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
  public boolean isOnTheLine(Position pos){
    return pos.distance(this.posX)+pos.distance(this.posY)==this.distance;
  }
}
