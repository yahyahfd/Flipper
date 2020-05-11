package moteur_physique;
import java.util.ArrayList;

public class Balle{
  private boolean sliding=false;
  public boolean getSliding(){
    return sliding;
  }
  public void setSliding(boolean b){
    sliding=b;
  }
  private Position pos;
  public Position getPos(){
    return pos;
  }
  private double r;//en cm
  public double getR(){
    return r;
  }
  private double m;//en kg
  public double getM(){
    return m;
  }
  private Vecteur v;
  public Vecteur getV(){
    return this.v;
  }
  private Vecteur a;//correspond a l'acceleration, pour l'instant c'est seulement la gravite
  private double t;//intervalle de temps(equivalent a 60fps)
  private double g=10;//constante de gravité
  public Balle(Position pos,double r,double m){
    this.pos=pos;
    this.r=r;
    this.m=m;
    this.v=new Vecteur(0,0);
    this.a=new Vecteur(0,0);
    this.t=16*0.01;
    a.setX(0);
    a.setY(10);
  }
  public Position futur(){
    double vy=v.getY()+a.getY()*t;
    double vx=v.getX()+a.getX()*t;
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position futur(double t){
    double vy=v.getY()+a.getY()*t;
    double vx=v.getX()+a.getX()*t;
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position collision(Border b){//Mix entre formule donné par le prof et https://ericleong.me/research/circle-line/
    if(b instanceof Launcher){
      Launcher launcher =(Launcher)b;
      return collisionLauncher(launcher);
    }
    if((Math.abs(v.getX()*b.getUni().getY()-v.getY()*b.getUni().getX())<1||(Math.abs(v.getX())<0.2&&Math.abs(v.getY())<0.2))&&b.isOnTop(this)){
      if(!b.verticale())return sliding(b);
    }
    if(b instanceof Flip){ //le cas du flip est differents car on donne une force supplementaire a la balle
      Flip flip=(Flip)b;
      if(flip.getUp()==true){
        double vx=flip.getV(this).getX()*2+v.getX();
        double vy=flip.getV(this).getY()*2+v.getY();
        double x=pos.getX()+vx*t;
        double y=pos.getY()+vy*t;
        return new Position(x,y);
      }
    }
    Position p1=pos.closestToPoint(b.getPosX(),b.getPosY());
    Position p2=pointOfCollision(b);
    Position pc=p2.closestToPoint(b.getPosX(),b.getPosY());
    double vnorme=v.norme();
    if(pc.isOnTheLine(b.getPosX(),b.getPosY())){//si on est dans le cas ou la balle ne touche pas les bout de la border alors on applique la formule du prof
      double vx=-v.vectUnitaire().scalaire(b.getNorm())*b.getRebond()*b.getNorm().getX()+v.vectUnitaire().scalaire(b.getUni())*b.getUni().getX()*b.getRebond();
      vx=vnorme*vx;
      double vy=-v.vectUnitaire().scalaire(b.getNorm())*b.getNorm().getY()*b.getRebond()+v.vectUnitaire().scalaire(b.getUni())*b.getUni().getY()*b.getRebond();
      vy=vnorme*vy;
      double x=pos.getX()+vx*t;
      double y=pos.getY()+vy*t;
      return new Position(x,y);
    }
    else{//dans ce cas on applique la formule du site
      Position endpoint;
      endpoint=p2.isCloser(b.getPosX(),b.getPosY());
      double rx=p2.getX()-endpoint.getX();
      double ry=p2.getY()-endpoint.getY();
      Vecteur r=new Vecteur(rx,ry);//vecteur directeur de la nouvelle vitesse
      double vx=vnorme*r.vectUnitaire().getX()*b.getRebond();
      double vy=vnorme*r.vectUnitaire().getY()*b.getRebond();
      setFutur(p2);
      double x=pos.getX()+vx*t;
      double y=pos.getY()+vy*t;
      return new Position(x,y);
    }
  }
  public Position pointOfCollision(Border b){
    //d1=distance(pos,closestPoint)
    //d2=distance(futur(),closestPoint)
    //r=d1 + (d2-d1) * t
    //tf=(r - d1) / (d2-d1)   0<tf<1
    //pointOfCollision=pos+v*tf*t
    double d1=pos.distance(pos.closestToPoint(b.getPosX(),b.getPosY()));
    double d2=futur().distance(futur().closestToPoint(b.getPosX(),b.getPosY()));
    double tf=(r-d1)/(d2-d1);
    double vx=v.getX()+a.getX()*t;
    double vy=v.getY()+a.getY()*t;
    double x=pos.getX()+vx*t*tf;
    double y=pos.getY()+vy*t*tf;
    return new Position(x,y);
  }
  public Position sliding(Border b){
    Vecteur r=b.angle();
    double x;
    double y;
    double vx;
    double vy;
    Vecteur vv=new Vecteur(v.getX()+this.a.getX()*t,v.getY()+this.a.getY()*t);
    double vvnorme=vv.norme();
    vx=vvnorme*r.getX();
    vy=vvnorme*r.getY();
    if(b.horizontale()&&!b.isOnTop(this)){
      vy=v.getY()+this.a.getY()*t;
    }
    else if(b.horizontale()){
      vx=v.getX()*b.getRebond();
      vy=0;
    }
    x=pos.getX()+vx*t;
    y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public void setFutur(Position pos){
    if(pos==null)return;
    this.v.setX((pos.getX()-this.pos.getX())/t);
    this.v.setY((pos.getY()-this.pos.getY())/t);
    this.pos.setY(pos.getY());
    this.pos.setX(pos.getX());
  }
  public Position collisionLauncher(Launcher launcher){
    if(launcher.getTop()==true){
      double vy=launcher.getVitesse();
      double y=pos.getY()+vy*t;
      return new Position(pos.getX(),y);
    }
    else if(launcher.getDown()==false&&launcher.getMoving()==true){
      return new Position(pos.getX(),pos.getY()-40);//suit le mouvement du launcher;
    }else if(launcher.getDown()==true&&launcher.getMoving()==true){
      return new Position(pos.getX(),pos.getY()+10);
    }
    return null;
  }
}
