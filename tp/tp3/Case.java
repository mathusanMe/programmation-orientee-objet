public class Case {
    private boolean estBlanche;
    private Piece piece;

    public Case(boolean estBlanche) {
        this.estBlanche = estBlanche;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean estVide() {
        return piece == null;
    }

    public void remplirPiece(Piece p) {
        piece = p;
    }

    public void enleverPiece() {
        piece = null;
    }

    public String toString() {
        if (estVide()) {
            if (estBlanche) {
                return ".";
            } else {
                return "#";
            }
        } else {
            return String.valueOf(piece.toString().charAt(0));
        }
    }
}
