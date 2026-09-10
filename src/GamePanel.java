import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private final int gamePanelWidth, gamePanelHeight;
    private final int shipWidth, shipHeight;
    private int shipPosX, shipPosY;

    private final Image spaceShip;

    private boolean leftPressed, rightPressed, upPressed, downPressed;

    private final int speed = 10; // step in px
    private final int delay = 40; // ms delay for repaint
    private final Timer timer = new Timer(delay, this);
    private int points = 0;
    private boolean starExplored = false;

    private final Color starColor = Color.YELLOW;
    private final int starWidth = 15, starHeight = 15;
    private int starPosX = 60, starPosY = 60;

    private int starFreqCountdown;
    private final int starFreq = 70;

    public GamePanel(int w, int h) {
        this.gamePanelWidth = w;
        this.gamePanelHeight = h;
        this.shipPosX = 100;
        this.shipPosY = 200;
        this.shipWidth = 60;
        this.shipHeight = 60;


        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocusInWindow();

        try {
            spaceShip = ImageIO.read(new File("assets/buzz.png"));
            this.timer.start();
            fly();
        } catch (IOException e) {
            System.out.println("Image not found");
            throw new RuntimeException(e);
        }

    }

    public Timer getTimer() {
        return this.timer;
    }

    private void fly() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                int action = e.getKeyCode();
                switch (action) {
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = true;
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                int action = e.getKeyCode();
                switch (action) {
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                    case KeyEvent.VK_UP:
                        upPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        downPressed = false;
                        break;
                }
            }
        };
        this.addKeyListener(keyAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.drawString("Explored Stars: " + points, 5, 30);

        g.setColor(starColor);
        g.fillOval(starPosX, starPosY, starWidth, starHeight);

        if (rightPressed) {
            g.drawImage(spaceShip, shipPosX, shipPosY, shipWidth, shipHeight, null);
        } else {
            g.drawImage(spaceShip, shipPosX + shipWidth, shipPosY, -shipWidth, shipHeight, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (collision()) {
            points++;
            starExplored = true;
        }

        starFreqCountdown -= 1;
        if (starFreqCountdown <= 0 || starExplored) {
            starExplored = false;
            Random rand = new Random();
            starPosX = rand.nextInt(gamePanelWidth - starWidth);
            starPosY = rand.nextInt(gamePanelHeight - starHeight);
            starFreqCountdown = starFreq;
        }

        if (leftPressed) {
            if (shipPosX <= -shipWidth) {
                shipPosX = gamePanelWidth + shipWidth;
            }
            shipPosX -= speed;
        }
        if (rightPressed) {
            if (shipPosX >= gamePanelWidth) {
                shipPosX = -shipWidth;
            }
            shipPosX += speed;
        }
        if (upPressed) {
            if (shipPosY <= -shipHeight) {
                shipPosY = gamePanelHeight + shipHeight;
            }
            shipPosY -= speed;
        }
        if (downPressed) {
            if (shipPosY >= gamePanelHeight) {
                shipPosY = -shipHeight;
            }
            shipPosY += speed;
        }
        this.repaint();

    }

    private boolean collision() {
        int shipPosXStart = shipPosX;
        int shipPosXEnd = shipPosX + shipWidth;
        int shipPosYStart = shipPosY;
        int shipPosYEnd = shipPosY + shipHeight;

        return shipPosXStart <= starPosX + starWidth
                && shipPosXEnd >= starPosX
                && shipPosYStart <= starPosY + starHeight
                && shipPosYEnd >= starPosY;
    }
}
