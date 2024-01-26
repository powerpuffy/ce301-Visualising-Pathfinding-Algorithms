import java.util.ArrayList;
import java.util.Collections;

public class AStar extends PathfindingAlgorithm{



    Node startNode;
    Node goalNode;
    Node currentNode;

    Node[][] nodeArray;

    int maxCol;
    int maxRow;

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> closedList = new ArrayList<>();
    ArrayList<Node> successorList = new ArrayList<>();

    public AStar(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }

    public AStar(GridPanel gridPanel) {
        this.startNode = gridPanel.startNode;
        this.goalNode = gridPanel.goalNode;
        this.currentNode = gridPanel.currentNode;
        this.nodeArray = gridPanel.nodeArray;
        this.maxCol = gridPanel.maxCol;
        this.maxRow = gridPanel.maxRow;
    }

    public int leastFIndex(ArrayList<Node> openList){

        int indexOfBestNode = 0;
        int maxFCost = 1000;

        for (Node n: openList){
            //System.out.println(n);
            if (n.fCost < maxFCost){
                maxFCost = n.fCost;
                indexOfBestNode = openList.indexOf(n);
            }
        }

        System.out.println("wow");
        System.out.println(openList.get(indexOfBestNode));
        return indexOfBestNode;
    }


    public void startSearch() throws InterruptedException {
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        System.out.println("start search");

        // Maybe add array of startnodes so you can search from multiple points
        openList.add(startNode);

        while (!openList.isEmpty()){
            System.out.println("SPEED : "  + ControlPanel.algoSpeed);
            Thread.sleep(ControlPanel.algoSpeed);
            //System.out.println("in while loop");
            System.out.println("open list size: " + openList.size());

            int indexOfBestNode = leastFIndex(openList);

            Node cur = openList.get(indexOfBestNode);

            if (cur.isGoal){
                backTrackPath();
                break;
            }

            cur.setAsSearched();

            openList.remove(indexOfBestNode);
            System.out.println("open list size after remove: " + openList.size());
            closedList.add(cur);

            //System.out.println("successors");

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!closedList.contains(n) && !openList.contains(n)){
                    n.parent = cur;

                    n.gCost =  n.weight + cur.gCost;
                    n.hCost = calculateHEuclidean(n,goalNode);

                    n.fCost = n.gCost + n.hCost;
                    //n.fCost = n.gCost;
                    //n.fCost = n.hCost;
                    openList.add(n);
                }
            }
            Collections.sort(openList);

        }


        System.out.println("ended");
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

    public void backTrackPath() throws InterruptedException {
        Node cur = goalNode;

        while (cur != startNode){
            Thread.sleep(10);
            cur = cur.parent;
            if (cur != startNode){
                cur.setAsPath();
            }
        }
    }



    public int calculateHManhattan(Node cur, Node goalNode){
        return Math.abs(cur.col - goalNode.col) + Math.abs(cur.row - goalNode.row);
    }

    public int calculateHEuclidean(Node cur, Node goalNode){
        return (int) Math.sqrt((Math.pow(cur.col - goalNode.col,2) + Math.pow(cur.row - goalNode.row,2)));
    }

    @Override
    public String toString() {
        return "A*";
    }
}
