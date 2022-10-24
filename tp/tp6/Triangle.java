public class Triangle extends Figure {
    // base et hauteur du triangle supposé isocèle et positionné comme sur le dessin
    private final double base, hauteur;

    public Triangle(int posX, int posY, double base, double hauteur) {
        super(posx, posy);
        this.base = base;
        this.hauteur = hauteur;
    }

    /**
     * la déformation du triangle
     * @param coeffH le coefficient de déformation verticale
     * @param coeffV le coefficient de déformation horizontale
     * @return le triangle déformé
     */
    public Triangle deformation(double coeffH, double coeffV) {
        return new Triangle(getPosX(), getPosY(), base * coeffH, hauteur * coeffV);
    }
}
