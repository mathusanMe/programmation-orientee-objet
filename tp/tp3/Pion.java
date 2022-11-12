public class Pion extends Piece {

    public Pion(boolean estBlanche) {
        super(estBlanche, "pion");
    }

    public boolean estValide(Deplacement d, Plateau p) {
        return super.estValide(d, p) 
        && (0 < d.dist()) && (d.dist() <= 2) 
        && (d.typeDeplacement() == 'v') && p.intermVides(d); 
    }

    public boolean peutVertical() {
        return true;
    }
    
}
