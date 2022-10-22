public class ParagrapheJoli extends Paragraphe {

    private int largeurPage;
    private int longueur;

    public ParagrapheJoli(Ligne ligne, int largeurPage, int longueur) {
        super(ligne);
        ligne.addChaine(new Espace(4));
        this.largeurPage = largeurPage;
        this.longueur = longueur;
    }

    public void addChaine(ChaineCar cc) {                                           // addString dans l'énoncé
        if (!isEmpty() && 
            lignes.getLast().dernierEstEspace() && 
            lignes.getLast().len() + cc.len() >= largeurPage) {                         // si la longueur de la ligne avec le mot à ajouter est
            lignes.getLast().justifier(longueur);
            super.addLigne();                                                           // superieur à la largeur de la page, alors saut de ligne
        }
        super.addChaine(cc);
    }
}
