package main.java.com.sam.algorithms;

import main.java.com.sam.data.PathfindingData;
import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.ui.GridPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.Stack;

public class DFS extends PathfindingAlgorithm {

    ArrayList<Node> visitedList = new ArrayList<>();
    ArrayList<Node> visitedListStart = new ArrayList<>();

    ArrayList<Node> visitedListGoal = new ArrayList<>();

    Stack<Node> stack = new Stack<>();
    Stack<Node> stackStart = new Stack<>();

    Stack<Node> stackGoal = new Stack<>();

    Node startNode;
    Node goalNode;
    Node currentNode;
    Node intersectionNode;
    Node[][] nodeArray;
    int maxCol;
    int maxRow;

    double startTime ;
    double endTime ;

    String algorithm = this.getClass().getSimpleName();

    int totalNumOfNodes;
    int numOfNodesVisited;
    int numOfNodesToGoal;

    double elapsedTime;




    public DFS(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }

    public DFS(GridPanel gridPanel) {
        super();
    }


    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;

        stack.add(startNode);
        visitedList.add(startNode);

        while (!stack.isEmpty()){

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            System.out.println(stack);


            Node cur = stack.pop();
            visitedList.add(cur);

            if (cur.isGoal){
                endTime = System.nanoTime();
                elapsedTime = (double) (endTime - startTime) / 1000000000;

                numOfNodesToGoal += 1;
                backTrackPath(isFast);
                break;
            }

            cur.setAsSearched();
            numOfNodesVisited += 1;

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!visitedList.contains(n)){
                    n.parent = cur;
                    stack.push(n);
                    //visitedList.add(cur);
                }
            }

        }

        System.out.println("Stack is empty");

    }

    public ArrayList<Node> getNeighbours(Node n){

        ArrayList<Node> neighbourList = new ArrayList<>();

        if (n.row - 1 >= 0){
            Node uppernode = nodeArray[n.col][n.row-1];
            if (!uppernode.isWall){
                neighbourList.add(uppernode);
            }
        }

        if (n.col + 1 < maxCol){
            Node rightnode = nodeArray[n.col+1][n.row];
            if (!rightnode.isWall){
                neighbourList.add(rightnode);
            }
        }

        if (n.row + 1 < maxRow){
            Node downnode = nodeArray[n.col][n.row+1];
            if (!downnode.isWall){
                neighbourList.add(downnode);
            }
        }

        if (n.col - 1 >= 0){
            Node leftnode = nodeArray[n.col-1][n.row];
            if (!leftnode.isWall){
                neighbourList.add(leftnode);
            }
        }

        return neighbourList;
    }

    public void backTrackPath(boolean isFast) throws InterruptedException {
        Node cur = goalNode;

        while (cur != startNode){
            if(isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(10);
            }

            cur = cur.parent;
            if (cur != startNode){
                cur.setAsPath();
                numOfNodesToGoal += 1;
            }
        }
    }

    public PathfindingData getPathfindingDataObject(){
        return new PathfindingData(this.algorithm, this.totalNumOfNodes, this.numOfNodesVisited, this.numOfNodesToGoal, this.elapsedTime);
    }


    @Override
    public String toString() {
        return "main.java.sam.algorithms.DFS";
    }

}
