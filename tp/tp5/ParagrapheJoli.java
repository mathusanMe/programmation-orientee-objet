public class ParagrapheJoli extends Paragraphe {

    private int largeurPage;

    public ParagrapheJoli(Ligne paragraphe, int largeurPage) {
        super(paragraphe);
        this.largeurPage = largeurPage;
    }
}
