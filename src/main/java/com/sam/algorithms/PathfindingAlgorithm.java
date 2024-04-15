package main.java.com.sam.algorithms;

import main.java.com.sam.data.PathfindingData;
import main.java.com.sam.ui.GridPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class PathfindingAlgorithm {



    public GridPanel gridpanel;

    Node startNode;
    Node goalNode;
    Node currentNode;

    public Node[][] nodeArray;

    int maxCol;
    int maxRow;

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> closedList = new ArrayList<>();
    ArrayList<Node> successorList = new ArrayList<>();

    double startTime ;
    double endTime ;

    String algorithm = this.getClass().getSimpleName();

    int totalNumOfNodes;

    int totalNumOfVisitableNodes;
    int numOfNodesVisited;
    int numOfNodesToGoal;

    double elapsedTime;

    long seed;

    ArrayList<Node> visitedList = new ArrayList<>();
    ArrayList<Node> visitedListStart = new ArrayList<>();
    ArrayList<Node> visitedListGoal = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>();
    Queue<Node> queueStart = new LinkedList<>();
    Queue<Node> queueGoal = new LinkedList<>();

    Stack<Node> stack = new Stack<>();
    Stack<Node> stackStart = new Stack<>();

    Stack<Node> stackGoal = new Stack<>();

    Boolean fromStart;

    Node intersectionNode;


    public PathfindingAlgorithm(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }
    public PathfindingAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public PathfindingAlgorithm(String s, Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.algorithm = s;
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }

    public abstract void startSearch(boolean isFast) throws InterruptedException;



    public PathfindingData getPathfindingDataObject(){
        return new PathfindingData(this.algorithm, this.totalNumOfNodes, this.totalNumOfVisitableNodes, this.numOfNodesVisited, this.numOfNodesToGoal, this.elapsedTime);
    }

    public void backTrackPath(boolean isFast) throws InterruptedException {
        Node cur = goalNode;

        while (cur != startNode){
            if (isFast){
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

    public void backTrackPathGoalVersion(boolean isFast) throws InterruptedException {
        Node cur = startNode;

        while (cur != goalNode){
            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(10);
            }

            cur = cur.secondParent;
            if (cur != goalNode){
                cur.setAsPath();
                numOfNodesToGoal += 1;
            }
        }
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

    public boolean intersects(Node n, ArrayList<Node> visitedList1, ArrayList<Node> visitedList2){
        if (visitedList1.contains(n) && visitedList2.contains(n)){
            intersectionNode = n;

            System.out.println("Intersection node: " + intersectionNode);

            System.out.println("Intersection node parent: " + intersectionNode.parent);
            System.out.println("Intersection node secondParent: " + intersectionNode.secondParent);

            if (intersectionNode != startNode && intersectionNode != goalNode){
                intersectionNode.setAsIntersectedPath();
            }
            return true;
        } else{
            return false;
        }
    }

    public void backTrackPathToNode(Node start, Node end, boolean isFast) throws InterruptedException {
        Node cur = start;
        System.out.println(cur);
        if (end.isStart){
            cur = cur.parent;
        } else if (end.isGoal){
            cur = cur.secondParent;
        }

        if (!cur.isGoal && !cur.isStart){
            cur.setAsPath();
            numOfNodesToGoal += 1;
        }


        while (cur != end){
            if(isFast){
                Thread.sleep(2);
            } else{
                Thread.sleep(10);
            }

            cur = cur.parent;

            if (cur != end){
                System.out.println(cur);
                cur.setAsPath();
                numOfNodesToGoal += 1;
            }
        }
    }

}
