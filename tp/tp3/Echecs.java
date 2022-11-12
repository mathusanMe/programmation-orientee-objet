public class Echecs {
    private boolean joueur;
    private int gagnant = 0;

    public Echecs() {
        joueur = true;
    }

    private void initialisePlateau(Plateau p) {
        Roi nRoi = new Roi(false);
        p.remplirCase(3, 1, nRoi);

        Roi bRoi = new Roi(true);
        p.remplirCase(0, 1, bRoi);

        Dame nDame = new Dame(false);
        p.remplirCase(3, 2, nDame);

        Dame bDame = new Dame(true);
        p.remplirCase(0, 2, bDame);

        Tour nTour1 = new Tour(false);
        Tour nTour2 = new Tour(false);
        p.remplirCase(3, 0, nTour1);
        p.remplirCase(3, 3, nTour2);

        Tour bTour1 = new Tour(true);
        Tour bTour2 = new Tour(true);
        p.remplirCase(0, 0, bTour1);
        p.remplirCase(0, 3, bTour2);

        Pion nPion1 = new Pion(false);
        Pion nPion2 = new Pion(false);
        Pion nPion3 = new Pion(false);
        Pion nPion4 = new Pion(false);
        p.remplirCase(2, 0, nPion1);
        p.remplirCase(2, 1, nPion2);
        p.remplirCase(2, 2, nPion3);
        p.remplirCase(2, 3, nPion4);

        Pion bPion1 = new Pion(true);
        Pion bPion2 = new Pion(true);
        Pion bPion3 = new Pion(true);
        Pion bPion4 = new Pion(true);
        p.remplirCase(1, 0, bPion1);
        p.remplirCase(1, 1, bPion2);
        p.remplirCase(1, 2, bPion3);
        p.remplirCase(1, 3, bPion4);
        
    }

    private boolean jouerTour(Deplacement d, boolean joueur, Plateau p) {
        if (p.horsLimite(d)) {
            return false;
        }

        int[] pointDepart = d.getPointDepart();
        Piece pieceDepart = p.getCase(pointDepart[0], pointDepart[1]).getPiece();

        if (pieceDepart == null) {
            return false;
        }

        if (pieceDepart.estBlanche() != joueur) {
            return false;
        }

        int[] pointArrivee = d.getPointArrivee();
        Piece pieceArrivee = p.getCase(pointArrivee[0], pointArrivee[1]).getPiece();

        if (pieceArrivee != null && pieceArrivee.estBlanche() == joueur) {
            return false;
        }

        if (!pieceDepart.estValide(d, p) && !(d.typeDeplacement() == 'd' && d.distAuCarre() == 2) && !(d.typeDeplacement() == 'v' && pieceDepart.peutVertical())) {
            return false;
        }

        if (pieceArrivee != null && pieceArrivee.estRoi()) {
            if (joueur) {
                gagnant = 1;
            } else {
                gagnant = 2;
            }
        }

        p.videCase(pointDepart[0], pointDepart[1]);
        p.remplirCase(pointArrivee[0], pointArrivee[1], pieceDepart);

        p.afficher();
        return true;
    }

    private void jouerPartie(Plateau p) {
        Communication com = new Communication();
        while (gagnant == 0) {
            if (joueur) {
                System.out.println("Le tour du blanc");
            } else {
                System.out.println("Le tour du noir");
            }
            System.out.println("x0 y0 x1 y1");
            Deplacement d = com.demanderDeplacement(joueur);
            while (p.horsLimite(d)) {
                System.out.println("x0 y0 x1 y1");
                d = com.demanderDeplacement(joueur);
            }
            if (jouerTour(d, joueur, p)) {
                joueur = !joueur;
            } else {
                System.out.println("Error");
            }
        }

        if (gagnant == 1) {
            System.out.println("Blanc a gagné!");
        } else if (gagnant == 2) {
            System.out.println("Noir a gagné!");
        } else {
            System.out.println("Match nul!");
        }
    }

    public static void main(String[] args) {
        Echecs e = new Echecs();
        Plateau p = new Plateau(4, 4);
        e.initialisePlateau(p);
        p.afficher();
        
        e.jouerPartie(p);
        // System.out.println("");
        // Deplacement d = new Deplacement(1, 0, 2, 1);
        // e.jouerTour(d, true, p);
        // p.afficher();

        // System.out.println("");
        // d = new Deplacement(1, 1, 2, 1);
        // e.jouerTour(d, false, p);
        // p.afficher();

        // System.out.println("");
        // d = new Deplacement(0, 1, 1, 0);
        // e.jouerTour(d, true, p);
        // p.afficher();

        // System.out.println("");
        // d = new Deplacement(1, 3, 1, 2);
        // e.jouerTour(d, false, p);
        // p.afficher();  
    }
}
