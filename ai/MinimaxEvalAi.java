package ai;

import java.util.List;
import java.util.logging.Logger;

public class MinimaxEvalAi implements MinimaxInterface{
	
	private static final int TAGLIO = 5;
	private static Logger logger=Logger.getGlobal();
	
	public Mossa trovaMossa(Tavolo tavolo) {
		if(tavolo.valutazione()==1)
			return tavolo.getCasuale();
		
		int taglio=0;
		return valore_max(tavolo, Integer.MIN_VALUE, Integer.MAX_VALUE,taglio).getMossa();
		
	}

	private MossaPunteggio valore_max(Tavolo tavolo, int alfa, int beta, int taglio) {
		logger.info("stampo taglio: "+ taglio);
		logger.info("sono in max ");
		List<Mossa> mosse=tavolo.mossePossibili("max");

		int migliorPunteggio = Integer.MIN_VALUE;
		Mossa migliorMossa = null;
		
		if(taglio > TAGLIO /*|| mosse.isEmpty() || tavolo.controllaFineGioco()*/) {
			migliorPunteggio = tavolo.valutazione();
			System.out.println("valutazione: "+ tavolo.valutazione());
			tavolo.toString();
		}
		else {
			for(Mossa mossa: mosse) {
				mossa.esegui();
				int punteggio = valore_min(tavolo, alfa, beta, taglio+1).getPunteggio();
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
	
	private MossaPunteggio valore_min(Tavolo tavolo, int alfa, int beta, int taglio) {
		logger.info("stampo taglio: "+ taglio);
		logger.info("Sono in min");
		int migliorPunteggio = Integer.MAX_VALUE;
		Mossa migliorMossa = null;
		List<Mossa> mosse=tavolo.mossePossibili("min");

		if(taglio > TAGLIO /*|| mosse.isEmpty() || tavolo.controllaFineGioco()*/)
			migliorPunteggio = tavolo.valutazione();
		else {
			for(Mossa mossa: mosse) {
				mossa.esegui();
				int punteggio = valore_max(tavolo, alfa, beta, taglio+1).getPunteggio();
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
