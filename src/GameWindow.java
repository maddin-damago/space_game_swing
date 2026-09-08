import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private int width = 800;
    private int height = 800;

    private GamePanel gamePanel;

    public GameWindow() {
        super("Game Window");

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(400, 200);
        this.setResizable(false);

        this.setVisible(true);

        int titleHeightPixel = this.getInsets().top;
        int borderRightPixel = this.getInsets().left;
        int borderLeftPixel = this.getInsets().right;
        int borderBottomPixel = this.getInsets().bottom;

//        System.out.println("Title Height: " + titleHeightPixel +
//                "\nBorder Right: " + borderRightPixel +
//                "\nBorder Left: " + borderLeftPixel +
//                "\nborder Bottom: " + borderBottomPixel);

        this.gamePanel = new GamePanel(width - borderRightPixel - borderLeftPixel,
                    height - titleHeightPixel - borderBottomPixel
                );

        this.add(gamePanel);
    }
}
