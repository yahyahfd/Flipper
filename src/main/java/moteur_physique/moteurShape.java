package moteur_physique;
public abstract class moteurShape{
  protected double rebond;
  public double getRebond(){
    return rebond;
  }
  public abstract double getArea();
  public abstract boolean isInTheShape(Position pos);
  public boolean almostEquals(double a,double b){
    return Math.abs(a-b)<0.1;//lors des calculs d'aire les valeur differe au millieme voir plus cette fonction fait l'approximatisation au dixieme grace a cette fonction
  }
}
