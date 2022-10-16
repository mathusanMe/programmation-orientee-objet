public class Image extends Media {
    private int largeur, hauteur;

    public Image(String titre, int largeur, int hauteur) {
        super(titre);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public String toString() {
        return super.toString() + "\nLargeur (en cm) : " + largeur + "\nHauteur (en cm) : " + hauteur;
    }

    public int ordreMedia() {
        return 1;
    }
    
}
