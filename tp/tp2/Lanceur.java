public class Lanceur {
    public static void main(String[] args) {
        System.out.println("############################");
        System.out.println("        Jeu Démineur        ");
        System.out.println("############################");

        Joueur j = new Joueur();
        j.setNom(j.demanderNom());
        int[] dims = j.demanderDimensions();
        int nbMines = j.demanderNbMines();
        Plateau p = new Plateau(dims[0], dims[1], nbMines);
        Jeu jeu = new Jeu(j, p);
        jeu.jouer();
    }
}