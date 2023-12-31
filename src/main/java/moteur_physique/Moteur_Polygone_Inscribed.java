package moteur_physique;
import java.util.ArrayList;
public class Moteur_Polygone_Inscribed extends Moteur_Polygone{
  public Moteur_Polygone_Inscribed(double rebond,int pscore,double rotation,int nbBorder,Position pos,double a,double b){
    super(rebond,pscore);
    double angle=(2*Math.PI)/nbBorder;
    ArrayList<Position> position=new ArrayList<Position>();
    rotation=Math.toRadians(rotation);//le polygone peut avoir une rotation
    double x=pos.getX()+(Math.cos(rotation)*a);
    double y=pos.getY()+(Math.sin(rotation)*b);
    super.addPos(new Position(x,y));
    for(int i=1;i<nbBorder;i++){
      x=Math.cos(angle*i)*a+pos.getX();
      y=Math.sin(angle*i)*b+pos.getY();
      super.addPos(new Position(x,y));
    }
  }
  public Moteur_Polygone_Inscribed(Moteur_Polygone p,int border,int nbBorder,double a,double b){
    super(p.getRebond(),p.getPolyScore());
    double angle=(2*Math.PI)/nbBorder;
    double rotation=p.getAngleOfBorder(border);
    double cx=p.getBorderCenter(border).getX();
    double cy=p.getBorderCenter(border).getY();
    for(int i=0;i<nbBorder;i++){
      double x=Math.cos(angle*i+rotation)*a+cx;
      double y=Math.sin(angle*i+rotation)*b+cy;
      double xx=Math.cos(rotation)*(x-cx)-Math.sin(rotation)*(y-cy)+cx;
      double yy=Math.sin(rotation)*(x-cx)+Math.cos(rotation)*(y-cy)+cy;
      super.addPos(new Position(xx,yy));
    }
  }
}
