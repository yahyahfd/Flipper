package flipper;
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
	public double[] getXY(){
		double[] res={x,y};
		return res;
	}
	public String toString(){
		return "x: "+String.valueOf(x)+" y: "+String.valueOf(y);
	}
}
