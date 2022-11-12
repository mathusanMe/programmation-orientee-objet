public class EntreeSpeciale extends Entree {
    public EntreeSpeciale(Dossier p, String nom, Element e) {
        super(p, nom, e);
    }

    /**
     * Affiche un message d'erreur plutôt que de supprimer l'entrée spéciale.
     */
    public void supprimer() {
        System.out.println("Impossible de supprimer l'entrée spéciale " + getNom());
    }

    /**
     * Affiche un message d'erreur plutôt que de remplacer l'entrée spéciale.
     */
    public void remplacer(Element e) {
        System.out.println("Impossible de remplacer l'entrée spéciale " + getNom());
    }
}
