public class Modele {
    private int rouge;
    private int vert;
    private int bleu;

    private int rougeMemorise;
    private int vertMemorise;
    private int bleuMemorise;

    public Modele(int rouge, int vert, int bleu) {
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
    }

    public int getRouge() {
        return rouge;
    }

    public int getVert() {
        return vert;
    }

    public int getBleu() {
        return bleu;
    }

    public int getRougeMemorise() {
        return rougeMemorise;
    }

    public int getVertMemorise() {
        return vertMemorise;
    }

    public int getBleuMemorise() {
        return bleuMemorise;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public void setBleu(int bleu) {
        this.bleu = bleu;
    }

    public void memoriser() {
        rougeMemorise = rouge;
        vertMemorise = vert;
        bleuMemorise = bleu;
    }
}
