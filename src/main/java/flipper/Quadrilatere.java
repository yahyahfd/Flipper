package flipper;
public class Quadrilatere extends Shape{
  //Nous utiliserons que des Quadrilatere inscriptible (les quatres points font partie d'un meme cercle)
  Position posA;
  Position posB;
  Position posC;
  Position posD;
  public Quadrilatere(double rebond,Position posA,Position posB,Position posC,Position posD){
    this.rebond=rebond;
    this.posA=posA;
    this.posB=posB;
    this.posC=posC;
    this.posD=posD;
  }
  public double getArea(){//simple formule qui marche pour tout les polygones
    double f1=((posA.getX()*posB.getY())-(posA.getY()*posB.getX()));
    double f2=((posB.getX()*posC.getY())-(posB.getY()*posC.getX()));
    double f3=((posC.getX()*posD.getY())-posC.getY()*posD.getX());
    double f4=((posD.getX()*posA.getY())-(posD.getY()*posA.getX()));
    return Math.abs((f1+f2+f3+f4)/2);
  }
  public boolean isInTheShape(Position pos){//on prend 4 triangle avec sommet la pos de la balle et 2 sommet du quadrilatere
    //si l'aire des 4 triangle additioné est la meme que celle du quadrilatere alors le points est dedans
    Triangle abp=new Triangle(rebond,posA,posB,pos);
    Triangle bcp=new Triangle(rebond,posB,posC,pos);
    Triangle cdp=new Triangle(rebond,posD,posC,pos);
    Triangle dap=new Triangle(rebond,posD,posA,pos);
    return almostEquals(abp.getArea()+bcp.getArea()+cdp.getArea()+dap.getArea(),this.getArea());
  }
  public double getBorderLength(int border){
    switch (border){
      case 0:return posA.distance(posB);
      case 1:return posB.distance(posC);
      case 2:return posC.distance(posD);
      case 3:return posD.distance(posA);
    }
    return 0;
  }
  public Position getBorderCenter(int border){
    switch (border){
      case 0:return new Position((posA.getX()+posB.getX())/2,(posA.getY()+posB.getY())/2);
      case 1:return new Position((posB.getX()+posC.getX())/2,(posB.getY()+posC.getY())/2);
      case 2:return new Position((posC.getX()+posD.getX())/2,(posC.getY()+posD.getY())/2);
      case 3:return new Position((posD.getX()+posA.getX())/2,(posD.getY()+posA.getY())/2);
    }
    return null;
  }
}
