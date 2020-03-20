package moteur_physique;
public class Quadrilatere extends Shape{
  //Nous utiliserons que des Quadrilatere inscriptible (les quatres points font partie d'un meme cercle)
  private Position posA;
  private Position posB;
  private Position posC;
  private Position posD;
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
  public Double[] getAllPosition(){//Double[] est utilise par javafx
    Double[] d=new Double[8];
    d[0]=Double.valueOf(posA.getX());
    d[1]=Double.valueOf(posA.getY());
    d[2]=Double.valueOf(posB.getX());
    d[3]=Double.valueOf(posB.getY());
    d[4]=Double.valueOf(posC.getX());
    d[5]=Double.valueOf(posC.getY());
    d[6]=Double.valueOf(posD.getX());
    d[7]=Double.valueOf(posD.getY());
    return d;
  }
  public double getSlopeOfBorder(int border){
    double slope=0;
    switch(border){
      case(0):slope=(posB.getY()-posA.getY())/(posB.getX()-posA.getX());break;
      case(1):slope=(posC.getY()-posB.getY())/(posC.getX()-posB.getX());break;
      case(2):slope=(posD.getY()-posC.getY())/(posD.getX()-posC.getX());break;
      case(3):slope=(posA.getY()-posD.getY())/(posA.getX()-posD.getX());break;
    }
    return Math.toDegrees(Math.atan(slope));
  }

  public Border isInTheShape(Balle balle){
    Border A = new Border(posA,posB,rebond);
    Border B = new Border(posB,posC,rebond);
    Border C = new Border(posC,posD,rebond);
    Border D = new Border(posD,posA,rebond);
    boolean res1 = A.isOnTheLine(balle);
    boolean res2 = B.isOnTheLine(balle);
    boolean res3 = C.isOnTheLine(balle);
    boolean res4= D.isOnTheLine(balle);
    if(res1==true){
      return A;
    }
    if(res2==true){
      return B;
    }
    if(res3==true){
      return C;
    }
    if(res4==true){
      return D;
    }
    return null;
  }

  public Border[] turnIntoBorders(){
    Border[] res = new Border[4];
    res[0] = new Border(posA,posB,rebond);
    res[1] = new Border(posB,posC,rebond);
    res[2] = new Border(posC,posD,rebond);
    res[3] = new Border(posD,posA,rebond);
    return res;
  }
}
