package ai;

import java.util.List;

public interface Tavolo {

	List<Mossa> mossePossibili();
	int utilita();
	boolean controllaFineGioco();
}
