
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
public class Food {

    public int row;
    public int col;
    public Color color;

    public Food(Snake snake) {
        boolean hit = true;
        while (hit) {
            hit = false;
            row = (int) (Math.random() * (ConfigSingleton.getInstance().getNumRows()));
            col = (int) (Math.random() * (ConfigSingleton.getInstance().getNumCols()));
            color = Color.RED;

            for (Node n : snake.listNodes) {
                if (n.row == row && n.col == col) {
                    hit = true;
                }
            }

            for (Node n : ConfigSingleton.getInstance().getListNodesObstacles()) {
                if (n.row == row && n.col == col) {
                    hit = true;
                }
            }
        }
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        Util.drawSquare(g, row, col, color, squareWidth, squareHeight);

    }

}
