public class Rectangle extends Rectangle {
    // largeur et longueur du rectangle
    private final double largeur, longueur;

    public Rectangle(int posX, int posY, double largeur, double longueur) {
        super(posX, posY);
        this.largeur = largeur;
        this.longueur = longueur;
    }
}
