public class Media {
    private static int compteur = 0;         // Compteur du nombre de medias cree
    private final int numero;                // Le numero d'enregistrement que l'on a attribue au media courant
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
        return String.valueOf(numero) + titre;
    }

    public boolean plusPetit(Media doc) {
        return numero < doc.getNumero();
    }
}
