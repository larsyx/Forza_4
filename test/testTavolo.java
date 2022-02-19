package test;

import java.util.List;
import java.util.Random;

import ai.Mossa;
import forza_4.TavoloLogic;

public class testTavolo {

	public static final String g="giocatore";
	public static final String c="computer";
	
	
	public static void main(String[] args) {
		String turno = "giocatore";
		TavoloLogic tavolo=new TavoloLogic();
		for(int i=0;i<25;i++) {
			if(turno.equals("giocatore"))
				turno = "computer";
			else
				turno = "giocatore";
			
			
			List<Mossa> mosse= tavolo.mossePossibili(turno);
			Random rand=new Random();
			mosse.get(rand.nextInt(mosse.size())).esegui();;
			
		}
		
		tavolo= new TavoloLogic();
		tavolo.inserisciPedina(0, c);
		tavolo.inserisciPedina(0, c);
		tavolo.inserisciPedina(0, c);
		tavolo.inserisciPedina(0, g);
	//	tavolo.inserisciPedina(0, c);
	//	tavolo.inserisciPedina(0, c);
//		tavolo.inserisciPedina(1, g);
//		tavolo.inserisciPedina(1, g);
//		tavolo.inserisciPedina(1, c);
		//tavolo.inserisciPedina(1, c);
		tavolo.inserisciPedina(2, c);
		tavolo.inserisciPedina(2, c);
	//	tavolo.inserisciPedina(2, c);
	//	tavolo.inserisciPedina(3, c);
		tavolo.inserisciPedina(3, g);
		tavolo.inserisciPedina(3, g);
		tavolo.inserisciPedina(3, g);
		tavolo.inserisciPedina(3, c);
		tavolo.inserisciPedina(4, g);
		tavolo.inserisciPedina(4, g);
		tavolo.inserisciPedina(5, g);
		
		System.out.println("stampo mosse max:");
		for(Mossa x: tavolo.mossePossibili("max"))
			System.out.println(x.toString()+ "  " + x.getTavolo().valutazione());

		System.out.println("stampo mosse min:");
		for(Mossa x: tavolo.mossePossibili("min"))
			System.out.println(x.toString()+ "  " + x.getTavolo().valutazione());
		
		tavolo.toString();
		System.out.println("stampo valutazione : " + tavolo.valutazione());
		/*System.out.println("stampo valutazione 3 computer: " + tavolo.controlla3("computer"));
		System.out.println("stampo valutazione 3 giocatore: " + tavolo.controlla3("giocatore"));
		*/
	}
}
