public class Plateau {
    private int longueur, largeur;
    private Case[][] plateau;

    public Plateau(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;

        plateau = new Case[longueur][largeur];

        boolean initClrBlanche = false;
        for (int i = 0; i < longueur; i++) {
            boolean currClrBlanche = initClrBlanche;
            for (int j = 0; j < largeur; j++) {
                plateau[i][j] = new Case(currClrBlanche);
                currClrBlanche = !currClrBlanche;
            }
            initClrBlanche = !initClrBlanche;
        }
    }

    public boolean horsLimite(int x, int y) {
        return (x < 0 && x > longueur - 1) || (y < 0 && y > largeur - 1);
    }

    public boolean horsLimite(Deplacement d) {
        int[] pointDepart = d.getPointDepart(), pointArrivee = d.getPointArrivee();
        return horsLimite(pointDepart[0], pointDepart[1]) || horsLimite(pointArrivee[0], pointArrivee[1]);          // Si point de départ ou point d'arrivée est hors limite
    }                                                                                                               // alors, c'est hors limite

    public Case getCase(int x, int y) {
        if (horsLimite(x, y)) {
            return null;
        } else {
            return plateau[x][y];
        }
    }

    public void videCase(int x, int y) {
        if (!horsLimite(x, y)) {
            plateau[x][y].enleverPiece();
        }
    }

    public void remplirCase(int x, int y, Piece p) {
        if (!horsLimite(x, y)) {
            plateau[x][y].remplirPiece(p);;
        }
    }

    public void afficher() {
        for (int i = 0; i < longueur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(plateau[i][j]);
            }
            System.out.println();
        }
    }

    public boolean intermVides(Deplacement d) {
        int[] pointDepart = d.getPointDepart(), pointArrivee = d.getPointArrivee();
        boolean estPointDepartEnHaut = pointDepart[0] <= pointArrivee[0];
        boolean estPointDepartAGauche = pointDepart[1] <= pointArrivee[1];
        switch (d.typeDeplacement()) {
            case 'd':
                return intermVidesDiagonale(d, pointDepart, pointArrivee, estPointDepartEnHaut);
            case 'h':
                if (estPointDepartAGauche) {
                    return intermVides(pointDepart, pointArrivee, 0, 1);
                } else {
                    return intermVides(pointArrivee, pointDepart, 0, 1);
                }
            case 'v':
                if (estPointDepartEnHaut) {
                    return intermVides(pointDepart, pointArrivee, 1, 0);
                } else {
                    return intermVides(pointArrivee, pointDepart, 1, 0);
                }
        }
        return false;
    }

    private boolean intermVidesDiagonale(Deplacement d, int[] pointDepart, int[] pointArrivee, boolean estPointDepartEnHaut) {
        switch (d.getDiagonaleType()) {
            case POSITIVE:
                if (estPointDepartEnHaut) {
                    return intermVides(pointDepart, pointArrivee, 1, -1);
                } else {
                    return intermVides(pointArrivee, pointDepart, 1, -1);
                }
            case NEGATIVE:
                if (estPointDepartEnHaut) {
                    return intermVides(pointDepart, pointArrivee, 1, 1);
                } else {
                    return intermVides(pointArrivee, pointDepart, 1, 1);
                }
            default:
                return false;
        }
    }

    private boolean intermVides(int[] pointEnAmont, int[] pointEnAval, int xOffset, int yOffset) {
        boolean estVide = true;
        while (pointEnAmont[0] != pointEnAval[0]) {
            estVide = estVide && plateau[pointEnAmont[0]][pointEnAmont[1]].estVide();
            pointEnAmont[0] += xOffset;
            pointEnAmont[1] += yOffset;
        }
        return estVide;
    }

    public static void main(String[] args) {
        Plateau p = new Plateau(8, 8);
        p.afficher();
    }
}
