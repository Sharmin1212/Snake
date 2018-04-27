
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
                    if (direction != DirectionType.RIGHT) {
                        direction = DirectionType.LEFT;
                    }

                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != DirectionType.LEFT) {
                        direction = DirectionType.RIGHT;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != DirectionType.DOWN) {
                        direction = DirectionType.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != DirectionType.UP) {
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

    public static final int NUM_ROWS = 40;
    public static final int NUM_COLS = 40;
    private int deltaTime;
    private Timer timer;
    boolean directionUp;
    boolean directionDown;
    boolean directionLeft;
    boolean directionRight;
    boolean gameOver = false;
    MyKeyAdapter keyAdapter;
    AudioStream audioSong = null;
    AudioStream audioEffect = null;
    private Food food;
    private SpecialFood specialFood;
    private Snake snake;
    private DirectionType direction = DirectionType.RIGHT;

    public Board() {
        super();
        initValues();
        timer = new Timer(deltaTime, this);
        keyAdapter = new MyKeyAdapter();
        setBackground(Color.BLACK);
    }

    public void initValues() {
        setFocusable(true);
        deltaTime = 100;
        snake = new Snake(3);
        food = new Food(snake);

    }

    public void initGame() {
        //AudioPlayer.player.stop(audioSong);
        initValues();

        removeKeyListener(keyAdapter);
        timer.setDelay(deltaTime);
        timer.start();
        addKeyListener(keyAdapter);
        gameOver = false;
        //playSong(".wav");
        direction = DirectionType.RIGHT;

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
        if (collisions() == true) {
            gameOver();
        } else {
            snake.movement(direction);
            repaint();
            Toolkit.getDefaultToolkit().sync();
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g, squareWidth(), squareHeight());
        if (food != null) {
            food.draw(g, squareWidth(), squareHeight());

        }
        // drawBoarder(g);
    }

    /*  public void drawBoarder(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(0, 0, NUM_COLS * squareWidth(), NUM_ROWS * squareHeight());
    }*/
    public void setScoreboard(ScoreBoard scoreboard) {
        this.scoreBoard = scoreboard;
    }

    public void playSong(String song) {
        InputStream music;
        try {
            music = new FileInputStream(new File(song));
            audioSong = new AudioStream(music);
            AudioPlayer.player.start(audioSong);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void playEffect(String effect) {
        InputStream music;
        try {
            music = new FileInputStream(new File(effect));
            audioEffect = new AudioStream(music);
            AudioPlayer.player.start(audioEffect);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void gameOver() {
        timer.stop();
        scoreBoard.gameOver();
    }

    private int squareWidth() {
        return getWidth() / NUM_COLS;
    }

    private int squareHeight() {
        return getHeight() / NUM_ROWS;
    }

}

//deltaTime, food, specialFood, snake/timer
//actionPerformed, gameOver, canMove, checkColision(boolean), checkFood(boolean), eatFood
