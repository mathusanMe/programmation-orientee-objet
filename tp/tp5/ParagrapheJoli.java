public class ParagrapheJoli extends Paragraphe {

    private int largeurPage;

    public ParagrapheJoli(Ligne ligne, int largeurPage) {
        super(ligne);
        this.largeurPage = largeurPage;
    }

    public void addChaine(ChaineCar cc) {                                           // addString dans l'énoncé
        if (!isEmpty() && 
            lignes.getLast().dernierEstEspace() && 
            lignes.getLast().len() + cc.len() >= largeurPage) {                         // si la longueur de la ligne avec le mot à ajouter est
            super.addLigne();                                                           // superieur à la largeur de la page, alors saut de ligne
        }
        super.addChaine(cc);
    }
}
