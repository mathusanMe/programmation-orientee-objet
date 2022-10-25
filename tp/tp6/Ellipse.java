public class Ellipse extends Figure implements Deformable {
    // grand rayon et petit rayon de l'ellipse
    private final double grand_rayon, petit_rayon;

    public Ellipse(int posX, int posY, double grand_rayon, double petit_rayon) {
        super(posX, posY);
        this.grand_rayon = grand_rayon;
        this.petit_rayon = petit_rayon;
    }

    /**
     * la déformation de l'ellipse 
     * @param coeffH le coefficient de déformation verticale
     * @param coeffV le coefficient de déformation horizontale
     * @return l'ellipse déformée (peut retourner un cercle)
     */
    public Ellipse deformation(double coeffH, double coeffV) {
        double newGR = grand_rayon * coeffH, newPR = petit_rayon * coeffV;
        return (newGR == newPR) ? new Cercle(getPosX(), getPosY(), newGR) : new Ellipse(getPosX(), getPosY(), newGR, newPR);
    }

    public double surface() {
        return Math.PI * grand_rayon * petit_rayon;
    }

    public void affiche() {
        System.out.println("------------- Ellipse ------------");
        System.out.println("Grand Rayon : " + grand_rayon + "; Petit Rayon : " + petit_rayon);
        System.out.println("Centre de l'ellipse : (" + getPosX() + ", " + getPosY() + ")");
        System.out.println("----------------------------------");
    }
}