public class Video extends Media {
    private double duree;

    public Video(String titre, double duree) {
        super(titre);
        this.duree = duree;
    }

    public String toString() {
        return super.toString() + "\nDuree (en minutes) : " + duree;
    }

    public int ordreMedia() {
        return 5;
    }
}
