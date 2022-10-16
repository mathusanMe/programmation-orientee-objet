public class BandeDessinee extends Media {                              // ne peut pas heritee de Livre car auteur different de dessinateur
    private String dessinateur;

    public BandeDessinee(String titre, String dessinateur) {
        super(titre);
        this.dessinateur = dessinateur;
    }

    public String toString() {
        return super.toString() + "\nDessinateur : " + dessinateur;
    }

    public int ordreMedia() {
        return 0;
    }
}