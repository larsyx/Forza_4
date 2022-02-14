package ai;

import java.util.List;

public class MinimaxAi implements MinimaxInterface{

	
	public Mossa trovaMossa(Tavolo tavolo) {
		return valore_max(tavolo, Integer.MIN_VALUE, Integer.MAX_VALUE).getMossa();	
	}

	private MossaPunteggio valore_max(Tavolo tavolo, int alfa, int beta) {
		List<Mossa> mosse=tavolo.mossePossibili("max");
		
		int migliorPunteggio = Integer.MIN_VALUE;
		Mossa migliorMossa = null;
		
		if(mosse.isEmpty())
			migliorPunteggio = tavolo.utilita();
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
				mossa.annulla();
			}
		}
		return new MossaPunteggio(migliorMossa, migliorPunteggio);
	}
	
	private MossaPunteggio valore_min(Tavolo tavolo, int alfa, int beta) {
		int migliorPunteggio = Integer.MAX_VALUE;
		Mossa migliorMossa = null;
		List<Mossa> mosse=tavolo.mossePossibili("min");

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
					beta = Math.min(beta, migliorPunteggio);		
				}
				mossa.annulla();
			}
		}
		return new MossaPunteggio(migliorMossa, migliorPunteggio);
	}
}
