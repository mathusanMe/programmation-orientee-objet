public class Rectangle extends Figure implement Deformable {
    // largeur et longueur du rectangle
    private final double largeur, longueur;

    public Rectangle(int posX, int posY) {
        super(posX, posY);
        this.largeur = largeur;
        this.longueur = longueur;
    }

    /**
     * la déformation du rectangle 
     * @param coeffH le coefficient de déformation verticale
     * @param coeffV le coefficient de déformation horizontale
     * @return le rectangle déformé
     */
    public Rectangle deformation(double coeffH, double coeffV) {
        return new Rectangle(getPosX(), getPosY(), largeur * coeffH, longueur * coeffV);
    }
}
