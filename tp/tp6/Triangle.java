public class Triangle extends Figure implements Deformable {
    // base et hauteur du triangle supposé isocèle et positionné comme sur le dessin
    private final double base, hauteur;

    public Triangle(int posX, int posY, double base, double hauteur) {
        super(posX, posY);
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

    public double surface() {
        return base * hauteur / 2;
    }

    public void affiche() {
        System.out.println("Triangle :");
        System.out.println("Base : " + base + "; Hauteur : " + hauteur);
        System.out.println("Centre du triangle : (" + getPosX() + ", " + getPosY() + ")");
        System.out.println("----------------------------------");
    }
}
