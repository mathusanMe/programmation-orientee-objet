public class Carre extends Rectangle {
    // coté du carré
    private final double cote;

    public Carre(int posX, int posY, double cote) {
        super(posX, posY, cote, cote);
        this.cote = cote;
    }

    public void affiche() {
        System.out.println("Carré :");
        System.out.println("Côté : " + cote);
        System.out.println("Centre du carré : (" + getPosX() + ", " + getPosY() + ")");
        System.out.println("----------------------------------");
    }
}
