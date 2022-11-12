public class Jeu {
    private Joueur joueur;
    private Plateau plateau;

    public Jeu(Joueur j, Plateau p) {
        joueur = j;
        plateau = p;
    }

    public void jouer() {
        while (!plateau.jeuGagne() && !plateau.jeuPerdu()) {
            plateau.afficheCourant();
            switch (joueur.demanderAction()) {
                case 'r':
                    int[] coord_r = joueur.demanderCoordonnes();
                    plateau.revelerCase(coord_r[0], coord_r[1]);
                break;
                case 'd':
                    int[] coord_d = joueur.demanderCoordonnes();
                    plateau.drapeauCase(coord_d[0], coord_d[1]);
                break;
            }
        }

        plateau.afficheCourant();
        
        if (plateau.jeuPerdu()) {
            System.out.println("Vous avez perdu!");
        } else {
            System.out.println("Vous avez gagné!");
        }

        if (joueur.veutJouer()) {
            plateau = new Plateau(plateau);
            jouer();
        } else {
            joueur.finir();
        }
    }
}
