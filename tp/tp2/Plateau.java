import java.util.Random;

public class Plateau {
    private int hauteur;
    private int largeur;
    private int nbMines;
    private int nbDrapeaux;
    private boolean[][] mines;
    private int[][] etats;
    private int[][] adja;

    public Plateau(int ha, int la, int mi) {
        hauteur = ha;
        largeur = la;
        nbMines = mi;
        nbDrapeaux = mi;
        mines = new boolean[ha + 2][la + 2];
        etats = new int[ha + 2][la + 2];
        adja = new int[ha + 2][la + 2];

        ajouteMinesAlea();
        calculeAdjacence();
    }

    public Plateau(Plateau p) {
        this(p.hauteur, p.largeur, p.nbMines);
    }

    private void ajouteMinesAlea() {
        Random rd = new Random();
        int nbMinesAPlacer = nbMines;
        while (nbMinesAPlacer > 0) {
            int x = rd.nextInt(hauteur) + 1;         
            int y = rd.nextInt(largeur) + 1;
            if (mines[x][y]) { continue; }
            mines[x][y] = true;
            nbMinesAPlacer--;
        }
    }

    private void calculeAdjacence() {
        int[][] offsetArray = new int[][] {
            {1, 0}, {0, 1},
            {1, 1},
            {-1, 0}, {0, -1}, 
            {-1, -1}, 
            {-1, 1}, {1, -1}
        };

        for (int i = 1; i < hauteur + 1; i++) {
            for (int j = 1; j < largeur + 1; j++) {
                for (int[] offset : offsetArray) {
                    if (mines[i + offset[0]][j + offset[1]]) {
                        adja[i][j] += 1;
                    }
                }
            } 
        }
    }

    public void afficheTout() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < largeur + 1; j++) {
                if (i == 0 && j == 0) { 
                    System.out.print("   ");
                }
                else if (i == 0) { 
                    System.out.print(" " + j + " ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.print("\n");
        }
        for (int i = 1; i < hauteur + 1; i++) {
            for (int j = 0; j < largeur + 1; j++) {
                if (j == 0) { 
                    System.out.print((char) (i - 1 + (int) 'A') + " |");
                }
                else if (mines[i][j]) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" " + adja[i][j] + " ");
                }
            } 
            System.out.print("\n");
        }
    }

    public void revelerCase(int l, int c) {
        if (etats[l][c] == 1) {
            System.out.println("Attention au drapeau!");
        } else if (etats[l][c] == 2) {
            System.out.println("Case déja révélée");
        } else {
            if (mines[l][c]) {
                etats[l][c] = 2;
            } else {
                revelerCaseAdj(l, c);
            }
        }
    }

    private void revelerCaseAdj(int l, int c) {
        if (l == 0 || c == 0 || l == hauteur + 1 || c == largeur + 1 || etats[l][c] == 1 || mines[l][c]) {
            return;
        }
        
        int[][] offsetArray = new int[][] {
            {1, 0}, {0, 1},
            {1, 1},
            {-1, 0}, {0, -1}, 
            {-1, -1}, 
            {-1, 1}, {1, -1}
        };
        etats[l][c] = 2;
        for (int[] offset : offsetArray) {
            int x = l + offset[0];
            int y = c + offset[1];
            if (etats[x][y] == 0) {
                revelerCaseAdj(x, y);
            }
        }
    }

    public void drapeauCase(int l, int c) {
        if (etats[l][c] == 2) { System.out.println("Coordonées Impropres!"); }
        else if (etats[l][c] == 1) { etats[l][c] = 0; nbDrapeaux++; }
        else if (etats[l][c] == 0) { etats[l][c] = 1; nbDrapeaux--; }
        else { System.out.println("Attention Etat Improbable!"); }
    }

    public void afficheCourant() {
        System.out.println("Mines : " + nbMines);
        System.out.println("Drapeaux : " + (nbMines - nbDrapeaux));
        System.out.print("   ");
        for (int j = 1; j < largeur + 1; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.print("\n");
        for (int i = 1; i < hauteur + 1; i++) {
            for (int j = 0; j < largeur + 1; j++) {
                if (j == 0) { 
                    System.out.print((char) (i - 1 + (int) 'A') + " |");
                }
                else if (etats[i][j] == 2) {
                    if (mines[i][j]) {
                        System.out.print(" * ");
                    } else {
                        System.out.print(" " + adja[i][j] + " ");
                    }
                } else if (etats[i][j] == 1) {
                    System.out.print(" ! ");
                } else {
                    System.out.print(" . ");
                }
            } 
            System.out.print("\n");
        }
    }

    public boolean jeuPerdu() {
        for (int i = 1; i < hauteur + 1; i++) {
            for (int j = 1; j < largeur + 1; j++) {
                if (mines[i][j] && etats[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean jeuGagne() {
        int tmp = 0;
        for (int i = 1; i < hauteur + 1; i++) {
            for (int j = 1; j < largeur + 1; j++) {
                if (etats[i][j] == 2) {
                    tmp++;
                }
            } 
        }
        return tmp == (hauteur * largeur - nbMines);
    }
}
