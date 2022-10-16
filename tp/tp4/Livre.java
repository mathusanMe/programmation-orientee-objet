public class Livre extends Media {
    private String auteur;
    private int nombrePages;

    public Livre(String titre, String auteur, int nombrePages) {
        super(titre);
        this.auteur = auteur;
        this.nombrePages = nombrePages;
    }

    public String toString() {
        return super.toString() + "\nAuteur : " + auteur + "\nNombre de Pages : " + nombrePages;
    }

    // public boolean plusPetit(Media doc) {
    //     if (!(doc instanceof Livre)) {
    //         return false;
    //     }
    //     return super.plusPetit(doc);
    // }

    // public boolean plusPetit(Livre doc) {
    //     if (!(doc instanceof Livre)) {
    //         return false;
    //     }
    //     return super.plusPetit(doc);
    // }
}
