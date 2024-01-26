import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends PathfindingAlgorithm{

    ArrayList<Node> visitedList = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>();

    Node startNode;
    Node goalNode;
    Node currentNode;
    Node[][] nodeArray;
    int maxCol;
    int maxRow;

    public BFS(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }

    public BFS(GridPanel gridPanel) {
        this.startNode = gridPanel.startNode;
        this.goalNode = gridPanel.goalNode;
        this.currentNode = gridPanel.currentNode;
        this.nodeArray = gridPanel.nodeArray;
        this.maxCol = gridPanel.maxCol;
        this.maxRow = gridPanel.maxRow;
    }


    public void startSearch() throws InterruptedException {

        queue.add(startNode);
        System.out.println(startNode);
        visitedList.add(startNode);

        while (!queue.isEmpty()){

            Thread.sleep(ControlPanel.algoSpeed);

            Node cur = queue.remove();

            if (cur.isGoal){
                backTrackPath();
                break;
            }


            cur.setAsSearched();

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!visitedList.contains(n)){
                    n.parent = cur;
                    queue.add(n);
                    visitedList.add(n);
                }
            }

        }

        System.out.println("Queue is empty");


    }

    public ArrayList<Node> getNeighbours(Node n){

        ArrayList<Node> neighbourList = new ArrayList<>();

        System.out.println(n);



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

    public void backTrackPath() throws InterruptedException {
        Node cur = goalNode;

        while (cur != startNode){
            Thread.sleep(10);
            System.out.println(cur);
            //System.out.println("DASdas");
            cur = cur.parent;

            if (cur != startNode){
                cur.setAsPath();
            }

        }

    }

    @Override
    public String toString() {
        return "BFS";
    }


}
