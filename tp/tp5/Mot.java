public class Mot extends ChaineCar {
    private String mot;             // l'attribut mot

    public Mot(String mot) {
        this.mot = mot;
    }

    public int len() {
        return mot.length();
    }

    public String toString() {
        return mot;
    }
}
