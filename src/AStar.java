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

    double startTime ;
    double endTime ;

    String algorithm = this.getClass().getSimpleName();

    int totalNumOfNodes;
    int numOfNodesVisited;
    int numOfNodesToGoal;

    double elapsedTime;

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
        int maxHCost = 1000;

        for (Node n: openList){
            //System.out.println(n);
            if (n.fCost < maxFCost){
                maxFCost = n.fCost;
                if(n.hCost < maxHCost){

                    maxHCost = n.hCost;
                    indexOfBestNode = openList.indexOf(n);
                }

            }
        }

        System.out.println(openList.get(indexOfBestNode));
        return indexOfBestNode;
    }


    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;



        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        System.out.println("start search");

        // Maybe add array of startnodes so you can search from multiple points
        openList.add(startNode);

        while (!openList.isEmpty()){
            System.out.println("SPEED : "  + ControlPanel.algoSpeed);

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }


            //System.out.println("in while loop");
            System.out.println("open list size: " + openList.size());
            System.out.println(openList);

            //int indexOfBestNode = leastFIndex(openList);
            //System.out.println(indexOfBestNode);

            //Node cur = openList.get(indexOfBestNode);
            Node cur = openList.get(0);

            if (cur.isGoal){
                endTime = System.nanoTime();
                elapsedTime = (double) (endTime - startTime) / 1000000000;

                numOfNodesToGoal += 1;
                backTrackPath(isFast);
                break;
            }

            cur.setAsSearched();
            numOfNodesVisited += 1;

            openList.remove(0);
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

    public PathfindingData getPathfindingDataObject(){
        return new PathfindingData(this.algorithm, this.totalNumOfNodes, this.numOfNodesVisited, this.numOfNodesToGoal, this.elapsedTime);
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
