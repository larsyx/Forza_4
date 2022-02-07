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
import ai.Mossa;



public class Main {
	
	private static String modalita="";
	private static boolean turno=true;
	
	private static ImageIcon icona = new ImageIcon("./icone/icona.png");
	private static JPanel panel;
	private static JButton avvio;
	private static JButton riavvia;

	
	private static TavoloUI tavolo= new TavoloUI();
	private static TavoloLogic tavoloLogic=new TavoloLogic();
	
	private static Logger logger=Logger.getGlobal();
	
	private  static MouseListener listener = new Listener();
	
	public static void main(String[] args) {
		logger.setLevel(Level.OFF);
		
		JFrame frame=new JFrame();
		frame.setTitle("Forza 4");
		frame.setSize(1000,1000);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("./icone/icona_default.png").getImage());
		frame.setVisible(true);
		
		tavolo=new TavoloUI();
		JPanel pannello=new JPanel(new BorderLayout());
		pannello.add(tavolo, BorderLayout.CENTER);
		
		creaMenu();
		pannello.add(panel, BorderLayout.SOUTH);
		frame.add(pannello);
	}


	public static void creaMenu() {
		panel=new JPanel(new FlowLayout());
		
		avvio=new JButton("Avvio");
		avvio.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				logger.info("Avvio Clicked");
				
				String[] opzioni= {"Difficile", "Normale"};
				
				if((!modalita.equals(opzioni[0])) && (!modalita.equals(opzioni[1]))){
					int i=JOptionPane.showOptionDialog(null, "Quale modalit� di gioco scegli?", "Scegli modalit�", 0, 0, icona, opzioni, e);
					logger.info("risultato "+ i );
					switch(i) {
						case 0:	modalita = "Difficile"; break;
						case 1:	modalita = "Normale"; break;
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
				modalita = "";
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
		logger.info(modalita);
		if(modalita.equals("Difficile") || modalita.equals("Normale"))
			tavolo.addMouseListener(listener);
	}
	
	public static void disattivaTavolo() {
		tavolo.removeMouseListener(listener);
	}
	
	private static void fineGioco(String giocatore) {
		disattivaTavolo();
		
		JOptionPane.showMessageDialog(null, "Gioco finito ha vinto: " + giocatore, "Forza 4", 0, icona);
		
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
				/*int idGettone = tavoloLogic.inserisciPedina(colonna, "giocatore");
				tavolo.ridisegna(idGettone, "giocatore");
				if(tavoloLogic.controllaFineGioco()) {
					if(tavoloLogic.controllaVincitoreX("giocatore"))
						fineGioco("giocatore");
					else
						fineGioco("pareggio");
				}
				
				if(idGettone>=0 && idGettone<=42)
					turno=false;
				*/
				eseguiMossa(colonna, "giocatore");
			}
			else {
				
				/*MinimaxAi ai=new MinimaxAi();
				Mossa mossa = ai.trovaMossa(tavoloLogic);
				
				int idGettone = tavoloLogic.inserisciPedina(mossa.getColonna(), "giocatore");
				tavolo.ridisegna(idGettone, "giocatore");
				
				if(tavoloLogic.controllaFineGioco()) {
					if(tavoloLogic.controllaVincitoreX("giocatore"))
						fineGioco("giocatore");
					else
						fineGioco("pareggio");
				}
				
				if(idGettone>=0 && idGettone<=42)
					turno=true;
				*/
				int colonna=tavolo.inserisciGettone(e.getX());
				eseguiMossa(colonna, "computer");
				/*
				//da cancellare usato solo per l'implementazione
				int colonna=tavolo.inserisciGettone(e.getX());
				int idGettone = tavoloLogic.inserisciPedina(colonna, "computer");
				tavolo.ridisegna(idGettone, "computer");
				if(tavoloLogic.controllaFineGioco()) {
					if(tavoloLogic.controllaVincitoreX("computer"))
						fineGioco("computer");
					else
						fineGioco("pareggio");
				}
				
				if(idGettone>=0 && idGettone<=42)
					turno=true;
				*/	
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
		}
		
		if(idGettone>=0 && idGettone<=42) {
			if(giocatore.equals("giocatore"))
				turno=false;
			if(giocatore.equals("computer"))
				turno=true;
		}
	}
}
