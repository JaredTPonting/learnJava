import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements ActionListener, KeyListener {
    // Game constatnts
    private final int PANEL_WIDTH = 800;
    private final int PANEL_HEIGHT = 600;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_HEIGHT = 100;
    private final int BALL_SIZE = 20;

    // paddle positions and speed
    private int leftPaddleY = PANEL_HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private int rightPaddleY = PANEL_HEIGHT / 2 - PADDLE_HEIGHT / 2;
    private final int PADDLE_SPEED = 5;

    // Ball  position and speed
    private int ballX = PANEL_WIDTH / 2 - BALL_SIZE / 2;
    private int ballY = PANEL_HEIGHT / 2 - BALL_SIZE / 2;
    private int ballXSpeed = 3;
    private int ballYSpeed = 3;

    // Timer for game loop
    private Timer timer;

    public PongGame() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.BLACK);
        timer = new Timer(10, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.setColor(Color.WHITE);

        g.fillRect(20, leftPaddleY, PADDLE_WIDTH, PADDLE_HEIGHT);

        g.fillRect(PANEL_WIDTH - 20 - PADDLE_WIDTH, rightPaddleY, PADDLE_WIDTH, PADDLE_HEIGHT);

        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // update ball p[osition
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Bounce of top and bottom walls
        if (ballY <= 0 || ballY >= PANEL_HEIGHT - BALL_SIZE) {
            ballYSpeed = -ballYSpeed;
        }

        // Bounce of left paddle
        if (ballX <= 20 + PADDLE_WIDTH &&
            ballY + BALL_SIZE >= leftPaddleY &&
            ballY <= leftPaddleY + PADDLE_HEIGHT) {
            ballXSpeed = -ballXSpeed;
            ballX = 20 + PADDLE_HEIGHT; // reposition ball to avoid repoeated collision
        }

        if (ballX<0 || ballX > PANEL_WIDTH) {
            ballX = PANEL_WIDTH / 2 - BALL_SIZE / 2;
            ballY = PANEL_HEIGHT / 2 - BALL_SIZE / 2;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Left paddle: W (up) and S (down)
        if ( key == KeyEvent.VK_W && leftPaddleY > 0) {
            leftPaddleY -= PADDLE_SPEED;
        }
        if (key == KeyEvent.VK_S && leftPaddleY < PANEL_HEIGHT - PADDLE_HEIGHT) {
            leftPaddleY += PADDLE_SPEED;
        }

        // Right paddle up and down
        if ( key == KeyEvent.VK_UP && rightPaddleY > 0) {
            rightPaddleY -= PADDLE_SPEED;
        }
        if (key == KeyEvent.VK_DOWN && rightPaddleY < PANEL_HEIGHT - PADDLE_HEIGHT) {
            rightPaddleY += PADDLE_SPEED;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not needed for this game
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed for this game
    }

    public static void main(String[] args) {
        // Create game window
        JFrame frame = new JFrame("Simple Pong Game");
        PongGame gamePanel = new PongGame();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}