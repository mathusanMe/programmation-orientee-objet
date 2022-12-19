import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class Cadre extends JFrame {

    class Carre extends JPanel implements MouseInputListener {
        private boolean mouvement = false;
        private int xClick, yClick;
        private Color color;

        public Carre(int x, int y, Color color) {
            super();
            this.color = color;
            setBounds(x, y, 50, 50);
            setBackground(color);
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            mouvement = !mouvement;
            if (mouvement) {
                setBackground(Color.GREEN);
            } else {
                setBackground(color);
                if (gagne()) {
                    finJeu();
                }
            }
            xClick = e.getX();
            yClick = e.getY();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!mouvement) {
                setBackground(Color.BLUE);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!mouvement) {
                setBackground(color);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (mouvement) {
                int x = getX() + e.getX() - xClick;
                int y = getY() + e.getY() - yClick;
                setLocation(x, y);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
    
        }

        public Color getColor() {
            return color;
        }
    }

    private JPanel panneau;
    private JPanel etiquette;
    private Couleurs couleurs;
    private Carre[] carres;

    public Cadre() {
        super("Cadre");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        panneau = new JPanel(null);
        couleurs = new Couleurs();
        carres = new Carre[couleurs.getWidth()];
        for (int i = 0; i < carres.length; i++) {
            carres[i] = new Carre((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight()), couleurs.getCouleurs()[i]);
            panneau.add(carres[i]);
        }

        this.add(panneau);
    }

    private boolean gagne() {
        Color cmpColor = carres[0].getColor();
        for (int i = 1; i < carres.length; i++) {
            if (carres[i].getColor() != cmpColor) {
                return false;
            }
        }
        return true;
    }

    private void finJeu() {
        etiquette = new JPanel();
        etiquette.add(new JLabel("Bravo!"));
        this.add(etiquette);
        this.remove(panneau);
        this.addMouseListener(
            new MouseInputListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    
                    
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    
                    
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    
                    
                }
            }
        );
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cadre();
            }
        });
        
    }
}