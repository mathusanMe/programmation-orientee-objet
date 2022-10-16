public class EstUnLivre extends Predicat {
    public boolean estVrai(Media doc) {
        return doc instanceof Livre;
    }
}
