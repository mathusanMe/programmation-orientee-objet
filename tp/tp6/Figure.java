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
     * @return l'abscisse du centre approximatif de la figure
     */
    public final int getPosX() {
        return posX;
    }

    /**
     * Retourne la position verticale du centre de la figure
     * @return l'ordonnée du centre approximatif de la figure
     */
    public final int getPosY() {
        return posY;
    }

    /**
     * Affiche un résumé des propriétés de la figure
     */
    public abstract void affiche();

    /**
     * Calcule la distance entre le centre de la figure sur laquelle est appelée la méthode et le centre de fig
     * @param fig la figure de laquelle il faut calculer la distance
     * @return distance entre deux centres
     */
    public final double estDistantDe(Figure fig) {
        return Math.sqrt((posX - fig.posX) * (posX - fig.posX) + (posY - fig.posY) * (posY - fig.posY));
    }

    /**
     * Calcule la surface de la figure
     * @return la surface de la figure
     */
    public abstract double surface();

    /**
     * Déplace une figure (et la modifie donc) dans la direction indiquée par x et y
     * @param x abscisse du vecteur déplacement
     * @param y ordonnée du vecteur déplacement
     */
    public final void deplacement(int x, int y) {
        posX += x;
        posY += y;
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(0, 0, 5, 10);
        r.affiche();
        r.deformation(1, 0.5).affiche();;

        Ellipse e = new Ellipse(0, 0, 5, 10);
        e.deplacement(2, 3);
        e.affiche();
        e.deformation(1, 0.5).affiche();;
        e.affiche();

        Triangle t = new Triangle(0, 0, 5, 10);
        t.affiche();
    }
}