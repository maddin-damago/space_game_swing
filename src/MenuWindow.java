import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame implements ActionListener {

    private int width = 600;
    private int height = 400;
    private GameWindow gameWindow;
    private boolean gameRunning, musicRunning;

    private JButton toggleGame, toggleMusic;

    public MenuWindow() {
        this.setTitle("MenuWindow");
        this.setResizable(false);
        this.setLocation(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(width, height);

        viewInit();

        this.setVisible(true);

        toggleGame.addActionListener(this);
        toggleMusic.addActionListener(this);
    }

    private void viewInit() {
        this.setLayout(new BorderLayout());
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.BLACK);
        panelNorth.setLayout(new FlowLayout());

        toggleGame = new JButton("Toggle Game");
        toggleGame.setText("Start Game");
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

        gameManual.setText("""
                Beware, Spock \r
                It's Chewbacca \r
                Set Phasers to 'StUnNiNg'\s
                
                This is TREK WARS""");

        panelCenter.add(gameManual);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleGame) {
            if (!gameRunning) {
                gameWindow = new GameWindow();
                toggleGame.setText("Stop Game");
                gameRunning = true;
            } else {
                gameWindow.dispose();
                toggleGame.setText("Start Game");
                gameRunning = false;
            }
        } else {
            System.out.println("Music");
        }
    }
}
