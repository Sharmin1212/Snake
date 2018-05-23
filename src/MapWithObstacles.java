
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
public class MapWithObstacles {

    public Color color;

    public MapWithObstacles() {
        color = Color.WHITE;
        for (int hor = 0; hor < 7; hor++) {
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(ConfigSingleton.getInstance().getNumRows() - 6, ConfigSingleton.getInstance().getNumCols() - 9 - hor, color)); //abajo derecha
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(8, 8 + hor, color)); //arriba izquierda
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(8, ConfigSingleton.getInstance().getNumCols() - 9 - hor, color)); //arriba derecha
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(ConfigSingleton.getInstance().getNumRows() - 6, 8 + hor, color)); //abajo izquierda
        }

        for (int ver = 0; ver < 7; ver++) {
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(ConfigSingleton.getInstance().getNumRows() - 6 - ver, 8, color));//abajo izquierda
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(8 + ver, ConfigSingleton.getInstance().getNumCols() - 9, color)); // arriba derecha
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(8 + ver, 8, color)); //arriba izquierda
            ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(ConfigSingleton.getInstance().getNumRows() - 6 - ver, ConfigSingleton.getInstance().getNumCols() - 9, color)); //abajo derecha
        }

        for (int hor = 0; hor < 3; hor++) {
            for (int ver = 0; ver < 3; ver++) {
                ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(14 + ver, 16 + hor, color)); //arriba izquierda
                ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(14 + ver, 31 + hor, color)); //arriba derecha
                ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(26 + ver, 16 + hor, color)); //abajo izquierda
                ConfigSingleton.getInstance().getListNodesObstacles().add(new Node(26 + ver, 31 + hor, color)); //abajo derecha
            }
        }

    }

    public void draw(Graphics g, int squareWidth, int squareHeight) {
        for (Node node : ConfigSingleton.getInstance().getListNodesObstacles()) {
            Util.drawSquare(g, node.row, node.col, color, squareWidth, squareHeight
            );
        }
    }
}
