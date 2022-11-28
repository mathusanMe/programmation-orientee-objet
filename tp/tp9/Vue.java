import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame {

    // Le modèle associé à la vue
    private Modele modele;

    // Le contrôleur associé à la vue
    private Controlleur controlleur;

    // Déclaration des composants graphiques
    private JPanel panneauColore = new JPanel();
    private JPanel panneauChoix = new JPanel();
    private JLabel etiqCouleur = new JLabel();

    private JPanel panneauChoixBoutons = new JPanel();

    private JButton memoriser = new JButton();
    private JButton senrappeler = new JButton();
    private JButton complementaire = new JButton();

    private JPanel panneauChoixCurseurs = new JPanel();

    private JSlider curseurVert = new JSlider();
    private JSlider curseurRouge = new JSlider();
    private JSlider curseurBleu = new JSlider();
    

    public Vue(Modele modele, Controlleur controlleur) {
        this.modele = modele;
        this.controlleur = controlleur;

        this.setTitle("Palette");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new GridLayout(1, 2));
        this.getContentPane().add(panneauChoix);

        panneauChoix.setLayout(new GridLayout(2, 1));
        panneauChoix.add(panneauChoixCurseurs);

        panneauChoixCurseurs.setLayout(new GridLayout(3, 1));

        panneauChoixCurseurs.add(curseurRouge);
        curseurRouge.setMajorTickSpacing(25);
        curseurRouge.setMinorTickSpacing(5);
        curseurRouge.setPaintTicks(true);
        curseurRouge.setPaintLabels(true);
        curseurRouge.setName("Rouge");
        curseurRouge.addChangeListener((event) -> {
            controlleur.sliderMoved();
            miseAJour();
        });

        panneauChoixCurseurs.add(curseurVert);
        curseurVert.setMajorTickSpacing(25);
        curseurVert.setMinorTickSpacing(5);
        curseurVert.setPaintTicks(true);
        curseurVert.setPaintLabels(true);
        curseurVert.setName("Vert");
        curseurVert.addChangeListener((event) -> {
            controlleur.sliderMoved();
            miseAJour();
        });

        panneauChoixCurseurs.add(curseurBleu);
        curseurBleu.setMajorTickSpacing(25);
        curseurBleu.setMinorTickSpacing(5);
        curseurBleu.setPaintTicks(true);
        curseurBleu.setPaintLabels(true);
        curseurBleu.setName("Bleu");
        curseurBleu.addChangeListener((event) -> {
            controlleur.sliderMoved();
        });
        
        panneauChoix.add(panneauChoixBoutons);
        panneauChoixBoutons.setLayout(new GridLayout(1, 3));

        panneauChoixBoutons.add(memoriser);
        memoriser.setText("Mémoriser");
        memoriser.addActionListener((event) -> {
            controlleur.memoriser();
        });

        panneauChoixBoutons.add(senrappeler);
        senrappeler.setText("S'en Rappeler");
        senrappeler.addActionListener((event) -> {
            controlleur.senrappeler();
        });

        panneauChoixBoutons.add(complementaire);
        complementaire.setText("Complémentaire");
        complementaire.addActionListener((event) -> {
            controlleur.complementaire();
        });

        panneauColore.setBackground(Color.GREEN);
        this.getContentPane().add(panneauColore);

        etiqCouleur.setText("Vert");
        etiqCouleur.setHorizontalAlignment(JLabel.CENTER);

        panneauColore.setLayout(new BorderLayout());
        panneauColore.add(etiqCouleur, BorderLayout.CENTER);

        miseAJour();
    }

    public int getCurseurRouge() {
        return curseurRouge.getValue();
    }

    public int getCurseurVert() {
        return curseurVert.getValue();
    }

    public int getCurseurBleu() {
        return curseurBleu.getValue();
    }

    public void updateCurseurs() {
        curseurRouge.setValue(modele.getRouge());
        curseurVert.setValue(modele.getVert());
        curseurBleu.setValue(modele.getBleu());

        miseAJour();
    }

    public void miseAJour() {
        etiqCouleur.setText(String.format("#%02X%02X%02X", modele.getRouge() * 255 / 100, modele.getVert() * 255 / 100, modele.getBleu() * 255 / 100));
        panneauColore.setBackground(new Color(modele.getRouge() * 255 / 100, modele.getVert() * 255 / 100, modele.getBleu() * 255 / 100));
    }
}
