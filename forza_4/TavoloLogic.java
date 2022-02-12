package forza_4;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import ai.Mossa;
import ai.Tavolo;

public class TavoloLogic implements Tavolo{

	private static Logger logger=Logger.getGlobal();
	private static final String EMPTY="";
	private static final String UTENTE="rosso";
	private static final String COMPUTER="verde";

	private String[][] tavolo;
	
	public TavoloLogic() {
		reset();
	}
	
	public String[][] getTavolo() {
		return tavolo;
	}
	
	public void reset() {
		tavolo = new String[6][7];
		for(int i=0; i<6; i++)
			for(int j=0; j<7; j++)
				tavolo[i][j]=EMPTY;
	}
	
	public int inserisciPedina(int index, String giocatore) {
		
		String turno;
		if(giocatore.equals("giocatore"))
			turno = UTENTE;
		else
			turno = COMPUTER;
		
		int i=1000;
		if(index>=0 && index <=6)
			for(i=0 ; i<6 ; i++)
				if(tavolo[i][index].equals("")) {
					tavolo[i][index] = turno;
					break;
				}
		
		return (7 * (5 - i))+index;
	}
	
	
	public String toString() {
		System.out.println("Stampo tavolo:");
		for(int i=0; i<7 ; i++) {
			System.out.println("\nstampo colonna: "+ i);
			for(int j=0 ; j<6 ; j++)
				System.out.print(" "+tavolo[j][i]+" ");
		}
		System.out.println("\n");
		return super.toString();
	}
	
	
	public boolean controllaFineGioco() {
		if(controllaVincitoreX("giocatore"))
			return true;
		
		if(controllaVincitoreX("computer"))
			return true;
		
		for(int i = 0; i<7; i++)
			if(tavolo[5][i].equals(EMPTY))
				return false;
		
		return true;
	}

	
	public boolean controllaVincitoreX(String giocatore) {
		
		String turno;
		if(giocatore.equals("giocatore"))
			turno = UTENTE;
		else
			turno = COMPUTER;
		
		boolean win;
		//controllo verticale
		for(int i=0; i<7; i++) {
            win = true; 
			for(int j=0; j<4; j++) {
                if(!tavolo[j][i].equals(turno)) 
                    win = false;
                
                if(!win) 
                    break;
			}
			if(win)
				return true;
			
			win = true; 
			for(int j=1; j<5; j++) {
                if(!tavolo[j][i].equals(turno)) 
                	win = false;
                
                if(!win) 
                    break; 
			}
			if(win)
				return true;
			
			win = true; 
			for(int j=2; j<6; j++) {
                if(!tavolo[j][i].equals(turno)) 
                	win = false;
                
                if(!win) 
                    break;   
			}
			if(win)
				return true;
		}
		
		//controllo orizzontale
		for(int i=0; i<6; i++) {
			win = true; 
			for(int j=0; j<4; j++) {
                if(!tavolo[i][j].equals(turno)) 
                	win = false;
                
                if(!win) 
                    break;
			}
			if(win)
				return true;
			
			win = true; 
			for(int j=1; j<5; j++) {
                if(!tavolo[i][j].equals(turno)) 
                	win = false;
                
                if(!win) 
                    break; 
			}
			if(win)
				return true;
			
			win = true; 
			for(int j=2; j<6; j++) {
                if(!tavolo[i][j].equals(turno)) 
                	win = false;
                
                if(!win) 
                    break;   
			}
			if(win)
				return true;
			
			win = true;
			for(int j=3; j<7; j++) {
                if(!tavolo[i][j].equals(turno)) 
                	win = false;
                
                if(!win) 
                    break;   
			}
			if(win)
				return true;
		}
		
		//controllo diagonale
		for(int k=0; k<3; k++) {
			for(int x=0; x<4;x++) {
				//verso destra
				win = true; 
				for(int i=0, j=0 ; i<4 && j<4 ; i++, j++) {
					
		            if(!tavolo[i+k][j+x].equals(turno)) 
		            	win = false;
		                
		            if(!win) 
		                break;	
				}
				if(win)
					return true;
				
				//verso sinistra
				win = true; 
				for(int i=3, j=0 ; i>=0 && j<4 ; i--, j++) {
		            if(!tavolo[i+k][j+x].equals(turno)) 
		            	win = false;
		                
		            if(!win) 
		                break;	
				}
				if(win)
					return true;
			}
		}
		return false;
	}

	
	public List<Mossa> mossePossibili(String turno){
		ArrayList<Mossa> mosse=new ArrayList<>();
		for(int i=0; i<7; i++)
			if(tavolo[5][i].equals(EMPTY)) {
				Mossa mossa= new Forza_4Mossa(this, i, prossimoTurno());
				mossa.esegui();
				int utilita = mossa.getTavolo().utilita();
				mossa.annulla();
				if(utilita >= 3 || utilita <= -3) 
					mosse.add(0, mossa);
				else
					mosse.add(mossa);
					
				
			}
		
		logger.info("stampo size mosse possibili: "+ mosse.size());
		return mosse;
	}
	
	

