public interface Triable {
    // échange les éléments en positions i et j
    /**
     * Échange les éléments en positions i et j.
     * @param i la position du premier élément à échanger
     * @param j la position du second élément à échanger
     */
    void echange(int i, int j);

    /**
     * retourne vrai si l'élément de position i est plus grand que l'élément de position j
     * @param i position du premier élément à comparer
     * @param j position du second élément à comparer
     * @return boolean
     */
    boolean plusGrand(int i, int j);

    /**
     * Retourne le nombre d'éléments.
     * @return int
     */
    int taille();

    /**
     * Tri à bulles
     * @param t un Triable
     */
    public static void triBulles(Triable t) {
        boolean change = false;
        do {
            change = false;
            for (int i = 0; i < t.taille() - 1; i++) {
                if (t.plusGrand(i, i + 1)) {
                    t.echange(i, i + 1);
                    change = true;
                }
            }
        } while (change);
    }
}

