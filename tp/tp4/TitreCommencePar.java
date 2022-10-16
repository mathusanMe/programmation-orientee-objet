public class TitreCommencePar extends Predicat {
    private char lettre;

    public TitreCommencePar(char lettre) {
        super();
        this.lettre = lettre;
    }
    
    public boolean estVrai(Media doc) {
        return Character.toLowerCase(doc.getTitre().charAt(0)) == Character.toLowerCase(lettre);
    }
}
