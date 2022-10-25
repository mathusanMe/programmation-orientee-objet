public class Dictionnaire implements Triable {
    private String[] tabMots;

    public Dictionnaire(String[] tabMots) {
        this.tabMots = tabMots;
    }

    public void echange(int i, int j) {
        String tmp = tabMots[i];
        tabMots[i] = tabMots[j];
        tabMots[j] = tmp;
    }

    public boolean plusGrand(int i, int j) {
        return tabMots[i].compareTo(tabMots[j]) > 0;
    }

    public int taille() {
        return tabMots.length;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < tabMots.length; i++) {
            s += tabMots[i];
            if (i < tabMots.length - 1) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        String[] tabMots = {"chat", "singe", "panda", "bleu", "sentiment"};
        Dictionnaire d = new Dictionnaire(tabMots);
        Triable.triBulles(d);
        System.out.println(d);
    }
}
