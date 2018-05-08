
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alu20919409n
 */
public class SecondMap {

    public ArrayList<Node> listNodes;
    public Color color;

    public SecondMap() {
        listNodes = new ArrayList<Node>();
        color = Color.WHITE;
        for (int hor = 0; hor < 7; hor++) {
            listNodes.add(new Node(Board.NUM_ROWS - 6, Board.NUM_COLS - 9 - hor, color)); //abajo derecha
            listNodes.add(new Node(8, 8 + hor, color)); //arriba izquierda
            listNodes.add(new Node(8, Board.NUM_COLS - 9 - hor, color)); //arriba derecha
            listNodes.add(new Node(Board.NUM_ROWS - 6, 8 + hor, color)); //abajo izquierda
            
            //
        }

        for (int ver = 0; ver < 7; ver++) {
            listNodes.add(new Node(Board.NUM_ROWS - 6 - ver, 8, color));//abajo izquierda
            listNodes.add(new Node(8 + ver, Board.NUM_COLS - 9, color)); // arriba derecha
            listNodes.add(new Node(8 + ver , 8, color)); //arriba izquierda
            listNodes.add(new Node(Board.NUM_ROWS - 6 - ver , Board.NUM_COLS - 9, color)); //abajo derecha

        }
    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.row, node.col, color, squareWidth, squareHeight
            );
        }
    }
}
