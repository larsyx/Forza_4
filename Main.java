import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(1900,1080);
		frame.setTitle("Forza 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setSize(1900, 900);
		
		Scacchiera scacchiera=new Scacchiera();
		
		frame.add(scacchiera);
		scacchiera.ridisegna(0,"computer");
		scacchiera.ridisegna(1,"giocatore");
		
		scacchiera.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}

