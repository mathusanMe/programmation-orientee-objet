abstract class Element {
    /**
     * Retourne le type de l'élément.
     * @return le type de l'élément
     */
    public abstract String getType();

    /**
     * Affiche l'élément.
     */
    public String toString() {
        return "fichier de type" + getType();
    }
}