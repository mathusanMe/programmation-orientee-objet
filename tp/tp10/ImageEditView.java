import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;


public class ImageEditView extends JFrame {
    public class ImagePane extends JPanel {

        public class Selection extends MouseAdapter implements MouseMotionListener {
            private int startX, startY, endX, endY;

            public Rectangle getRectangle() {
                Rectangle r;
                if (startX > endX && startY > endY) {
                    r = new Rectangle(endX, endY, startX - endX, startY - endY);
                } else if (startX > endX) {
                    r = new Rectangle(endX, startY, startX - endX, endY - startY);
                } else if (startY > endY) {
                    r = new Rectangle(startX, endY, endX - startX, startY - endY);
                } else {
                    r = new Rectangle(startX, startY, endX - startX, endY - startY);
                }
                return r;
            }

            public void mousePressed(MouseEvent e) {
                cutButton.setEnabled(false);

                startX = endX = e.getX();
                startY = endY = e.getY();
                repaint();
            }

            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                
                if (Math.abs(endX - startX) > 0 && Math.abs(endY - startY) > 0) {
                    cutButton.setEnabled(true);
                }
                repaint();
            }

            public void mouseMoved(MouseEvent e) {
                
            }
        }

        Selection selection = new Selection();

        public ImagePane() {
            super();
            this.setPreferredSize(new Dimension(model.getImage().getWidth(), model.getImage().getHeight()));
            addMouseListener(selection);
            addMouseMotionListener(selection);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(model.getImage(), 0, 0, this);
            ((Graphics2D) g).draw(selection.getRectangle());
        }
    }

    private final JButton cutButton, undoButton, redoButton;
    private ImagePane imagePane;
    private final ImageEditModel model;

    public ImageEditView(ImageEditModel model) {
        super();
        this.model = model;
        this.setTitle("ImageEdit");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cutButton = new JButton("Cut");
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");

        imagePane = new ImagePane();

        JMenuBar menuBar = new JMenuBar();
        
        cutButton.setEnabled(false);
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);

        menuBar.add(cutButton);
        menuBar.add(undoButton);
        menuBar.add(redoButton);

        cutButton.addActionListener( (ActionEvent e) -> {
            model.saveCut(imagePane.selection.getRectangle());
            imagePane.repaint();
            cutButton.setEnabled(false);
            undoButton.setEnabled(true);
            redoButton.setEnabled(false);
        });

        undoButton.addActionListener( (ActionEvent e) -> {
            if (model.undoManager.canUndo()) {
                model.undoManager.undo();
                imagePane.repaint();
                redoButton.setEnabled(true);
                if (!model.undoManager.canUndo()) {
                    undoButton.setEnabled(false);
                }
                cutButton.setEnabled(true);
            }
        });

        redoButton.addActionListener( (ActionEvent e) -> {
            if (model.undoManager.canRedo()) {
                model.undoManager.redo();
                imagePane.repaint();
                undoButton.setEnabled(true);
                if (!model.undoManager.canRedo()) {
                    redoButton.setEnabled(false);
                }
                cutButton.setEnabled(false);
            }
        });

        setJMenuBar(menuBar);

        this.setContentPane(imagePane);
    }
}
