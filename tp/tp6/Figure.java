public abstract class Figure {
    // coordonnées du centre approximatif de la figure
    private int posX;
    private int posY;

    public Figure(int x, int y) {
        posX = x;
        posY = y;
    }

    /**
     * Retourne la position horizontale du centre de la figure
     * @return posX : l'abscisse du centre approximatif de la figure
     */
    public final int getPosX() {
        return posX;
    }

    /**
     * Retourne la position verticale du centre de la figure
     * @return posY : l'ordonnée du centre approximatif de la figure
     */
    public final int getPosY() {
        return posY;
    }

    /**
     * Affiche un résumé des propriétés de la figure
     */
    public abstract void affiche();
}