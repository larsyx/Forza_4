package ai;

import java.util.List;

public class MinimaxAi {

	
	public Mossa trovaMossa(Tavolo tavolo) {
		System.out.println("sono in trova mossa");
		return valore_max(tavolo, Integer.MIN_VALUE, Integer.MAX_VALUE).getMossa();
		
	}

	private MossaPunteggio valore_max(Tavolo tavolo, int alfa, int beta) {
		System.out.print("sono in max ");
		List<Mossa> mosse=tavolo.mossePossibili();
		int migliorPunteggio = Integer.MIN_VALUE;
		Mossa migliorMossa = null;
		
		if(mosse.isEmpty())
			migliorPunteggio= tavolo.utilita();
		else {
			for(Mossa mossa: mosse) {
				mossa.esegui();
				int punteggio = valore_min(tavolo, alfa, beta).getPunteggio();
				if(punteggio > migliorPunteggio) {
					migliorPunteggio = punteggio;
					migliorMossa = mossa;
					
					if(migliorPunteggio >= beta) {
						mossa.annulla();
						break;
					}
					alfa = Math.max(alfa, migliorPunteggio);		
				}
			}
		}
		
		return new MossaPunteggio(migliorMossa, migliorPunteggio);
	}
	
	private MossaPunteggio valore_min(Tavolo tavolo, int alfa, int beta) {
		int migliorPunteggio = Integer.MAX_VALUE;
		Mossa migliorMossa = null;
		List<Mossa> mosse=tavolo.mossePossibili();
		
		if(mosse.isEmpty())
			migliorPunteggio = tavolo.utilita();
		else {
			for(Mossa mossa: mosse) {
				mossa.esegui();
				int punteggio = valore_max(tavolo, alfa, beta).getPunteggio();
				if(punteggio < migliorPunteggio) {
					migliorPunteggio = punteggio;
					migliorMossa = mossa;
					
					if(migliorPunteggio <= alfa) {
						mossa.annulla();
						break;
					}
					beta = Math.min(alfa, migliorPunteggio);		
				}
			}
		}
		
		return new MossaPunteggio(migliorMossa, migliorPunteggio);
	}
}
