public class Palette {
    private Vue view;
    private Modele modele;
    private Controlleur controlleur;

    public Palette() {
        modele = new Modele(50, 50, 50);
        controlleur = new Controlleur(null, modele);
        view = new Vue(modele, controlleur);
        controlleur.setView(view);
        view.setVisible(true);
    }

    public static void main(String[] args) {
        new Palette();
    }
}