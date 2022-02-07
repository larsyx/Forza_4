package ai;

public class MossaPunteggio {

        private final Mossa mossa;
        private final int punteggio;

        public MossaPunteggio(Mossa mossa, int punteggio) {
            this.mossa = mossa;
            this.punteggio = punteggio;
        }

        public Mossa getMossa() {
            return mossa;
        }

        public int getPunteggio() {
            return punteggio;
        }
    
}
