import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

    //SCREEN SETTINGS

    public final int maxCol = 20;

    public final int maxRow = 20;

    // Change back to size 30. Increasing for debug
    final int nodeSize = 50;
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

    }

    private void samSetStartNode(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                if (n.isStart){
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
                if (n.isGoal){
                    n.setAsGoal();
                    goalNode = n;

                }
            }
        }
    }


    public int[][] getMap(){
        int[][] map = new int[this.maxCol][this.maxRow];
        for (int i = 0; i < this.nodeArray.length; i++) {
            for (int j = 0; j < this.nodeArray[i].length; j++){
                if (nodeArray[i][j].isWall){
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
                n.deleteText();
            }
        }
    }



    public void resetSearch(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                if (!(n.isStart || n.isGoal || n.isWall)){
                    if (n.isSwamp){
                        n.setAsSwamp();
                    } else{
                        n.setAsDefault();
                    }
                }
            }
        }
    }

    public void resetButtonText(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.deleteText();
            }
        }
    }

    public void enableBorders(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.setBorderPainted(true);
            }
        }
    }

    public void disableBorders(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.setBorderPainted(false);
            }
        }
    }

    public void resetAllParents(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.parent = null;
                n.secondParent = null;
            }
        }
    }


    public void samSearch(String algoString) throws InterruptedException {

        resetSearch();
        resetButtonText();
        resetAllParents();


        samSetStartNode();
        samSetGoalNode();

        //algo.setGridpanel(this);
        PathfindingAlgorithm algo = null;
        if (algoString.equals("A*")){
            algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("BFS")) {
            algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("DFS")) {
            algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("Random Walk")) {
            algo = new RandomWalk(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else{
            algo = null;
        }
        //BFS algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //DFS algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //AStar algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        //RandomWalk algo = new RandomWalk(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);

        PathfindingAlgorithm finalAlgo = algo;
        new Thread(new Runnable() {
            public void run() {
                try {
                    finalAlgo.startSearch();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void setGoalNode(int col, int row){
        nodeArray[col][row].setAsGoal();
        goalNode = nodeArray[col][row];
    }

    private void setWallNode(int col, int row){
        nodeArray[col][row].setAsWall();
    }

    private void setStartNode(int col, int row){
        nodeArray[col][row].setAsStart();
        startNode = nodeArray[col][row];
        currentNode = startNode;
    }


}
