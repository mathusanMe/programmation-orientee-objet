import java.util.Scanner;

public class Joueur {
    private String nom;
    private Scanner scanReponse;

    public Joueur() {
        nom = "Anonyme";
        scanReponse = new Scanner(System.in);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void finir() {
        scanReponse.close();
    }

    public boolean veutJouer() {
        System.out.println("Voulez-vous jouer (oui/non) ?");
        return scanReponse.next().equals("oui") ? true : false;
    }

    public String demanderNom() {
        System.out.println("Quel est votre nom ?");
        return scanReponse.next();
    }

    public int[] demanderDimensions() {
        System.out.println("Les dimensions du tableau (d'abord Hauteur puis Largeur) ?");
        int[] res = new int[2];
        res[0] = scanReponse.nextInt();
        res[1] = scanReponse.nextInt();

        return res;
    }

    public int demanderNbMines() {
        System.out.print("Combien de mines ? ");
        return scanReponse.nextInt();
    }

    public char demanderAction() {
        System.out.println("Voulez-vous reveler une case (r) ou placer un drapeau (d) ?");
        return scanReponse.next().charAt(0);
    }

    public int[] demanderCoordonnes() {
        System.out.println("Entrez les coordonées (par exemple : A1)");
        String coord = scanReponse.next();
        if (coord.length() != 2) { return new int[]{-1, -1}; }
        char x = coord.charAt(0);
        char y = coord.charAt(1);
        return new int[]{(int) x - (int) 'A' + 1, Integer.parseInt(String.valueOf(y))};
    }
}
