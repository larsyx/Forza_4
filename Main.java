
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;


public class Main {

	private static boolean turno;
	private static Map<Integer, String> pedine;
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(1900,1080);
		frame.setTitle("Forza 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.setSize(1900, 900);
		turno=true;
		pedine=new HashMap<>();
		
		for(int i=0;i<42;i++)
			pedine.put(i, null);
		
		Tavolo tavolo=new Tavolo();
		frame.add(tavolo);
		
		tavolo.addMouseListener(new MouseListener() {
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
					int colonna=tavolo.inserisciGettone(e.getX());
					if(colonna!=-1) {
						int idGettone=-1;
						for(int i=5; i>=0;i--) {
							if(pedine.get((i*7)+colonna)==null) {
								idGettone=(i*7)+colonna;
								pedine.put(idGettone, "giocatore");
								break;
							}		
						}
						if(idGettone != -1) {
							//controllaFineGioco(idGettone);
							tavolo.ridisegna(idGettone, "giocatore");
							
						}
					}
					
					turno=false;
				}
				else {
					//da cancellare usato solo per l'implementazione
					int colonna=tavolo.inserisciGettone(e.getX());
					if(colonna!=-1) {
						int idGettone=-1;
						for(int i=5; i>=0;i--) {
							if(pedine.get((i*7)+colonna)==null) {
								idGettone=(i*7)+colonna;
								pedine.put(idGettone, "computer");
								break;
							}		
						}
						if(idGettone != -1)
							tavolo.ridisegna(idGettone, "computer");
					}
					turno=true;
				}
			}
		});
	
	}
	

	/*public static boolean controllaFineGioco(int id) {
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
	}*/


}

