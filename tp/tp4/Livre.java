public class Livre extends Media {
    private String auteur;
    private int nombrePages;

    public Livre(String titre, String auteur, int nombrePages) {
        super(titre);
        this.auteur = auteur;
        this.nombrePages = nombrePages;
    }

    public String toString() {
        return super.toString() + "-Livre";
    }
}
