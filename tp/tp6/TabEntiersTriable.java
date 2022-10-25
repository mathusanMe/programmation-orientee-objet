public class TabEntiersTriable implements Triable {
    private int[] tab;

    public TabEntiersTriable(int[] tab) {
        this.tab = tab;
    }

    public void echange(int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    public boolean plusGrand(int i, int j) {
        return tab[i] > tab[j];
    }

    public int taille() {
        return tab.length;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < tab.length; i++) {
            s += tab[i];
            if (i < tab.length - 1) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        int[] tab = {3, 2, 1, 5, 4};
        TabEntiersTriable t = new TabEntiersTriable(tab);
        Triable.triBulles(t);
        System.out.println(t);
    }
}
