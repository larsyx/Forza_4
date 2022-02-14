package ai;

import java.util.List;

public interface Tavolo {

	List<Mossa> mossePossibili(String turno);
	int utilita();
	int valutazione();
	boolean controllaFineGioco();
	Mossa getCasuale();
	
}
