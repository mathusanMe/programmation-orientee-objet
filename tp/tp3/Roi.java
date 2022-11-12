public class Roi extends Piece {

    public Roi(boolean estBlanche) {
        super(estBlanche, "roi");
    }

    public boolean estValide(Deplacement d, Plateau p) {
        return super.estValide(d, p) && (0 < d.dist()) && (d.dist() <= 1) && (d.typeDeplacement() == 'v' || d.typeDeplacement() == 'h' || d.typeDeplacement() == 'd') && p.intermVides(d); 
    }

    public boolean peutVertical() {
        return true;
    }
}
