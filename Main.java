
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;


public class Main {

	private static boolean turno;
	private static Map<Integer, Boolean> gettoni;
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(1900,1080);
		frame.setTitle("Forza 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setSize(1900, 900);
		turno=true;
		gettoni=new HashMap<>();
		
		for(int i=0;i<42;i++)
			gettoni.put(i, false);
		
		Scacchiera scacchiera=new Scacchiera();
		frame.add(scacchiera);
		scacchiera.ridisegna(0,"computer");
		scacchiera.ridisegna(1,"giocatore");
		
		scacchiera.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(turno) {
					int colonna=scacchiera.inserisciGettone(e.getX());
					if(colonna!=-1) {
						int idGettone=-1;
						for(int i=5; i>=0;i--) {
							if(!gettoni.get((i*7)+colonna)) {
								idGettone=(i*7)+colonna;
								gettoni.put(idGettone, true);
								break;
							}		
						}
						if(idGettone != -1)
							scacchiera.ridisegna(idGettone, "giocatore");
					}
					turno=false;
				}
				else {
					//da cancellare usato solo per l'implementazione
					int colonna=scacchiera.inserisciGettone(e.getX());
					if(colonna!=-1) {
						int idGettone=-1;
						for(int i=5; i>=0;i--) {
							if(!gettoni.get((i*7)+colonna)) {
								idGettone=(i*7)+colonna;
								gettoni.put(idGettone, true);
								break;
							}		
						}
						if(idGettone != -1)
							scacchiera.ridisegna(idGettone, "computer");
					}
					turno=true;
				}
			}
		});
		
		
		
	}
	
	


}

