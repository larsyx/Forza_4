package test;

import java.util.List;
import java.util.Random;

import ai.Mossa;
import forza_4.TavoloLogic;

public class testTavolo {

	
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
		
		tavolo.toString();
		System.out.println("stampo valutazione 3 computer: " + tavolo.controlla3("computer"));
		System.out.println("stampo valutazione 3 giocatore: " + tavolo.controlla3("giocatore"));
		
	}
}
