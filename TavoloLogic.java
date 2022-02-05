




public class TavoloLogic {

	private String[][] tavolo;
	
	public TavoloLogic() {
		tavolo= new String[6][7];
	}
	
	
	public String[][] getTavolo() {
		return tavolo;
	}
	
	public int inserisciPedina(int index, String giocatore) {
		System.out.println(index + " " + giocatore);
		int i=-1;
		if(index>=0 && index <=6)
			for(i=0 ; i<6 ; i++)
				if(tavolo[i][index] == null) {
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
		return false;
	}


	public void reset() {
		tavolo = new String[6][7];
		
	}
	
}
