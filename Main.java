
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Main {

	private static String modalita="";
	private static boolean turno=true;
	
	
	private static JPanel panel;
	private static JButton avvio;
	private static JButton riavvia;

	
	private static Tavolo tavolo= new Tavolo();
	private static TavoloLogic tavoloLogic=new TavoloLogic();
	
	private static Logger logger=Logger.getGlobal();
	
	private  static MouseListener listener = new Listener();
	
	public static void main(String[] args) {
		//logger.setLevel(Level.OFF);
		
		JFrame frame=new JFrame();
		frame.setTitle("Forza 4");
		frame.setSize(1000,1000);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("icona.png").getImage());
		frame.setVisible(true);
		

		tavolo=new Tavolo();
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
					int i=JOptionPane.showOptionDialog(null, "Quale modalità di gioco scegli?", "Scegli modalità", 0, 0, null, opzioni, e);
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
		System.out.println("GIoco finito ha vinto "+ giocatore);
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
				int idGettone = tavoloLogic.inserisciPedina(colonna, "giocatore");
//				if(tavoloLogic.controllaVincitoreX("giocatore"))
//					fineGioco("giocatore");
				tavolo.ridisegna(idGettone, "giocatore");
				tavoloLogic.toString();
				turno=false;
			}
			else {
				//da cancellare usato solo per l'implementazione
				int colonna=tavolo.inserisciGettone(e.getX());
				int idGettone = tavoloLogic.inserisciPedina(colonna, "computer");
				tavoloLogic.controllaVincitoreX("giocatore");
//				if(tavoloLogic.controllaVincitoreX("giocatore"))
//					fineGioco("computer");
				tavolo.ridisegna(idGettone, "computer");
				tavoloLogic.toString();
				turno=true;
			}
		}	
	}
	
	
}

