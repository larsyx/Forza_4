import java.awt.Color;


public class Gettone {


	private int x,y ;
	private Color color;
	
	public Gettone( int x, int y, Color c) {
		this.x=x;
		this.y=y;
		color=c;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	
	
	

}
