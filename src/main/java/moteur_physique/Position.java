package moteur_physique;
public class Position {
	private double x;
	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x=x;
	}
	private double y;
	public double getY(){
		return y;
	}
	public void setY(double y){
		this.y=y;
	}
	public Position(double posx,double posy) {
		this.x=posx;
		this.y=posy;
	}
	/**
	 * @param pos position qu'on utilise
	 * @return distance entre deux position this et pos
	 */
	public double distance(Position pos){
		return Math.sqrt(Math.pow(pos.x-this.x,2)+Math.pow(pos.y-this.y,2));
	}

  public Position isCloser(Position a, Position b){
		if(this.distance(a)<this.distance(b)){
			return a;
		}
		return b;
  }

	public double[] getXY(){
		double[] res={x,y};
		return res;
	}
	/**
	 * @param pos deuxieme position qu'on utilise
	 * @return l'equation de la droite avec les 2 points l'appartenant
	 */
	public double[] equationDroite(Position pos){//le resultat est sous forme {y,a,x,p,bv}
    double a=0;
    double p=0;
    int bv=0;//1 si droite verticale
    if(this.getY()==pos.getY()){//cas d'une droite horizontale
      a=0;
      p=pos.getY();
    }else if(this.getX()==pos.getX()){//cas d'une droite verticale
      bv=1;
      a=pos.getX();
    }else{
      a=(pos.getY()-this.getY())/(pos.getX()-this.getX());//a=(yb-ya)/(xb-xa)
      p=this.getY()-(this.getX()*a);//p=y-ax
    }
    double[] res={a,p,bv};
    return res;
  }
	public boolean isEqual(Position pos){
		return x==pos.x&&y==pos.y;
	}
	public String toString(){
		return "x: "+String.valueOf(x)+" y: "+String.valueOf(y);
	}
	public Position closestToPoint(Position posX,Position posY){
		double lx1=posX.getX();
		double ly1=posX.getY();
		double lx2=posY.getX();
		double ly2=posY.getY();
		double x0=this.getX();
		double y0=this.getY();
		double A1 = ly2 - ly1;
		double B1 = lx1 - lx2;
		double C1 = (ly2 - ly1)*lx1 + (lx1 - lx2)*ly1;
		double C2 = -B1*x0 + A1*y0;
		double det = A1*A1 - -B1*B1;
		double cx = 0;
		double cy = 0;
		if(det != 0){
			cx =((A1*C1 - B1*C2)/det);
			cy =((A1*C2 - -B1*C1)/det);
		}else{
			cx = x0;
			cy = y0;
		}
		return new Position(cx,cy);
	}
	public boolean isOnTheLine(Position posX,Position posY){//verifie si this est sur le segment [posX,posY]
    return this.distance(posX)+this.distance(posY)<=posX.distance(posY)+0.1&&this.distance(posX)+this.distance(posY)>=posX.distance(posY)-0.1;
  }
}
