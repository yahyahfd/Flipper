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
  private double a;
  private double distance;
  private double rebond;//contient une variable de rebond entre 0 et 1
  public Border(Position posX,Position posY,double rebond){
    this.posX=posX;
    this.posY=posY;
    this.distance=this.posX.distance(this.posY);
    if(rebond>=0&&rebond<=1)this.rebond=rebond;
    else this.rebond=0;
  }
  public Position intersection(Balle balle){
    double a=(posY.getY()-posX.getY())/(posY.getX()-posX.getX());
    double p=posX.getY()-posX.getX()*a;
    double aa=(balle.futur().getY()-balle.getPos().getY())/(balle.futur().getX()-balle.getPos().getX());
    double pp=balle.getPos().getY()-balle.getPos().getX()*aa;
    double x=(pp-p)/(a-aa);
    double y=a*x+p;
    return new Position(x,y);
  }
  public boolean isOnTheSegment(Balle balle){
    Position c=intersection(balle);
    if(c.getX()<posX.getX()||c.getX()>pos.getX()||c.getY()<posX.getY()||c.getY()>posY.getY()){
      return false;
    }
    return true;
  }
  public boolean isOnTheLine(Balle balle){
    if(!isOnTheSegment(balle))return false;
    double dx=balle.getPos().getX()-balle.futur().getX();
    double dy=balle.getPos().getY()-balle.futur().getY();
    if(dx>0&&dy>0&&(c.getX()<=balle.futur().getX()||c.getY()<=balle.futur().getYc.getX()<=balle.futur().getX())){
      return true;
    }
    if(dx<0&&dy>0&&(c.getX()>=balle.futur().getX()||c.getY()<=balle.futur().getYc.getX()<=balle.futur().getX())){
      return true;
    }
    if(dx>0&&dy<0&&(c.getX()<=balle.futur().getX()||c.getY()>=balle.futur().getYc.getX()<=balle.futur().getX())){
      return true;
    }
    if(dx<0&&dy<0&&(c.getX()>=balle.futur().getX()||c.getY()>=balle.futur().getYc.getX()<=balle.futur().getX())){
      return true;
    }
    return false;
  }
}