	public String prossimoTurno(){
		int nu=0;
		int nc=0;
		for(int x=0; x<7;x++) {
			for(int i=0; i<6; i++) {
				if(tavolo[i][x].equals(UTENTE))
					nu++;
				else if(tavolo[i][x].equals(COMPUTER))
					nc++;
			}	
		}
		
		if((nu-nc)==1)
			return "computer";
		if((nu-nc)==0)
			return "giocatore";
		
		return null;
	}
	
	public int utilita() {
		if(controllaVincitoreX("giocatore"))
			return -4;
		
		if(controllaVincitoreX("computer"))
			return 4;
		
		if(controlla3("giocatore"))
			return -3;
		
		if(controlla3("utente"))
			return 3;
		
		if(controlla2("giocatore"))
			return -2;
		
		if(controlla2("computer"))
			return 2;
		
		
		return 1;	
		
		
	}

	
	private boolean controlla2(String giocatore) {
		String turno;
		
		if(giocatore.equals("giocatore"))
			turno = UTENTE;
		else
			turno = COMPUTER;
		
		for(int x=1; x<6; x++)
			for(int k=1; k<5; k++) {
				if(tavolo[x][k].equals(EMPTY))
					break;
				if(tavolo[x][k].equals(turno))
					for(int i=-1; i<2; i++) 
						for(int y=-1; y<2; y++)
							if(tavolo[x+i][k+y].equals(turno))
								return true;
			}
		
		//controllo rimanenti
		for(int i=0; i<5; i++) {
			if(tavolo[i][0].equals(turno))
				if(tavolo[i+1][0].equals(turno))
					return true;
			if(tavolo[i][6].equals(turno))
				if(tavolo[i+1][6].equals(turno))
					return true;
		}
		
		for(int i=0; i<6; i++) {
			if(tavolo[0][i].equals(turno))
				if(tavolo[0][i+1].equals(turno))
					return true;
			if(tavolo[5][i].equals(turno))
				if(tavolo[5][i+1].equals(turno))
					return true;
		}
		return false;
	}

	private boolean controlla3(String giocatore) {
		String turno;
		if(giocatore.equals("giocatore"))
			turno = UTENTE;
		else
			turno = COMPUTER;
		
		boolean win;
		//controllo verticale
		for(int i=0; i<7; i++) {
			for(int k=0; k<3; k++) {
	            win = true; 
				for(int j=0; j<k+3; j++) {
	                if(!tavolo[j+k][i].equals(turno)) 
	                    win = false;
	                
	                if(!win) 
	                    break;
				}
				if(win)
					return true;
			}
				
		}
		
		//controllo orizzontale
		for(int i=0; i<6; i++) {
			for(int k=0; k<4; k++) {
				win = true; 
				for(int j=0; j<k+3; j++) {
	                if(!tavolo[i][j+k].equals(turno)) 
	                	win = false;
	                
	                if(!win) 
	                    break;
				}
				if(win)
					return true;
			}
		}
					
		//controllo diagonale
		for(int k=0; k<4; k++) {
			for(int x=0; x<5;x++) {
				//verso destra
				win = true; 
				for(int i=0, j=0 ; i<3 && j<3 ; i++, j++) {
		            if(!tavolo[i+k][j+x].equals(turno)) 
		            	win = false;
		                
		            if(!win) 
		                break;	
				}
				if(win)
					return true;
				
				//verso sinistra
				win = true; 
				for(int i=2, j=0 ; i>=0 && j<3 ; i--, j++) {
		            if(!tavolo[i+k][j+x].equals(turno)) 
		            	win = false;
		                
		            if(!win) 
		                break;	
				}
				if(win)
					return true;
			}
		}
		return false;
	}

	


	public class Forza_4Mossa implements Mossa{

		private TavoloLogic tavolo;
		private int colonna;
		private String giocatore;
		
		public Forza_4Mossa(TavoloLogic tavolo, int colonna, String giocatore) {
			this.tavolo = tavolo;
			this.colonna = colonna;
			this.giocatore = giocatore;
		}
		
		@Override
		public void esegui() {
			tavolo.inserisciPedina(colonna, giocatore);
		}

		@Override
		public void annulla() {
			if(!tavolo.tavolo[5][colonna].equals(EMPTY))
				tavolo.tavolo[5][colonna] = EMPTY;
			else {
				int i=0;
				for(i=0; i<6 ;i++) {
					if(tavolo.tavolo[i][colonna].equals(EMPTY))
						break;	
				}
				tavolo.tavolo[i-1][colonna] = EMPTY;
			}
		}

		@Override
		public String toString() {
			return "Forza_4Mossa [tavolo=" + tavolo + ", colonna=" + colonna + ", giocatore=" + giocatore + "]";
		}

		@Override
		public int getColonna() {
			return colonna;
		}
		
		public Tavolo getTavolo() {
			return tavolo;
		}
	}
	


	@Override
	public Mossa getCasuale() {
		Random rand=new Random();
		
		Mossa mossa= new Forza_4Mossa(this, rand.nextInt(7), COMPUTER);
		
		return mossa;
	}
	
}
