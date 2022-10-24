public class Ellipse extends Rectangle {
    // grand rayon et petit rayon de l'ellipse
    private final double grand_rayon, petit_rayon;

    public Ellipse(int posX, int posY, double grand_rayon, double petit_rayon) {
        super(posX, posY);
        this.grand_rayon = grand_rayon;
        this.petit_rayon = petit_rayon;
    }
}
