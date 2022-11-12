public class Fou extends Piece {

    public Fou(boolean estBlanche) {
        super(estBlanche, "fou");
    }
    
    public boolean estValide(Deplacement d, Plateau p) {
        return (super.estValide(d, p)) && (d.typeDeplacement() == 'd') && (p.intermVides(d));
    }
}
