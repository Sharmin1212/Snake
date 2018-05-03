
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class Board extends JPanel implements ActionListener {

    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != DirectionType.RIGHT && timer.isRunning() && !snake.turning) {
                        snake.turning = true;

                        direction = DirectionType.LEFT;
                    }

                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != DirectionType.LEFT && timer.isRunning() && !snake.turning) {
                        snake.turning = true;

                        direction = DirectionType.RIGHT;

                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != DirectionType.DOWN && timer.isRunning() && !snake.turning) {
                        snake.turning = true;

                        direction = DirectionType.UP;

                    }
                    break;
                case KeyEvent.VK_DOWN:

                    if (direction != DirectionType.UP && timer.isRunning() && !snake.turning) {
                        snake.turning = true;
                        direction = DirectionType.DOWN;

                    }
                    break;
                case KeyEvent.VK_P:
                    if (!gameOver) {
                        if (timer.isRunning()) {
                            timer.stop();
                            AudioPlayer.player.stop(audioSong);
                            scoreBoard.paused();
                        } else {
                            timer.start();
                            AudioPlayer.player.start(audioSong);
                            scoreBoard.resume();
                        }
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }
    }

    public ScoreBoard scoreBoard;

    public static final int NUM_ROWS = 30;
    public static final int NUM_COLS = 30;
    private int deltaTime;
    private final Timer timer;
    boolean directionUp;
    boolean directionDown;
    boolean directionLeft;
    boolean directionRight;
    boolean gameOver = false;
    MyKeyAdapter keyAdapter;
    AudioStream audioSong;
    AudioStream audioEffect;
    private Food food;
    private SpecialFood specialFood;
    boolean specialCondition;
    private int foodCounter = 0;
    private Snake snake;
    private DirectionType direction = DirectionType.RIGHT;

    private JFrame parentFrame;

    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public Board() {
        super();
        this.audioSong = null;
        this.audioEffect = null;
        initValues();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
        setBackground(Color.BLACK);
    }

    private void initValues() {
        setFocusable(true);
        deltaTime = 100;
        snake = new Snake(3);
        food = new Food(snake);

        specialFood = new SpecialFood(snake);

    }

    public void initGame() {
        AudioPlayer.player.stop(audioSong);
        initValues();

        removeKeyListener(keyAdapter);
        timer.setDelay(deltaTime);
        timer.start();
        addKeyListener(keyAdapter);
        gameOver = false;
        playSong("Song2.wav");
        direction = DirectionType.RIGHT;
        foodCounter = 0;

    }

    private boolean collisions() {
        Node head = snake.listNodes.get(0);
        Node snakeBody = null;

        for (int i = 1; i < snake.listNodes.size(); i++) {
            snakeBody = snake.listNodes.get(i);
            if (head.col == snakeBody.col && head.row == snakeBody.row) {
                return true;
            }
        }

        if (head.row == food.row && head.col == food.col) {
            snake.eatFood(direction);
            food = new Food(snake);
            scoreBoard.increment(100);
            foodCounter++;
            playEffect("EatFood1.wav");

        }

        if (head.row == specialFood.row && head.col == specialFood.col && specialCondition) {
            snake.eatFood(direction);
            specialFood = new SpecialFood(snake);
            scoreBoard.increment(300);
            foodCounter++;
            playEffect("EatFood2.wav");

        }

        if (head.col < 0) {
            return true;
        }

        if (head.row < 0) {
            return true;
        }

        if (head.row > NUM_ROWS) {
            return true;
        }

        if (head.col > NUM_COLS - 1) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //snake.turning = false;

        if (collisions() == true) {
            try {
                gameOver();
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            snake.movement(direction);
            repaint();
            Toolkit.getDefaultToolkit().sync();

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g, squareWidth(), squareHeight());
        if (food != null) {
            food.draw(g, squareWidth(), squareHeight());
        }
        if (specialFood != null && (foodCounter % 6 == 0)) {
            specialCondition = true;
            specialFood.draw(g, squareWidth(), squareHeight());
        } else {
            specialCondition = false;
        }

    }

    public void setScoreboard(ScoreBoard scoreboard) {
        this.scoreBoard = scoreboard;
    }

    public void playSong(String song) {
        InputStream music;
        try {
            music = new FileInputStream(new File(song));
            audioSong = new AudioStream(music);
            AudioPlayer.player.start(audioSong);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    public void playEffect(String effect) {
        InputStream music;
        try {
            music = new FileInputStream(new File(effect));
            audioEffect = new AudioStream(music);
            AudioPlayer.player.start(audioEffect);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    public void gameOver() throws IOException {
        timer.stop();
        AudioPlayer.player.stop(audioSong);

        playSong("GameOver.wav");
        scoreBoard.gameOver();
        RecordsDialog d = new RecordsDialog(parentFrame, true, scoreBoard.getScore());
        d.setVisible(true);

    }

    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }
}
