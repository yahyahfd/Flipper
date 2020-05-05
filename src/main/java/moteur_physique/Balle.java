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
  public void gravity(){
    a.setY(g);
    this.v.setY(v.getY()+a.getY()*t);
    if(pos.getY()>=800)pos.setY(0);
    else pos.setY(pos.getY()+v.getY()*t);
  }
  public Position futur(){
    double vy=v.getY()+a.getY()*t;
    double vx=v.getX()+a.getX()*t;
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position collision2(Border b){
    double vx;
    double vy;
    Position p2=pointOfCollision(b);
    Position pc=p2.closestToPoint(b.getPosX(),b.getPosY());
    this.pos=pointOfCollision(b);//on bouge la balle jusqu'au point de collision puis applique dessus sa nouvelle vitesse
    if(b instanceof Flip){
      Flip flip=(Flip)b;
      if(flip.getUp()==true){
        vx=flip.getV(this).getX()+v.getX();
        vy=flip.getV(this).getY()+v.getY();
        if(vy>-58)vy=-58;//vitesse mininum
        double x=pos.getX()+vx*t;
        double y=pos.getY()+vy*t;
        return new Position(x,y);
      }
    }
    vx=-v.vectUnitaire().scalaire(b.getNorm())*b.getRebond()*b.getNorm().getX()*v.norme()+v.norme()*v.vectUnitaire().scalaire(b.getUni())*b.getUni().getX()*b.getRebond();
    vy=-v.vectUnitaire().scalaire(b.getNorm())*b.getNorm().getY()*b.getRebond()*v.norme()+v.norme()*v.vectUnitaire().scalaire(b.getUni())*b.getUni().getY()*b.getRebond();
    double x=pos.getX()+vx*t;
    double y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position collision(Border b){//Mix entre formule donné par le prof et https://ericleong.me/research/circle-line/
    if(b instanceof Flip){
      Flip flip=(Flip)b;
      if(flip.getUp()==true){
        double vx=flip.getV(this).getX()+v.getX();
        double vy=flip.getV(this).getY()+v.getY();
        double x=pos.getX()+vx*t;
        double y=pos.getY()+vy*t;
        return new Position(x,y);
      }
    }
    Position p1=pos.closestToPoint(b.getPosX(),b.getPosY());
    Position p2=pointOfCollision(b);
    Position pc=p2.closestToPoint(b.getPosX(),b.getPosY());
    this.pos=p2;//on met la balle au point de collision pour eviter les bug graphique
    if(pc.isOnTheLine(b.getPosX(),b.getPosY())){//si on est dans le cas ou la balle ne touche pas les bout de la border alors on applique la formule du prof
      double vx=-v.scalaire(b.getNorm())*b.getRebond()*b.getNorm().getX()+v.scalaire(b.getUni())*b.getUni().getX()*b.getRebond();
      double vy=-v.scalaire(b.getNorm())*b.getNorm().getY()*b.getRebond()+v.scalaire(b.getUni())*b.getUni().getY()*b.getRebond();
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
      double vx=v.norme()*r.vectUnitaire().getX()*b.getRebond();
      double vy=v.norme()*r.vectUnitaire().getY()*b.getRebond();
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
    double tf=(r-d1)/(d2-d1);//on fait -0.1 pour arriver un dixieme de seconde avant l'evenement
    double vx=v.getX()+a.getX()*t;
    double vy=v.getY()+a.getY()*t;
    double x=pos.getX()+vx*t*tf;
    double y=pos.getY()+vy*t*tf;
    return new Position(x,y);
  }
  public Position sliding(Border b){
    double a=b.angle(new Vecteur(1,0));
    double x;
    double y;
    double vx;
    double vy;
    vx=(v.getX()+Math.cos(a)*g*t)*b.getRebond();
    vy=(v.getY()+Math.sin(a)*g*t)*b.getRebond();
    if(b.horizontale()&&!b.isOnTop(this)){
      vy=v.getY()+this.a.getY()*t;
    }
    if(b.horizontale()){
      vx=v.getX()*b.getRebond();
      vy=0;
    }
    pos.setY(b.stayOnTop(this));//si le calcul de l'angle est un peu different de la réelle courbure de la droite la balle passe par dessous la bordure
    x=pos.getX()+vx*t;
    y=pos.getY()+vy*t;
    return new Position(x,y);
  }
  public Position slidingColliding(Border b,Border c){
    double a=b.angle(new Vecteur(1,0));
    double x;
    double y;
    double vx;
    double vy;
    vx=(v.getX()+Math.cos(a)*g*t);
    vy=(v.getY()+Math.sin(a)*g*t);
    vx=-vx*c.getRebond();
    vy=-vy*c.getRebond();
    if(b.horizontale()&&!b.isOnTop(this)){
      vy=v.getY()+this.a.getY()*t;
    }
    if(b.horizontale()){
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
  public Position collisionFlip(Flip flip){
    if(flip.getUp()==true){
      double vy;
      double vx;
      vx=flip.getV(this).getX()*2+v.getX();
      vy=flip.getV(this).getY()*2+v.getY();
      if(vy>-58)vy=-58;//vitesse mininum
      double x=pos.getX()+vx*t;
      double y=pos.getY()+vy*t;
      return new Position(x,y);
    }else return null;
  }
  public Position collisionLauncher(Launcher launcher){
    if(launcher.getTop()==true){
      double vy=launcher.getVitesse();
      double y=pos.getY()+vy*t;
      return new Position(pos.getX(),y);
    }
    else if(launcher.getDown()==false&&launcher.getMoving()==true){
      return new Position(pos.getX(),pos.getY()-20);//suit le mouvement du launcher;
    }else if(launcher.getDown()==true&&launcher.getMoving()==true){
      return new Position(pos.getX(),pos.getY()+10);
    }
    return null;
  }
}
