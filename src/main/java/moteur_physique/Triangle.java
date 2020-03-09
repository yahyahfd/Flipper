package moteur_physique;
public class Triangle extends Shape{
  private Position posA;
  private Position posB;
  private Position posC;
  public Triangle(double rebond,Position posA,Position posB,Position posC){
    this.rebond=rebond;
    this.posA=posA;
    this.posB=posB;
    this.posC=posC;
  }
  public double getArea(){
    double f1=((posA.getX()*posB.getY())-(posA.getY()*posB.getX()));
    double f2=((posB.getX()*posC.getY())-(posB.getY()*posC.getX()));
    double f3=((posC.getX()*posA.getY())-posC.getY()*posA.getX());
    return Math.abs((f1+f2+f3)/2);
  }
  public boolean isInTheShape(Position pos){
    Triangle abp=new Triangle(rebond,posA,posB,pos);
    Triangle bcp=new Triangle(rebond,posB,posC,pos);
    Triangle acp=new Triangle(rebond,posA,posC,pos);
    return almostEquals(abp.getArea()+bcp.getArea()+acp.getArea(),this.getArea());
  }
}
