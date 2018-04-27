
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
public class Snake {

    public ArrayList<Node> listNodes;
    private DirectionType direction;

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }

    public Snake(int snakeLength) {
        listNodes = new ArrayList<Node>();
        this.direction = DirectionType.RIGHT;
        for (int i = 0; i < snakeLength; i++) {
            listNodes.add(new Node(Board.NUM_ROWS / 2, Board.NUM_COLS / 2 - i, Color.GREEN));
        }

    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : listNodes) {
            Util.drawSquare(g, node.row, node.col, node.color, squareWidth, squareHeight);

        }
    }

    public void movement(DirectionType direction) {
        Node head = listNodes.get(0);
        Node node = new Node(head.row, head.col, Color.GREEN);
        switch (direction) {
            case LEFT:
                node.col--;
                listNodes.add(0, node);
                break;
            case RIGHT:
                node.col++;
                listNodes.add(0, node);
                break;
            case UP:
                node.row--;
                listNodes.add(0, node);
                break;

            case DOWN:
                node.row++;
                listNodes.add(0, node);
                break;

            default:

                break;

        }

        listNodes.remove(listNodes.size() - 1);
    }

    public void eatFood(DirectionType direction) {
        Node head = listNodes.get(0);
        Node node = new Node(head.row, head.col, Color.GREEN);
            
        
        switch (direction) {
            case LEFT:
                node.col--;
                for (int i = 0; i < 3; i++) {
                listNodes.add(0, node);
                }
                break;
            case RIGHT:
                node.col++;
                for (int i = 0; i < 3; i++) {
                listNodes.add(0, node);
                }
                break;
            case UP:
                node.row--;
                for (int i = 0; i < 3; i++) {
                listNodes.add(0, node);
                }
                break;

            case DOWN:
                node.row++;
                for (int i = 0; i < 3; i++) {
                listNodes.add(0, node);
                }
                break;

            default:

                break;

        }

    
    }
}
