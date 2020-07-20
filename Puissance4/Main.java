import java.util.Scanner;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

/* REMARQUE : Lorsque que le joueur commence l'ordinateur est assez rapide pour jouer.
Mais lorsque l'ia commence elle met beaucoup plus de temps à jouer. */


public class Main {
	public static void main(String[] args) {
		
		/* Initialisation variable */
        Puissance3 jeu = new Puissance3();
        Noeud noeud = new Noeud(true, jeu.getMatriceJeu());
        Coup CoupIa;

		/* Choix Mode de Jeu */
		Scanner sc1 = new Scanner(System.in);
		System.out.println("\nPuissance 4 par Laurent SAVIVANH\n\n");
		System.out.println("Mode de jeu : \n\n1 : Joueur commence \n2 : IA commence\n");
		System.out.print("Choix : ");
		int mode = sc1.nextInt();
		while ((mode < 1) || (mode > 4)) {
            System.out.println("Mode de jeu : \n\n1 : Joueur commence \n2 : IA commence\n");
            System.out.println("Erreur choix\n");
            System.out.print("Choix : ");
            mode = sc1.nextInt();
        }
		System.out.println(" ");


        /* ***DEBUT DU JEU*** */

        System.out.println(jeu.toString());
        // IA = true et Joueur = False
		/* Choix du mode de jeu */
        if (mode == 1) noeud.setMax(!noeud.isMax());
			/* Vérification Fin de jeu */
            while(!jeu.estFinJeu(!noeud.isMax(),jeu.getMatriceJeu())) {
                noeud.setMatrice(jeu.getMatriceJeu());
					/* Ia joue */
                    if(noeud.isMax() == true){
						System.out.println("L'IA est entrain de jouer...");
                        CoupIa = jeu.alpha_beta(noeud, MIN_VALUE, MAX_VALUE, jeu.profondeur);
                        jeu.jouer(true, CoupIa.getColonne(), jeu.getMatriceJeu());
                        System.out.println("\nL'IA a joué la colonne "+CoupIa.getColonne());

                    } else {
						/* Choix du coup */
                        Scanner sc = new Scanner(System.in);
                        System.out.print("\nVeuillez choisir la colonne : ");
						System.out.println(" ");
                        int CoupJoueur = sc.nextInt();

						/* Tant que joueur n'a pas jouer */

							while (!jeu.jouer(noeud.isMax(), CoupJoueur, jeu.getMatriceJeu())){
								System.out.println(jeu.toString());
                                System.out.print("\nChoisir une colonne entre 0 et " + (jeu.WIDTH-1) + " : ");
                                CoupJoueur = sc.nextInt();
							}

					}
					System.out.println(jeu.toString());
                    
					/* Changement du joueur */
                    noeud.setMax(!noeud.isMax());
					System.out.println(" ");

                }
				/* Fin du jeu */
                if(noeud.isMax()){
                    System.out.println("Victoire");
                }else{
                    System.out.println("Défaite");
                }
        }
    
}
