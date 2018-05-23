
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class ScoreBoard extends JLabel {


    public ScoreBoard() {
        super();
        ConfigSingleton.getInstance().setScore(0);;
        setText("Score: " + 0);
    }

    public void increment(int points) {
        int score = ConfigSingleton.getInstance().getScore();
        score += points;
        ConfigSingleton.getInstance().setScore(score);
        setText("Score: " + score);
    }

    public void reset() {
        ConfigSingleton.getInstance().setScore(0);
        setText("Score: " + 0);
    }
    


    public void paused() {
        setText("PAUSED");
    }

    public void resume() {
        setText("Score: " + ConfigSingleton.getInstance().getScore());
    }

    public void gameOver() {
        setText("GAME OVER  || Score: " + ConfigSingleton.getInstance().getScore());
    }
}
