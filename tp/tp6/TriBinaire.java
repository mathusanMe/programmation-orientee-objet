public class TriBinaire implements Triable {
    private String[] tabBits;
    
    public TriBinaire(String[] tabBits) {
        this.tabBits = tabBits;
    }

    public void echange(int i, int j) {
        String tmp = tabBits[i];
        tabBits[i] = tabBits[j];
        tabBits[j] = tmp;
    }

    public boolean plusGrand(int i, int j) {
        return Integer.parseInt(tabBits[i], 2) > Integer.parseInt(tabBits[j], 2);
    }

    public int taille() {
        return tabBits.length;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < tabBits.length; i++) {
            s += tabBits[i];
            if (i < tabBits.length - 1) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        String[] tabBits = {"101", "100", "111", "110"};
        TriBinaire t = new TriBinaire(tabBits);
        Triable.triBulles(t);
        System.out.println(t);
    }
}
