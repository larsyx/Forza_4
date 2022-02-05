package forza_4;





public class TavoloLogic {

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
				tavolo[i][j]="";
	}
	
	public int inserisciPedina(int index, String giocatore) {
		
		int i=1000;
		if(index>=0 && index <=6)
			for(i=0 ; i<6 ; i++)
				if(tavolo[i][index].equals("")) {
					tavolo[i][index] = giocatore;
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
	
	

	
	public boolean controllaVincitoreX(String turno) {
		
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



	
}
