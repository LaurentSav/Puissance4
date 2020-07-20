
public class Noeud {

	/* Attribut */
    public int [][] matrice;
    private boolean max;
    private int h;

	/* Constructeur */
    public Noeud(boolean max, int[][] matrice) {
        this.max = max;
        this.matrice = matrice;
    }

	/* Méthode */

	/* Fonction : Retourne H */
    public int getH(){
        return this.h;
    }
	/* Fonction : Changer la valeur de H */
    public void setH(int h){
        this.h = h;
    }

	/* Fonction : Changer la valeur de la matrice */
    public void setMatrice(int[][] matrice){
        this.matrice = matrice;
    }

	/* Fonction : Retourne la matrice */
    public int [][] getMatrice(){
        return this.matrice;
    }

	/* Fonction : Retourn la valeur max (true or false) */
    public boolean isMax(){
        return this.max;
    }

	/* Fonction : Changer la valeur de max */
    public void setMax(boolean max){
        this.max = max;
    }

	/* Fonction : Retourne la matrice sous forme d'un String */
    public String toString() {
		String res = "\n";
		for (int i = 0; i < this.matrice.length; i++) {
				res= res + "|";
				for (int j = 0; j < this.matrice[i].length; j++) {
						res = res + this.matrice[i][j] + "|";
				}
				res = res + "\n";
		}
		return res;
    }

	
	/* Fonction : Vérification 4 pions alignés en ligne */
    public int QuatrePionsAlignesLigne(boolean typeJoueur){
		
		/* Initialisation */

		/* Joueur ou IA */
		int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}

        int comptPion=0;
        int curseurcolonne;
        int ligne = this.matrice.length;
        int colonne = this.matrice[0].length - 2;

		/* Vérification */

