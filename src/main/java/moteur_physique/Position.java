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
	public String toString(){
		return "x: "+String.valueOf(x)+" y: "+String.valueOf(y);
	}
}
