import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class GridPanel extends JPanel {

    //SCREEN SETTINGS

    public boolean isDisplayText;
    public boolean isCostText;
    public boolean isPositionText;

    public final int maxCol = 25;

    public final int maxRow = 25;

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

    public void resetCosts(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.gCost = 0;
                n.hCost = 0;
                n.fCost = 0;
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

    public void disableText(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.disableText();
            }
        }
    }

    public void enableCostText(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.enableCostText();
            }
        }
    }

    public void enablePositionText(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                n.enablePositionText();
            }
        }
    }

    public void disableButtons(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                //n.setEnabled(false);

                for( ActionListener al : n.getActionListeners() ) {
                    n.removeActionListener( al );
                }
            }
        }
    }

    public void enableButtons(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                //n.setEnabled(true);
                n.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Objects.equals(ControlPanel.currentselection, "start")){
                            n.setAsStart();
                        }

                        if (Objects.equals(ControlPanel.currentselection, "goal")){
                            n.setAsGoal();
                        }

                        if (Objects.equals(ControlPanel.currentselection, "wall")){
                            n.setAsWall();
                        }

                        if (Objects.equals(ControlPanel.currentselection, "default")){
                            n.setAsDefault();
                        }

                        if (Objects.equals(ControlPanel.currentselection, "swamp")){
                            n.setAsSwamp();
                        }
                    }
                });
            }
        }
    }



    public void samSearch(String algoString) throws InterruptedException {

        disableButtons();

        resetSearch();
        resetButtonText();
        resetAllParents();
        resetCosts();



        startNode = null;
        goalNode = null;
        boolean isFast = false;

        samSetStartNode();
        samSetGoalNode();



        //algo.setGridpanel(this);
        PathfindingAlgorithm algo = null;
        if (algoString.equals("A*")){
            algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("Dijkstra")) {
            algo = new Dijkstra(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("BFS")) {
            algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("BFS - Bidirectional")) {
            algo = new BFSBidirectional(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("DFS")) {
            algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        } else if (algoString.equals("DFS - Bidirectional")) {
            algo = new DFSBidirectional(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
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
                    finalAlgo.startSearch(isFast);
                    enableButtons();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }


    public void samSearchComparison(String algoString, String algoString2, int numOfRuns, File file) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < numOfRuns; i++){

                        startNode = null;
                        goalNode = null;
                        boolean isFast = true;

                        disableButtons();
                        resetSearch();
                        resetButtonText();
                        resetAllParents();
                        resetCosts();

                        wipeGrid();
                        RandomizedPrims rp = new RandomizedPrims(nodeArray,maxCol,maxRow);
                        rp.generateMazeQuick();

                        setRandomStartNode();
                        setRandomGoalNode();
                        samSetStartNode();
                        samSetGoalNode();

                        ArrayList<PathfindingData> pathfindingDataArrayList = new ArrayList<>();
                        CSVWriter myCSVWriter = new CSVWriter(file);

                        //algo.setGridpanel(this);
                        PathfindingAlgorithm algo = null;
                        if (algoString.equals("A*")){
                            algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString.equals("Dijkstra")) {
                            algo = new Dijkstra(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString.equals("BFS")) {
                            algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString.equals("BFS - Bidirectional")) {
                            algo = new BFSBidirectional(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString.equals("DFS")) {
                            algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString.equals("DFS - Bidirectional")) {
                            algo = new DFSBidirectional(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString.equals("Random Walk")) {
                            algo = new RandomWalk(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else{
                            algo = null;
                        }


                        PathfindingAlgorithm finalAlgo = algo;
                        finalAlgo.startSearch(isFast);

                        PathfindingData data = finalAlgo.getPathfindingDataObject();
                        data.run = i+1;
                        data.maxCol = maxCol;
                        data.maxRow = maxRow;
                        data.seed = rp.seed;
                        System.out.println(data);

                        pathfindingDataArrayList.add(data);





                        resetSearch();
                        resetButtonText();
                        resetAllParents();
                        resetCosts();

                        if (algoString2.equals("A*")){
                            algo = new AStar(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString2.equals("Dijkstra")) {
                            algo = new Dijkstra(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString2.equals("BFS")) {
                            algo = new BFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString2.equals("BFS - Bidirectional")) {
                            algo = new BFSBidirectional(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString2.equals("DFS")) {
                            algo = new DFS(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString2.equals("DFS - Bidirectional")) {
                            algo = new DFSBidirectional(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else if (algoString2.equals("Random Walk")) {
                            algo = new RandomWalk(startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
                        } else{
                            algo = null;
                        }

                        PathfindingAlgorithm finalAlgo2 = algo;
                        finalAlgo2.startSearch(isFast);

                        data = finalAlgo2.getPathfindingDataObject();
                        data.run = i+1;
                        data.maxCol = maxCol;
                        data.maxRow = maxRow;
                        data.seed = rp.seed;
                        System.out.println(data);

                        pathfindingDataArrayList.add(data);


                        myCSVWriter.writeToCSV(pathfindingDataArrayList);
                        enableButtons();

                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    public void setRandomStartNode(){
        for (Node[] na: nodeArray){
            for (Node n: na){
                if (n.isDefault){
                    n.setAsStart();
                    return;
                }
            }
        }
    }

    public void setRandomGoalNode(){
        for (int i = nodeArray.length-1; i > 0; i--){
            for (int j = nodeArray[i].length-1; j > 0; j--){
                if (nodeArray[i][j].isDefault){
                    nodeArray[i][j].setAsGoal();
                    return;
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

    private void setStartNode(int col, int row){
        nodeArray[col][row].setAsStart();
        startNode = nodeArray[col][row];
        currentNode = startNode;
    }


}
