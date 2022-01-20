
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;


public class Main {

	private static boolean turno;
	private static Map<Integer, String> gettoni;
	
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
			gettoni.put(i, null);
		
		Scacchiera scacchiera=new Scacchiera();
		frame.add(scacchiera);
		
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
							if(gettoni.get((i*7)+colonna)==null) {
								idGettone=(i*7)+colonna;
								gettoni.remove(idGettone);
								gettoni.put(idGettone, "giocatore");
								break;
							}		
						}
						if(idGettone != -1) {
							controllaFineGioco(idGettone);
							scacchiera.ridisegna(idGettone, "giocatore");
							
						}
					}
					
					turno=false;
				}
				else {
					//da cancellare usato solo per l'implementazione
					int colonna=scacchiera.inserisciGettone(e.getX());
					if(colonna!=-1) {
						int idGettone=-1;
						for(int i=5; i>=0;i--) {
							if(gettoni.get((i*7)+colonna)==null) {
								idGettone=(i*7)+colonna;
								gettoni.put(idGettone, "computer");
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
	

	public static boolean controllaFineGioco(int id) {
		int consecutivi=1;
		String utente=gettoni.get(id);
		
		//controllo orizzontale
		if(gettoni.get(id+1).equals(utente))
			if(gettoni.get(id+2).equals(utente))
				if(gettoni.get(id+3).equals(utente))
					return true;
				else if(gettoni.get(id-1).equals(utente))
					return true;
		
		System.out.println(utente+" ha vinto");
		return false;
	}


}

