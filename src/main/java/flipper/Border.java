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
    this.rebond=rebond;
  }
}
