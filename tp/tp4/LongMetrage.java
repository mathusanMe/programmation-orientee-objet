public class LongMetrage extends Video {
    private String realisateur;

    public LongMetrage(String titre, double duree, String realisateur) {
        super(titre, duree);
        this.realisateur = realisateur;
    }

    public String toString() {
        return super.toString() + "\nRealisateur : " + realisateur;
    }

    public int ordreMedia() {
        return 3;
    }
}
