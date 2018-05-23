
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
public class ConfigSingleton {

    private int score;
    private int numRows = 38;
    private int numCols = 52;
    private int foodCounter = 0;
    private boolean specialCondition;
    private boolean purpleCondition;
    public ArrayList<Node> listNodesObstacles = new ArrayList<Node>();

    public ArrayList<Node> getListNodesObstacles() {
        return listNodesObstacles;
    }

    public void setListNodesObstacles(ArrayList<Node> listNodesObstacles) {
        this.listNodesObstacles = listNodesObstacles;
    }

    public boolean isPurpleCondition() {
        return purpleCondition;
    }

    public void setPurpleCondition(boolean purpleCondition) {
        this.purpleCondition = purpleCondition;
    }

    public boolean isSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(boolean specialCondition) {
        this.specialCondition = specialCondition;
    }

    public int getFoodCounter() {
        return foodCounter;
    }

    public void setFoodCounter(int foodCounter) {
        this.foodCounter = foodCounter;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    private static ConfigSingleton instance = null;

    private ConfigSingleton() {
    }

    public static ConfigSingleton getInstance() {
        if (instance == null) {
            instance = new ConfigSingleton();
        }

        return instance;
    }

}
