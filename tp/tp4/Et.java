public class Et extends Predicat {
    private Predicat p1, p2;

    public Et(Predicat p1, Predicat p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean estVrai(Media doc) {
        return p1.estVrai(doc) && p2.estVrai(doc);
    }
}
