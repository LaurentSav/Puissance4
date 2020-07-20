
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

import java.util.Scanner;

public class Puissance3 {

	/* Attribut */
    private int matriceJeu[][];
    public int HEIGHT = 8;
    public int WIDTH = 8;
    public int profondeur;

	/* Constructeur */
    public Puissance3() {
        this.matriceJeu = new int[this.HEIGHT][this.WIDTH];
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {

                this.matriceJeu[i][j] = 0;
            }
        }

		this.profondeur = this.HEIGHT-1;

    }

	/* Méthode */

	/* Fonction : Retourne la matrice du jeu */
    public int[][] getMatriceJeu() {
        return this.matriceJeu;
    }

	/* Fonction : Retourne False lorsque la colonne jouée est remplit, si la colonne est en dehors de grille de jeu
	Retourne true s'il est possible de jouer sur la colonne*/
    public boolean jouer(boolean typeJoueur, int colonne, int[][] matrice) {
        int SymbJoueur;
        int i = matrice.length - 1;

        if (typeJoueur == true) {
            SymbJoueur = 1;
        } else {
            SymbJoueur = 2;
        }

        if (colonne == -1) return false;
        if (colonne < 0 || colonne >= this.WIDTH || matrice[0][colonne] != 0) {
            return false;
        }

        while (i >= 0) {
            if (matrice[i][colonne] != 0) {
                i = i -1;
            } else {
                matrice[i][colonne] = SymbJoueur;
                return true;
            }
        }
        return false;
    }

	/* Fonction : Vérification fin de jeu */
    public boolean estFinJeu(boolean typeJoueur, int[][] matrice) {
		
        int finjeu;
        Noeud n = new Noeud(typeJoueur, matrice);

        if (n.QuatrePionsAlignesLigne(typeJoueur) == 1000 ||   n.QuatrePionsAlignesColonne(typeJoueur) == 1000 ||
		n.QuatrePionsAlignesdiagonaleDroite(typeJoueur) == 1000 || 
		n.QuatrePionsAlignesdiagonaleGauche(typeJoueur) == 1000) {
            return true;
        }

        finjeu = n.matchNul(typeJoueur);
        if (finjeu == 1000) {
            System.out.println("\n MATCH NUL. ");
            return true;
        }
        return false;
    }

	/* Fonction : Retourne la matrice sous forme d'un String */
    public String toString() {
		String res = "\n";
		for (int i = 0; i < this.HEIGHT; i++) {
				res= res + "|";
				for (int j = 0; j < this.WIDTH; j++) {
						res = res + this.matriceJeu[i][j] + "|";
				}
				res = res + "\n";
		}
		return res;
    }

	/* Fonction : Copie une matrice */
    public void copieMatrice(int[][] mSource, int[][] mDest) {
        for (int i = 0; i < this.HEIGHT; i++) {
            for (int j = 0; j < this.WIDTH; j++) {
                mDest[i][j] = mSource[i][j];
            }
        }
    }

	/* Fonction : Algorithme Alpha Beta */
    public Coup alpha_beta(Noeud n, int alpha, int beta, int profondeur) {

        int bestj = 0;

        if ((profondeur == 1) || (estFinJeu(n.isMax(), n.getMatrice()))){
            n.evaluer();
            return new Coup(n.getH(), -1);
        }

        if (n.isMax() == true) {
            for (int j = 0; j < this.WIDTH; j++) {
				/* Copie de la matrice */
                int[][] copie = new int[HEIGHT][WIDTH];
                copieMatrice(n.getMatrice(), copie);

                if (jouer(!n.isMax(), j, copie)) {
                    Noeud successeur = new Noeud(!n.isMax(), copie);
                    Coup Coup2 = alpha_beta(successeur, MIN_VALUE, MAX_VALUE, profondeur - 1);
	
                    successeur.setH(Coup2.getEval());

                    if (Coup2.getEval() > alpha) {
                        alpha = Coup2.getEval();
                        bestj = j;
                    }
                    if (alpha >= beta) {
                        Coup Coup3 = new Coup(alpha, bestj);
                        return Coup3;
                    }
                }
            }
            return new Coup(alpha,bestj);
        } else {
            for (int j2 = 0; j2 < this.WIDTH; j2++) {
				/* Copie de la matrice */
                int[][] copie = new int[HEIGHT][WIDTH];
                copieMatrice(n.getMatrice(), copie);

                if (jouer(n.isMax(), j2, copie)){
                    Noeud successeur2 = new Noeud(!n.isMax(), copie);
                    Coup Coup4 = alpha_beta(successeur2, MIN_VALUE, MAX_VALUE, profondeur - 1);

                    successeur2.setH(Coup4.getEval());

                    if (Coup4.getEval() < beta){
                        beta = Coup4.getEval();
                        bestj = j2;
                    }
                    if (beta <= alpha) {
                        Coup Coup5 = new Coup(beta, bestj);
                        return Coup5;
                    }
                }
            }
            return new Coup(beta, bestj);
        }

    }


}



