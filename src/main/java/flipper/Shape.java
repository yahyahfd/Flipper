package flipper;
public abstract class Shape{
  protected double rebond;
  public double getRebond(){
    return rebond;
  }
  protected Position pos;
  public Position getPos(){
    return pos;
  }
  public abstract double getArea();
  public abstract boolean isInTheShape(Position pos);
}
