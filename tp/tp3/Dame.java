public class Dame extends Piece {

    public Dame(boolean estBlanche) {
        super(estBlanche, "dame");
    }
    
    public boolean estValide(Deplacement d, Plateau p) {
        return super.estValide(d, p) && p.intermVides(d);
    }

    public boolean peutVertical() {
        return true;
    }
}