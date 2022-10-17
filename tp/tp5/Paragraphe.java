public class Paragraphe extends ChaineCar {
    private Ligne paragraphe;

    public Paragraphe(Ligne paragraphe) {
        this.paragraphe = paragraphe;
    }

    public int len() {
        return paragraphe.len();
    }

    public String toString() {
        return paragraphe.toString();
    }

    public boolean isEmpty() {
        return paragraphe.isEmpty();
    }

    public void addChaine(ChaineCar cc) {
        paragraphe.addChaine(cc);
    }

    public void removeDernierEspace() {
        paragraphe.removeDernierEspace();
    }
}
