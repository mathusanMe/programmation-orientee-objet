public class Cercle extends Ellipse {
    // rayon du cercle
    private final double rayon;

    public Cercle(int posX, int posY, double rayon) {
        super(posX, posY, rayon, rayon);
        this.rayon = rayon;
    }
    
}
