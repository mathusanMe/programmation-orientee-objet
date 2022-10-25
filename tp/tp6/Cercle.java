public class Cercle extends Ellipse {
    // rayon du cercle
    private final double rayon;

    public Cercle(int posX, int posY, double rayon) {
        super(posX, posY, rayon, rayon);
        this.rayon = rayon;
    }

    public void affiche() {
        System.out.println("------------ Cercle -------------");
        System.out.println("Rayon : " + rayon);
        System.out.println("Centre du cercle : (" + getPosX() + ", " + getPosY() + ")");
        System.out.println("----------------------------------");
    }
}
