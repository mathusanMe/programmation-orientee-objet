public class Manga extends BandeDessinee {
    private boolean sensInverse;

    public Manga(String titre, String dessinateur, boolean sensInverse) {
        super(titre, dessinateur);
        this.sensInverse = sensInverse;
    }

    public String toString() {
        return super.toString() + "\nSens Inversé : " + sensInverse;
    }

    public int ordreMedia() {
        return 4;
    }
    
}
