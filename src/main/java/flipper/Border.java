public class Border{//une bordure est considerer comme une ligne
  private double distance;
  private Position posX;
  public Position getPosX(){
    return posX;
  }
  private Position posY;
  public Position getPosY(){
    return posY;
  }
  private double rebond;//contient une variable de rebond entre 0 et 1
  public Border(Position posX,Position poxY,double rebond){
    this.posX=posX;
    this.posY=posY;
    this.distance=posX.distance(posY);
    if(rebond>=0&&rebond<=1)this.rebond=rebond;
    else this.rebond=0;
  }
  public boolean isOnTheLine(Position pos){
    return pos.distance(this.posX)+pos.distance(this.posY)==this.distance;
  }
}
