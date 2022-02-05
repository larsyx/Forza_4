import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Tavolo extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Color COLORE_GIOCATORE=Color.RED;
	private static final Color COLORE_COMPUTER=Color.GREEN;
	private static final Color COLORE_VUOTO=Color.WHITE;
	
	ArrayList<Pedina> pedine;
	
	public Tavolo() {
		pedine=new ArrayList<>();
		generaGettoni();
	}
	
	private void generaGettoni() {
		for(int i=0;i<6;i++) 
			for(int j=0;j<7;j++) 
				pedine.add(new Pedina(495+(j*130), 75+(i*130), COLORE_VUOTO));
	}

	public void paintComponent(Graphics g) {
		
		Graphics2D g2= (Graphics2D) g;
		
		Rectangle box = new Rectangle(465,45,970,810);
		
		g2.setColor(Color.BLUE);
		g2.fill(box);
		
		Ellipse2D.Double cerchio;
		
		for(Pedina x: pedine) {
			 cerchio=new Ellipse2D.Double(x.getX(),x.getY(),100,100);
			 g2.setColor(x.getColor());
			 g2.fill(cerchio);
		}
			
		
		
		
	}
	
	
	public void ridisegna(int index, String user) {	
		
		if(index<0 || index>42)
			return;
		
		switch (user) {
			case "computer": {
				pedine.get(index).setColor(COLORE_COMPUTER);
				break;
			}
			case "giocatore":{
				pedine.get(index).setColor(COLORE_GIOCATORE);
				break;
			}
			default:
				throw new IllegalArgumentException();
		}
		repaint();
	}
	
	public int inserisciGettone(int x) {
		System.out.println("sono in inserisciGettone:" + x);
		if(x>=495 && x<=595)
			return 0;
		else if(x>=625 && x<=725)
			return 1;
		else if(x>=755 && x<=855)
			return 2;
		else if(x>=885 && x<=985)
			return 3;
		else if(x>=1015 && x<=1115)
			return 4;
		else if(x>=1145 && x<=1245)
			return 5;
		else if(x>=1275 && x<=1375)
			return 6;
		else
			return -1;
	}

	public void reset() {
//		pedine=new ArrayList<>();
//		generaGettoni();
//		this.repaint();
		
	}
}
