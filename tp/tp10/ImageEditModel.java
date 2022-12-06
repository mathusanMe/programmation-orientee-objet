import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;

import java.awt.image.BufferedImage;
import java.awt.*;

public class ImageEditModel {
    public class Coupe {
        private Rectangle z;
        int[][] pixels;

        public Coupe(int x, int y, int width, int height, BufferedImage image) {
            z = new Rectangle(x, y, width, height);
            pixels = new int[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    pixels[i][j] = image.getRGB(i, j);
                }
            }
        }

        public void doit() {
            ImageEditModel.this.clearzone(z);
        }

        public void undo() {
            ImageEditModel.this.fillzone(z, pixels);
        }
    }

    public class CutEdit extends AbstractUndoableEdit {
        private Coupe c;

        public CutEdit(Coupe c) {
            this.c = c;
        }

        public void undo() {
            super.undo();
            c.undo();
        }

        public void redo() {
            super.redo();
            c.doit();
        }
    }

    private BufferedImage image;
    public UndoManager undoManager = new UndoManager();

    public ImageEditModel(String chemin) {
        try {
            image = ImageIO.read(new File(chemin));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    /**
     * Modifie la zone de l'image correspondant au rectangle passé en paramètre
     * @param z : zoom factor
     * @param pixels : number of pixels to add
     */
    public void fillzone(Rectangle z, int[][] pixels) {
        if (z.width != pixels.length || z.height != pixels[0].length) {
            throw new IllegalArgumentException("La zone et le tableau de pixels ne sont pas de la même taille");
        }

        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                image.setRGB(z.x + i, z.y + j, pixels[i][j]);
            }
        }
    }

    /**
     * Va mettre des pixels blancs dans la zone correspondant au rectangle passé en paramètre
     * @param z : zoom factor
     */
    public void clearzone(Rectangle z) {
        Color color = Color.white;
        int srgb = color.getRGB();

        int[][] pixels = new int[z.width][z.height];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j] = srgb;
            }
        }
        
        fillzone(z, pixels);
    }

    public void saveCut(Rectangle z) {
        Coupe c = new Coupe(z.x, z.y, z.width, z.height, image.getSubimage(z.x, z.y, z.width, z.height));
        c.doit();
        undoManager.addEdit(new CutEdit(c));
    }
}