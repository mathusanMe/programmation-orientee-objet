public class Ellipse extends Figure implements Deformable {
    // grand rayon et petit rayon de l'ellipse
    private final double grand_rayon, petit_rayon;

    public Ellipse(int posX, int posY) {
        super(posX, posY);
        this.grand_rayon = grand_rayon;
        this.petit_rayon = petit_rayon;
    }

    /**
     * la déformation de l'ellipse 
     * @param coeffH le coefficient de déformation verticale
     * @param coeffV le coefficient de déformation horizontale
     * @return l'ellipse déformée
     */
    public Ellipse deformation(double coeffH, double coeffV) {
        return new Ellipse(getPosX(), getPosY(), grand_rayon * coeffH, petit_rayon * coeffV);
    }
}
