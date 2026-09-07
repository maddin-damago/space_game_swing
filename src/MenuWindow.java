import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuWindow extends JFrame {

    private int width = 600;
    private int height = 400;

    private JButton toggleGame, toggleMusic;

    public MenuWindow() {
        this.setTitle("MenuWindow");
        this.setResizable(false);
        this.setLocation(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(width, height);

        viewInit();

        this.setVisible(true);
    }
    // test
    private void viewInit() {
        this.setLayout(new BorderLayout());
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);
        panelNorth.setLayout(new FlowLayout());

        toggleGame = new JButton("Toggle Game");
        toggleGame.setText("Toggle Game");
        panelNorth.add(toggleGame);
        toggleMusic = new JButton("Toggle Music");
        toggleMusic.setText("Toggle Music");
        panelNorth.add(toggleMusic);

        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.BLACK);

        JTextArea gameManual = new JTextArea();
        gameManual.setBackground(Color.BLACK);
        gameManual.setForeground(Color.WHITE);
        gameManual.setMargin(new Insets(30, 30, 30, 30));
        gameManual.setFont(new Font("Arial", Font.ITALIC, 20));

        gameManual.setText("Beware, Spock \r\n" +
            "It's Chewbacca \r\n" +
            "Set Phasers to 'StUnNiNg' \n\n" +
                "This is TREK WARS");

        panelCenter.add(gameManual);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);


    }
}
