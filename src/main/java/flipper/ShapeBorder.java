package flipper;
import moteur_physique.*;
public class ShapeBorder{
  Borders borders;
  Shapes shapes;
  public ShapeBorder(Borders borders,Shapes shapes){
    this.borders=borders;
    this.shapes=shapes;
  }
  public Border isOnALine(Balle balle){
    Border b=borders.isOnALine(balle);
    Border s=shapes.isOnALine(balle);
    if(s!=null&&b!=null){
      if(balle.getPos().isCloser(balle.pointOfCollision(b),balle.pointOfCollision(s))==balle.pointOfCollision(s)){
        return s;
      }else return b;
    }
    if(s!=null)return s;
    if(b!=null)return b;
    return null;
  }
  public Border isSliding(Balle balle){
    Border b=borders.isSliding(balle);
    Border s=shapes.isSliding(balle);
    if(b!=null)return b;
    if(s!=null)return s;
    return null;
  }
}
