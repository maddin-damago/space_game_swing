import javax.swing.*;

public class GameWindow extends JFrame {
    private int width = 800;
    private int height = 800;

    public GameWindow() {
        super("Game Window");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(width, height);
        this.setVisible(true);
    }
}
