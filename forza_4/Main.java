package forza_4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ai.MinimaxAi;
import ai.MinimaxEvalAi;
import ai.MinimaxInterface;
import ai.Mossa;



public class Main {

	private static boolean turno=true;
	
	private static ImageIcon icona = new ImageIcon("./icone/icona.png");
	private static JPanel panel;
	private static JButton avvio;
	private static JButton riavvia;
	private static JFrame frame;

	
	private static TavoloUI tavolo= new TavoloUI();
	private static TavoloLogic tavoloLogic=new TavoloLogic();
	private static MinimaxInterface algoritmo=null;
	
	private static Logger logger=Logger.getGlobal();
	
	private  static MouseListener listener = new Listener();
	
	public static void main(String[] args) {
		logger.setLevel(Level.OFF);

		frame=new JFrame();
		frame.setTitle("Forza 4");
		frame.setSize(1800,1100);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("./icone/icona_default.png").getImage());
		frame.setVisible(true);
		
		
		
		tavolo=new TavoloUI();
		JPanel pannello=new JPanel(new BorderLayout());
		pannello.add(tavolo, BorderLayout.CENTER);
		
		creaMenu();
		pannello.add(panel, BorderLayout.SOUTH);
		frame.add(pannello);
		
		frame.revalidate();
	}


	public static void creaMenu() {
		panel=new JPanel(new FlowLayout());
		
		avvio=new JButton("Avvio");
		avvio.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logger.info("Avvio Clicked");
				
				String[] opzioni= {"Difficile", "Normale"};
				
				if((algoritmo==null)){
					int i=JOptionPane.showOptionDialog(frame, "Quale modalità di gioco scegli?", "Scegli modalità", 0, 0, icona, opzioni, e);
					logger.info("risultato "+ i );
					switch(i) {
						case 0:	algoritmo=new MinimaxAi(); break;
						case 1:	algoritmo=new MinimaxEvalAi(); break;
						default: return;
					}
					avvio.setEnabled(false);
					attivaTavolo();
				}
			}
		});
		
		riavvia=new JButton("Reset");
		riavvia.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				logger.info("Riavvia Clicked");
				algoritmo = null;
				avvio.setEnabled(true);
				disattivaTavolo();
				
				tavolo.reset();
				tavoloLogic.reset();
				turno=true;
			}
		});
		
		panel.add(avvio);
		panel.add(riavvia);
	}
	
	public static void attivaTavolo() {
		if(algoritmo!=null)
			tavolo.addMouseListener(listener);
	}
	
	public static void disattivaTavolo() {
		tavolo.removeMouseListener(listener);
	}
	
	private static void fineGioco(String giocatore) {
		disattivaTavolo();
		String messaggioFineGioco; 
		if(giocatore.equals("pareggio")) 
			messaggioFineGioco =  "Gioco finito in ";
		else
			messaggioFineGioco =  "Gioco finito ha vinto: ";
		
		
		JOptionPane.showMessageDialog(frame, messaggioFineGioco + giocatore, "Forza 4", 0, icona);
		
	}
	
	private static void turnoComputer() {
		
		logger.info(algoritmo.toString());
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				long inizio = System.currentTimeMillis();
				Mossa mossa = algoritmo.trovaMossa(tavoloLogic);
				long fine = System.currentTimeMillis();
				System.out.println("tempo impiegato dal algoritmo: " + (fine-inizio) + "ms");
				eseguiMossa(mossa.getColonna(), "computer");
			}
		});
		
		thread.start();	
	}	
	
	public static class Listener implements MouseListener{
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(turno) {
				int colonna=tavolo.inserisciGettone(e.getX());
				eseguiMossa(colonna, "giocatore");
				
				//mossa computer
				
			}
			else {	
				/*MinimaxAi ai=new MinimaxAi();
				Mossa mossa = ai.trovaMossa(tavoloLogic);
				eseguiMossa(mossa.getColonna(), "computer");
				*/
				
				//int colonna=tavolo.inserisciGettone(e.getX());
				//eseguiMossa(colonna, "computer");		
			}
		}	
	}
	
	
	
	public static void eseguiMossa(int colonna, String giocatore) {

		int idGettone = tavoloLogic.inserisciPedina(colonna, giocatore);
		tavolo.ridisegna(idGettone, giocatore);
		if(tavoloLogic.controllaFineGioco()) {
			if(tavoloLogic.controllaVincitoreX(giocatore))
				fineGioco(giocatore);
			else
				fineGioco("pareggio");
			
			return;
		}
		
		if(idGettone>=0 && idGettone<=42) {
			if(giocatore.equals("giocatore")) {
				turno=false;
				turnoComputer();
			}
			if(giocatore.equals("computer")) {
				turno=true;
			}
		}
	}
}

