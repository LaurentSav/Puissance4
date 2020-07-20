public class Coup{

	/* Attribut */
	private int eval;
	private int colonne;

	/* Constructeur */
	public Coup(int val, int c){
		this.eval = val;
		this.colonne = c;
	}
	
	/* Méthode */

	/* Fonction : Retourne l'attribut eval*/
	public int getEval(){
		return this.eval;
	}

	/* Fonction : Retourne l'attribut Colonne*/
	public int getColonne(){
		return this.colonne;
	}
	

}