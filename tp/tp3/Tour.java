public class Tour extends Piece {

    public Tour(boolean estBlanche) {
        super(estBlanche, "tour");
    }

    public boolean estValide(Deplacement d, Plateau p) {
        return super.estValide(d, p) && (d.typeDeplacement() == 'v' || d.typeDeplacement() == 'h') && p.intermVides(d); 
    }

    public boolean peutVertical() {
        return true;
    }
}