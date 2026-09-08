import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener {
    private int gamePanelWidth, gamePanelHeight;
    private int shipPosX, shipPosY;
    private int shipWidth, shipHeight;

    private Image spaceShip;

    private boolean leftPressed, rightPressed, upPressed, downPressed;

    private int speed = 10; // step in px
    private int delay = 40; // ms delay for repaint
    private Timer timer = new Timer(delay, this);

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
            throw new RuntimeException(e);
        }

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
        g.drawImage(spaceShip, shipPosX, shipPosY, shipWidth, shipHeight, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (leftPressed) {
            if (shipPosX <= 0) {
                shipPosX = gamePanelWidth;
            }
            shipPosX -= speed;
        }
        if (rightPressed) {
            if (shipPosX >= gamePanelWidth) {
                shipPosX = 0;
            }
            shipPosX += speed;
        }
        if (upPressed) {
            if (shipPosY <= 0) {
                shipPosY = gamePanelHeight;
            }
            shipPosY -= speed;
        }
        if (downPressed) {
            if (shipPosY >= gamePanelHeight) {
                shipPosY = 0;
            }
            shipPosY += speed;
        }

        this.repaint();

    }
}
