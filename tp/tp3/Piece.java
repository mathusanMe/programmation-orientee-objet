public class Piece {
    private boolean estBlanche;
    private String nom;

    public Piece(boolean estBlanche, String nom) {
        this.estBlanche = estBlanche;
        this.nom = nom;
    }

    public boolean estBlanche() {
        return estBlanche;
    }

    public boolean estRoi() {
        return nom.equalsIgnoreCase("roi");
    }

    public boolean peutVertical() {
        return false;
    }

    public String toString() {
        if (!estBlanche) {                                          // si la pièce est noire
            char[] charArray = nom.toCharArray();                   // on crée un tableau de caractères du 
            charArray[0] = Character.toUpperCase(charArray[0]);     // chaîne de caractère "nom" puis on change le premier caractère
            StringBuilder sb = new StringBuilder();                 
            sb.append(charArray);                                   // Grâce à StringBuilder, on colle le tableau
            return sb.toString();                                   // pour former la chaîne à retourner
        } else {
            return nom;
        }
    }

    public boolean estValide(Deplacement d, Plateau p) {
        return !p.horsLimite(d);
    }
}