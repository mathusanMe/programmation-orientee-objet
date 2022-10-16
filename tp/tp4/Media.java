public class Media {
    private static int compteur = 0;         // Compteur du nombre de medias cree
    private final int numero;                // Le numero d'enregistrement non modifiable que l'on a attribue au media
    private String titre;

    public Media(String titre) {
        numero = compteur;
        compteur++;
        
        this.titre = titre;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitre() {
        return titre;
    }

    public String toString() {
        return String.valueOf(numero) + ". " + titre;  // chaine de caractere constituee du numero d'enregistrement suivi du titre du media
    }

    // public boolean plusPetit(Media doc) {       // verifie que le media `doc` a ete cree strictement apres le media courant
    //     return numero < doc.getNumero();
    // }

    public int ordreMedia() {
        return -1;
    }

    public boolean plusPetit(Media doc) {
        if (this.ordreMedia() != doc.ordreMedia()) {
            return true;
        } else {
            return numero < doc.getNumero();
        }
    }
}