        for(int i=0; i<ligne; i++){
            for (int j=0;j<colonne;j++){
                curseurcolonne=j;
                while(curseurcolonne<this.matrice[i].length && this.matrice[i][curseurcolonne] == symbolJoueur && comptPion<4){
                    comptPion = comptPion + 1;
					curseurcolonne = curseurcolonne + 1;

                }
                if(comptPion==4) return 1000;
                comptPion=0;
            }
        }
        return 0;
    }

	/* Fonction : Vérification 4 pions alignés en colonne */
    public int QuatrePionsAlignesColonne(boolean typeJoueur){

		/* Initialisation */

		/* Joueur ou IA */
		int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}

		int comptPion=0;
        int curseurligne;
        int ligne = this.matrice.length - 1;
        int colonne = this.matrice[0].length;

		/* Vérification */

        for(int i=ligne; i>=3; i--){
            curseurligne=i;
            for (int j=0;j<colonne;j++){
                while(curseurligne>=0 &&this.matrice[curseurligne][j] == symbolJoueur && comptPion<4){
					comptPion = comptPion + 1;
                    curseurligne = curseurligne - 1;
                    
                }
                if(comptPion==4) return 1000;
                comptPion=0;
                curseurligne=i;
            }
        }
        return 0;
    }

	/* Fonction : Vérification 4 pions alignés en diagonale droite*/
    public int QuatrePionsAlignesdiagonaleDroite(boolean typeJoueur) { 

		/* Initialisation */

		/* Joueur ou IA */
		int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}

		int curseurligne, curseurcolonne;
        int comptPion = 0;
        int ligne = this.matrice.length;
        int colonne = this.matrice[0].length;

		/* Vérification */

        for (int i = ligne - 1; i >= 3; --i)
        {
            curseurligne = i;
            for (int j = 0; j <= colonne - 3; ++j)
            {
                curseurcolonne = j;
                if (this.matrice[i][j] == symbolJoueur) {
                    while ((curseurligne >= 0) && (curseurcolonne < colonne) && (this.matrice[curseurligne][curseurcolonne] == symbolJoueur && comptPion < 4)) {
                        comptPion = comptPion + 1;
                        curseurligne = curseurligne - 1;
                        curseurcolonne = curseurcolonne + 1;
                    }
                    if (comptPion == 4)  
                    {
                        return 1000;

                    }
                }
                comptPion = 0;
            }
        }
        return 0;
    }

	/* Fonction : Vérification 4 pions alignés en diagonale gauche*/
    public int QuatrePionsAlignesdiagonaleGauche(boolean typeJoueur) {

		/* Initialisation */
		
		/* Joueur ou IA */
		int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}

        int curseurligne, curseurcolonne;
        int comptPion = 0;
        int ligne = this.matrice.length;
        int colonne = this.matrice[0].length;

		/* Vérification */
		
        for (int i = ligne - 1; i >= 3; i--)  
        {
            curseurligne = i;
            for (int j = colonne - 1; j >= 3; j--)    
            {
                curseurcolonne = j;
                if (this.matrice[i][j] == symbolJoueur) {
                    while ((curseurligne >= 0) && (curseurcolonne >= 0) && (this.matrice[curseurligne][curseurcolonne] == symbolJoueur) && (comptPion < 4)) {
                        curseurligne = curseurligne - 1;      
						
                        curseurcolonne = curseurcolonne - 1;
                        comptPion = comptPion + 1;
                    }
                    if (comptPion == 4)
                    {
                        return 1000;
                    }
                    curseurcolonne = j;
                    curseurligne = i;
                    comptPion = 0;
                }
            }
        }
        return 0;
    }

	/* Fonction : Vérification Match nul, lorsque toute les cases de la grille sont remplit */
    public int matchNul(boolean typeJoueur){
		/* Initialisation */
        int compteur = 0;
        int ligne = this.matrice.length;
        int colonne = this.matrice[0].length;
		for(int j=0; j < colonne; j++){
			if(this.matrice[0][j] != 0){
				compteur = compteur + 1;
			}
		}
        if(compteur == ligne) {
            return 1000;
        }
        return 0;
    }

	/* Fonction : Evaluation ligne*/
    public int QuatrePionsPossiblesLigne(boolean typeJoueur) {
		
		/* Initialisation */

        /* Joueur vs Ia */
        int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}
		int evaluation = 0;
        int colonne = this.matrice[0].length;

		/* Evaluation */

        for (int i = 0; i < this.matrice.length; i++) {
            for (int j = 0; j < colonne; j++) {
                if(this.matrice[i][j] == 0) {

					// 0 1 1 1
                    if(j+3 < colonne){ 
                        if (this.matrice[i][j + 1] == symbolJoueur && this.matrice[i][j + 2] == symbolJoueur && this.matrice[i][j + 3] == symbolJoueur){
							evaluation = evaluation + 600;
                        }
                    }
					// 1 0 1 1
                    if((j-1 >= 0) && (j+2 < colonne)){ 
                        if (this.matrice[i][j - 1] == symbolJoueur && this.matrice[i][j + 1] == symbolJoueur && this.matrice[i][j + 2] == symbolJoueur){
							evaluation = evaluation + 600;
                        }
                    }
					// 1 1 0 1
                    if((j-2 >= 0) && (j+1 < colonne)){ 
                        if (this.matrice[i][j - 2] == symbolJoueur && this.matrice[i][j - 1] == symbolJoueur && this.matrice[i][j + 1] == symbolJoueur){
                            
							evaluation = evaluation + 600;

                        }
                    }
					
                    // 1 1 1 0
                    if(j-3 >= 0){ 
                        if (this.matrice[i][j - 3] == symbolJoueur && this.matrice[i][j - 2] == symbolJoueur && this.matrice[i][j - 1] == symbolJoueur){
							
                            evaluation = evaluation + 600;
                        }
                    }

					// 0 1 1
                    if(j+2 < colonne){
                        if (this.matrice[i][j + 1] == symbolJoueur && this.matrice[i][j + 2] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }
					// 1 0 1
                    if((j-1) >= 0 && (j+1 < colonne)){
                        if (this.matrice[i][j - 1] == symbolJoueur && this.matrice[i][j + 1] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }
										
                    // 1 1 0
                    if(j-2 >= 0){
                        if (this.matrice[i][j - 2] == symbolJoueur && this.matrice[i][j - 1] == symbolJoueur) {
							
                            evaluation = evaluation + 200;
                        }
                    }
					// 1 0
                    if(j-1 >= 0){
                        if (this.matrice[i][j - 1] == symbolJoueur) {
							
                            evaluation = evaluation + 30;
                        }
                    }
					// 0 1
                    if(j+1 < colonne){
                        if (this.matrice[i][j + 1] == symbolJoueur) {
                            evaluation = evaluation + 30;
                        }
                    }
                }
            }
        }
		
        return evaluation;
    }

	/* Fonction : Evaluation Colonne*/
	public int QuatrePionsPossiblesColonne(boolean typeJoueur){

		/* Initialisation */

		/* Joueur vs Ia */
		int symbolJoueur;
		if(typeJoueur){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}

		int evaluation = 0;
		int ligne = this.matrice.length;

		/* Evaluation */

		for (int i = ligne-1; i >=0; i--) {
			for (int j = 0; j < this.matrice[0].length; j++) {
				if(this.matrice[i][j] == 0) {

					// 0 1 1 1
					if(i+3 < ligne){ 
						if (this.matrice[i + 1][j] == symbolJoueur && this.matrice[i + 2][j] == symbolJoueur && this.matrice[i + 3][j] == symbolJoueur){
							evaluation = evaluation + 600;
						}
					}
					// 1 0 1 1
					if((i-1 >= 0) && (i+2 < ligne)){ 
						if (this.matrice[i - 1][j] == symbolJoueur && this.matrice[i + 1][j] == symbolJoueur && this.matrice[i + 2][j] == symbolJoueur){
							evaluation = evaluation + 600;
						}
					}
					// 1 1 0 1
					if((i-2 >= 0) && (i+1 < ligne)){ 
						if (this.matrice[i - 2][j] == symbolJoueur && this.matrice[i - 1][j] == symbolJoueur && this.matrice[i + 1][j] == symbolJoueur){
							evaluation = evaluation + 600;
						}
					}
					// 1 1 1 0
					if(i-3 >= 0){ 
						if (this.matrice[i - 3][j] == symbolJoueur && this.matrice[i - 2][j] == symbolJoueur && this.matrice[i - 1][j] == symbolJoueur){
							evaluation = evaluation + 600;
						}
					}

					// 0 1 1
					if(i+2 < ligne){
						if (this.matrice[i + 1][j] == symbolJoueur && this.matrice[i + 2][j] == symbolJoueur) {
							evaluation = evaluation + 200;
						}
					}
					// 1 0 1
					if((i-1 >= 0) && (i+1 < ligne)){
						if (this.matrice[i - 1][j] == symbolJoueur && this.matrice[i + 1][j] == symbolJoueur) {
							evaluation = evaluation + 200;
						}
					}
					// 1 1 0
					if(i-2 >= 0){
						if (this.matrice[i - 2][j] == symbolJoueur && this.matrice[i - 1][j] == symbolJoueur) {
							evaluation = evaluation + 200;
						}
					}					
					// 0 1
					if(i+1 < ligne){
						if (this.matrice[i + 1][j] == symbolJoueur) {
							evaluation = evaluation + 30;
						}
					}
				}
			}
		}

		return evaluation;
	}
	
	/* Fonction : Evaluation Diagonale Droite*/
    public int QuatrePionsAlignesdiagonaleDroitePossible(boolean typeJoueur){
        
		/* Initialisation */

		/* Joueur vs Ia */
        int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		} else {
			symbolJoueur = 2;
		}

		int evaluation = 0;
        int ligne = this.matrice.length;
        int colonne = this.matrice[0].length;

		/* Evaluation */

        for (int i = ligne-1; i >=0; i--) {
            for (int j = 0; j < colonne; j++) {
                if(this.matrice[i][j] == 0) {

					// 0 1 1 1
                    if((i-3 >= 0) && (j+3 < colonne)){ 
                        if (this.matrice[i-1][j+1] == symbolJoueur && this.matrice[i-2][j+2] == symbolJoueur && this.matrice[i-3][j+3] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }
					// 1 0 1 1
                    if((i+1 < ligne) && (i-2 >= 0) && (j-1 >= 0) && (j+2 < colonne)){ 
                        if (this.matrice[i+1][j-1] == symbolJoueur && this.matrice[i-1][j+1] == symbolJoueur && this.matrice[i-2][j+2] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }
					// 1 1 0 1
                    if((i+2 < ligne) && (i-1 >= 0) && (j-2 >= 0) && (j+1 < colonne)){ 
                        if (this.matrice[i+2][j-2] == symbolJoueur && this.matrice[i+1][j-1] == symbolJoueur && this.matrice[i-1][j+1] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }

					// 1 1 1 0
                    if((i-3 >= 0) && (j-3 >= 0)){ 
                        if (this.matrice[i-3][j-3] == symbolJoueur && this.matrice[i-2][j-2] == symbolJoueur && this.matrice[i-1][j-1] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }

					// 0 1 1
                    if((i-2 >= 0) && (j+2 < colonne)){
                        if (this.matrice[i-1][j+1] == symbolJoueur && this.matrice[i-2][j+2] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }
					// 1 0 1
                    if(i-1 >= 0 && (i+1<ligne) && (j-1 >= 0) && (j+1 < colonne)){
                        if (this.matrice[i+1][j-1] == symbolJoueur && this.matrice[i-1][j+1] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }

                    // 1 1 0
                    if((i+2 < ligne) && (j-2 >= 0)){
                        if (this.matrice[i+2][j-2] == symbolJoueur && this.matrice[i+1][j-1] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }

					// 1 0
                    if((i+1 < ligne) && (j-1 >= 0)){
                        if (this.matrice[i+1][j-1] == symbolJoueur) {
                            evaluation = evaluation + 30;
                        }
                    }
					// 0 1
                    if((i-1 >= 0) && (j+1 < colonne)){
                        if (this.matrice[i-1][j+1] == symbolJoueur) {
                            evaluation = evaluation + 30;
                        }
                    }
                }
            }
        }
		
        return evaluation;
    }

	/* Fonction : Evaluation Diagonale Gauche*/
    public int QuatrePionsAlignesdiagonaleGauchePossible(boolean typeJoueur){
        
		/* Initialisation */

		/* Joueur vs IA */
        int symbolJoueur;
		if (typeJoueur == true){
			symbolJoueur = 1;
		}else{
			symbolJoueur = 2;
		}

		int evaluation = 0;
        int ligne = this.matrice.length;
        int colonne = this.matrice[0].length;

		/* Evaluation */

        for (int i = ligne-1; i >=0; i--) {
            for (int j = 0; j < colonne; j++) {
                if(this.matrice[i][j] == 0) {

					// 0 1 1 1
                    if((i+3<ligne) && (j+3<colonne)){ 
                        if (this.matrice[i+1][j+1] == symbolJoueur && this.matrice[i+2][j+2] == symbolJoueur && this.matrice[i+3][j+3] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }
					// 1 0 1 1
                    if((i-1>=0) && (i+2<ligne) && (j-1>=0) && (j+2)<colonne){ 
                        if (this.matrice[i-1][j-1] == symbolJoueur && this.matrice[i+1][j+1] == symbolJoueur && this.matrice[i+2][j+2] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }
					// 1 1 0 1
                    if((i-2>=0) && (i+1<ligne) && (j-2>=0) && (j+1<colonne)){ 
                        if (this.matrice[i-2][j-2] == symbolJoueur && this.matrice[i-1][j-1] == symbolJoueur && this.matrice[i+1][j+1] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }
					// 1 1 1 0
                    if((i+3<ligne) && (j-3>=0)){ 
                        if (this.matrice[i+3][j-3] == symbolJoueur && this.matrice[i+2][j-2] == symbolJoueur && this.matrice[i+1][j-1] == symbolJoueur){
                            evaluation = evaluation + 600;
                        }
                    }

					// 0 1 1
                    if((i+2<ligne) && (j+2<colonne)){
                        if (this.matrice[i+1][j+1] == symbolJoueur && this.matrice[i+2][j+2] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }
					// 1 0 1
                    if(i-1>=0 && i+1<ligne && j-1>=0 && j+1<colonne){
                        if (this.matrice[i-1][j-1] == symbolJoueur && this.matrice[i+1][j+1] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }
					// 1 1 0
                    if((i-2>=0) && (j-2>=0)){
                        if (this.matrice[i-2][j-2] == symbolJoueur && this.matrice[i-1][j-1] == symbolJoueur) {
                            evaluation = evaluation + 200;
                        }
                    }
					// 1 0
                    if(i-1>=0&& j-1>=0){
                        if (this.matrice[i-1][j-1] == symbolJoueur) {
                            evaluation = evaluation + 30;
                        }
                    }
					// 0 1
                    if(i+1<ligne && j+1<colonne){
                        if (this.matrice[i+1][j+1] == symbolJoueur) {
                            evaluation = evaluation + 30;
                        }
                    }
                }
            }
        }
        return evaluation;
    }

	/* Fonction : Evaluation dans H*/
	public void evaluer(){

			this.h = -2*this.QuatrePionsAlignesLigne(false)
					+ this.QuatrePionsAlignesLigne(true)
					-2*this.QuatrePionsAlignesColonne(false)
					+ this.QuatrePionsAlignesColonne(true)
					-2*this.QuatrePionsAlignesdiagonaleDroite(false)
					+ this.QuatrePionsAlignesdiagonaleDroite(true)
					-2*this.QuatrePionsAlignesdiagonaleGauche(false)
					+ this.QuatrePionsAlignesdiagonaleGauche(true)
					-2*this.QuatrePionsPossiblesLigne(false)
					+ this.QuatrePionsPossiblesLigne(true)
					-2*this.QuatrePionsPossiblesColonne(false)
					+ this.QuatrePionsPossiblesColonne(true)
					-2*this.QuatrePionsAlignesdiagonaleDroitePossible(false)
					+ this.QuatrePionsAlignesdiagonaleDroitePossible(true)
					-2*this.QuatrePionsAlignesdiagonaleGauchePossible(false)
					+ this.QuatrePionsAlignesdiagonaleGauchePossible(true);
	}
}
