import java.awt.Color;

public class Couleurs {
    private Color[] couleurs;

    public Couleurs() {
        couleurs = new Color[Math.min((int) (Math.random() + 1 * 10), 10)];
        for (int i = 0; i < couleurs.length; i++) {
            couleurs[i] = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        }
    }

    public Color[] getCouleurs() {
        return couleurs;
    }

    public int getWidth() {
        return couleurs.length;
    }
}
