
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
public class SpecialFood extends Food {

    public SpecialFood(Snake snake) {
        super(snake);
    }


    
    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, row, col, Color.YELLOW, squareWidth *2, squareHeight*2);

    }
}
