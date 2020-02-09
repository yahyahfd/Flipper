package flipper;
public class Rectangle extends Shape{
  Position posA;
  Position posB;
  Position posC;
  Position posD;
  public Rectangle(double rebond,Position posA,Position posB,Position posC,Position posD){
    this.pos=new Position((posA.getX()+posB.getX())/2,(posA.getY()+posD.getY())/2);//centre du rectangle present dans la classe shape
    this.rebond=rebond;
    this.posA=posA;
    this.posB=posB;
    this.posC=posC;
    this.posD=posD;
  }
  public double getLength(){
    return posA.distance(posB);
  }
  public double getHeight(){
    return posA.distance(posD);
  }
  public double getArea(){
    return getLength()*getHeight();
  }
  public boolean isInTheShape(Position p){
    Vecteur AB=new Vecteur(posB.getX()-posA.getX(),posB.getY()-posA.getY());
    Vecteur BC=new Vecteur(posC.getX()-posB.getX(),posC.getY()-posB.getY());
    Vecteur AP=new Vecteur(p.getX()-posA.getX(),p.getY()-posA.getY());
    Vecteur BP=new Vecteur(p.getX()-posB.getX(),p.getY()-posB.getY());
    double scABAP= AB.scalaire(AP);
    double scABAB= AB.scalaire(AB);
    double scBCBP= BC.scalaire(BP);
    double scBCBC= BC.scalaire(BC);
    return 0<=scABAP&&scABAP<=scABAB&&0<=scBCBP&&scBCBP<=scBCBC; //true veut-dire qu'on est a droite de la bordure gauche
  }
}
