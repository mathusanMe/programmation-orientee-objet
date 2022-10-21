public class FormateurJoli extends Formateur {

    private int largeurPage;

    public FormateurJoli(String filename, int largeurPage) {
        super(filename);
        this.largeurPage = largeurPage;
    }
}
