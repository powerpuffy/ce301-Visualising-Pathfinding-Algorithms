import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

    //SCREEN SETTINGS

    final int maxCol = 18;

    final int maxRow = 18;
    final int nodeSize = 60;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    //NODE
    Node[][] nodeArray = new Node[maxCol][maxRow];

    public static Node startNode;
    static Node goalNode;
    static Node currentNode;

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();

    boolean goalReached = false;

    public GridPanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow,maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        //PLACE NODES
        int col = 0;
        int row = 0;

        while (col < maxCol && row < maxRow){

            nodeArray[col][row] = new Node(col,row);
            this.add(nodeArray[col][row]);

            col++;
            if(col == maxCol){
                col = 0;
                row++;
            }
        }

        setStartNode(3,3);
        setGoalNode(7,7);
    }


    private void setStartNode(int col, int row){
        nodeArray[col][row].setAsStart();
        startNode = nodeArray[col][row];
        currentNode = startNode;
    }

    private void setGoalNode(int col, int row){
        nodeArray[col][row].setAsGoal();
        goalNode = nodeArray[col][row];
    }

    private void setWallNode(int col, int row){
        nodeArray[col][row].setAsWall();

    }

    public void samSearch(){
        /*
        BFS mybfs = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        mybfs.startSearch();

         */

        DFS mydfs = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        mydfs.startSearch();

    }

}
