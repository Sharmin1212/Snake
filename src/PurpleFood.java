
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class PurpleFood extends Food {

    private int visibleTime = 5;

    public PurpleFood(Snake snake) {
        super(snake);
        
    }

    public int getVisibleTime() {
        return visibleTime;
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, row, col, Color.decode("#7F00FF"), squareWidth, squareHeight);

    }
}
