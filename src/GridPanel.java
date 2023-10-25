import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

    //SCREEN SETTINGS

    final int maxCol = 25;

    final int maxRow = 25;
    final int nodeSize = 30;
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
        ControlPanel cp = new ControlPanel(this);
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

        //setStartNode(3,4);
        //setGoalNode(21,21);
    }


    private void setStartNode(int col, int row){
        nodeArray[col][row].setAsStart();
        startNode = nodeArray[col][row];
        currentNode = startNode;
    }

    private void samSetStartNode(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                if (n.start){
                    n.setAsStart();
                    startNode = n;
                    currentNode = startNode;
                }
            }
        }
    }

    private void samSetGoalNode(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                if (n.goal){
                    n.setAsGoal();
                    goalNode = n;

                }
            }
        }
    }

    private void setGoalNode(int col, int row){
        nodeArray[col][row].setAsGoal();
        goalNode = nodeArray[col][row];
    }

    private void setWallNode(int col, int row){
        nodeArray[col][row].setAsWall();
    }


    public int[][] getMap(){
        int[][] map = new int[this.maxCol][this.maxRow];
        for (int i = 0; i < this.nodeArray.length; i++) {
            for (int j = 0; j < this.nodeArray[i].length; j++){
                if (nodeArray[i][j].wall){
                    map[i][j] = 1;
                } else{
                    map[i][j] = 0;
                }
            }
        }
        return map;
    }

    public void fillMap(int[][] maparray){
        for (int i = 0; i < maparray.length; i++) {
            for (int j = 0; j < maparray[i].length; j++){
                if (maparray[i][j] == 1){
                    nodeArray[i][j].setAsWall();
                }
            }
        }

    }

    public void wipeGrid(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.setAsDefault();
            }
        }
    }



    public void resetSearch(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                if (!(n.start || n.goal || n.wall)){
                    n.setAsDefault();
                }
            }
        }
    }


    public void samSearch() throws InterruptedException {

        resetSearch();

        samSetStartNode();
        samSetGoalNode();

        //BFS algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //DFS algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);

        AStar algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        new Thread(new Runnable() {
            public void run() {
                try {
                    algo.startSearch();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


}
