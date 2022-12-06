public class Launcher {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ImageEditModel model = new ImageEditModel("metaverse.png");
                ImageEditView view = new ImageEditView(model);
                view.pack();
                view.setVisible(true);
            }
        });
    }
}
