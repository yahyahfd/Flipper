package flipper;
import moteur_physique.*;
public class ShapeBorder{//regroupe toute les bordure et shape du jeu
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
}
