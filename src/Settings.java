import main.java.com.sam.util.Node;

public class Settings {

    final int maxCol = 25;

    final int maxRow = 25;
    final int nodeSize = 30;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    Node[][] nodeArray = new Node[maxCol][maxRow];

    public static Node startNode;
    static Node goalNode;
    static Node currentNode;

    public Settings(){

    }

    public int getMaxCol() {
        return maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Node[][] getNodeArray() {
        return nodeArray;
    }

    public static Node getStartNode() {
        return startNode;
    }

    public static Node getGoalNode() {
        return goalNode;
    }

    public static Node getCurrentNode() {
        return currentNode;
    }
}
