import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame {

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
    

    public Vue() {
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

        panneauChoixCurseurs.add(curseurVert);
        curseurVert.setMajorTickSpacing(25);
        curseurVert.setMinorTickSpacing(5);
        curseurVert.setPaintTicks(true);
        curseurVert.setPaintLabels(true);

        panneauChoixCurseurs.add(curseurBleu);
        curseurBleu.setMajorTickSpacing(25);
        curseurBleu.setMinorTickSpacing(5);
        curseurBleu.setPaintTicks(true);
        curseurBleu.setPaintLabels(true);

        panneauChoix.add(panneauChoixBoutons);
        panneauChoixBoutons.setLayout(new GridLayout(1, 3));

        panneauChoixBoutons.add(memoriser);
        memoriser.setText("Mémoriser");

        panneauChoixBoutons.add(senrappeler);
        memoriser.setText("S'en Rappeler");

        panneauChoixBoutons.add(complementaire);
        complementaire.setText("Complémentaire");

        panneauColore.setBackground(Color.GREEN);
        this.getContentPane().add(panneauColore);

        etiqCouleur.setText("Vert");
        etiqCouleur.setHorizontalAlignment(JLabel.CENTER);

        panneauColore.setLayout(new BorderLayout());
        panneauColore.add(etiqCouleur, BorderLayout.CENTER);
    }
}
