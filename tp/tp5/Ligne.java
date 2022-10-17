public class Ligne extends ChaineCar {
    LinkedList<ChaineCar> ligne;

    public Ligne(LinkedList<ChaineCar> ligne) {
        this.ligne = ligne;
    }

    public int len() {
        return 0;
    }

    public String toString() {
        return super.toString();
    }
    
}
