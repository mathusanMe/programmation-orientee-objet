public class Controlleur {
    private Vue view;
    private Modele modele;

    public Controlleur(Vue view, Modele modele) {
        this.view = view;
        this.modele = modele;
    }

    public void sliderMoved() {
        modele.setRouge(view.getCurseurRouge());
        modele.setVert(view.getCurseurVert());
        modele.setBleu(view.getCurseurBleu());

        view.miseAJour();
    }

    public Vue getView() {
        return view;
    }

    public void setView(Vue view) {
        this.view = view;
    }

    public void memoriser() {
        modele.memoriser();
    }

    public void senrappeler() {
        modele.setRouge(modele.getRougeMemorise());
        modele.setVert(modele.getVertMemorise());
        modele.setBleu(modele.getBleuMemorise());

        view.updateCurseurs();
    }

    public void complementaire() {
        modele.setRouge(100 - modele.getRouge());
        modele.setVert(100 - modele.getVert());
        modele.setBleu(100 - modele.getBleu());

        view.updateCurseurs();
    }
}
