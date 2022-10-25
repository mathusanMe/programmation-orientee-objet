public class Rectangle extends Figure implements Deformable {
    // largeur et longueur du rectangle
    private final double largeur, longueur;

    public Rectangle(int posX, int posY, double largeur, double longueur) {
        super(posX, posY);
        this.largeur = largeur;
        this.longueur = longueur;
    }

    /**
     * la déformation du rectangle 
     * @param coeffH le coefficient de déformation verticale
     * @param coeffV le coefficient de déformation horizontale
     * @return le rectangle déformé (peut retourner un carré)
     */
    public Rectangle deformation(double coeffH, double coeffV) {
        double newLargeur = largeur * coeffH, newLongueur = longueur * coeffV;
        return (newLargeur == newLongueur) ? new Carre(getPosX(), getPosY(), newLargeur) : new Rectangle(getPosX(), getPosY(), newLargeur, newLongueur);
    }

    public double surface() {
        return largeur * longueur;
    }

    public void affiche() {
        System.out.println("----------- Rectangle ------------");
        System.out.println("Largeur : " + largeur + "; Longueur : " + longueur);
        System.out.println("Centre du rectangle : (" + getPosX() + ", " + getPosY() + ")");
        System.out.println("----------------------------------");
    }
}
