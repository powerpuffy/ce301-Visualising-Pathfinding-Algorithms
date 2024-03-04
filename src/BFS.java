import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends PathfindingAlgorithm{

    ArrayList<Node> visitedList = new ArrayList<>();
    ArrayList<Node> visitedListStart = new ArrayList<>();
    ArrayList<Node> visitedListGoal = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>();
    Queue<Node> queueStart = new LinkedList<>();
    Queue<Node> queueGoal = new LinkedList<>();

    Boolean fromStart;

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


    /*
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

     */



    // this is bidircetional
    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;


        queueStart.add(startNode);
        System.out.println(startNode);
        visitedListStart.add(startNode);

        queueGoal.add(goalNode);
        System.out.println(goalNode);
        visitedListGoal.add(goalNode);


        while (!queueStart.isEmpty() || !queueGoal.isEmpty()){

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            Node curStart = null;
            Node curGoal = null;


            if (!queueStart.isEmpty()){
                curStart = queueStart.remove();
            }
            
            if (!queueGoal.isEmpty()){
                curGoal = queueGoal.remove();
            }


            if (curStart != null){

                curStart.setAsSearched();
                numOfNodesVisited += 1;
                if (intersects(curStart, visitedListStart, visitedListGoal)){
                    System.out.println("from start");
                    endTime = System.nanoTime();
                    elapsedTime = (double) (endTime - startTime) / 1000000000;

                    numOfNodesToGoal += 2;
                    backTrackPathToNode(intersectionNode,startNode, isFast);
                    backTrackPathToNode(intersectionNode,goalNode, isFast);
                    break;
                }

                ArrayList<Node> neighbourList = getNeighbours(curStart);

                for (Node n: neighbourList){
                    if (!visitedListStart.contains(n)){
                        if (n.parent == null){
                            n.parent = curStart;
                        } else {
                            n.secondParent = n.parent;
                            n.parent = curStart;
                        }
                        queueStart.add(n);
                        visitedListStart.add(n);
                    }
                }
            }

            if (curGoal != null){

                curGoal.isFromGoal = true;
                curGoal.setAsSearched();
                numOfNodesVisited += 1;
                if (intersects(curGoal, visitedListStart, visitedListGoal)){
                    System.out.println("from goal");
                    endTime = System.nanoTime();
                    elapsedTime = (double) (endTime - startTime) / 1000000000;

                    numOfNodesToGoal += 2;
                    backTrackPathToNode(intersectionNode,startNode, isFast);
                    backTrackPathToNode(intersectionNode,goalNode, isFast);
                    break;
                }

                ArrayList<Node> neighbourList2 = getNeighbours(curGoal);

                for (Node n: neighbourList2){
                    if (!visitedListGoal.contains(n)){
                        if (n.parent == null){
                            n.parent = curGoal;
                        } else {
                            n.secondParent = curGoal;
                        }

                        queueGoal.add(n);
                        visitedListGoal.add(n);
                    }
                }
            }
        }
        System.out.println("Queue is empty");
    }


    public boolean intersects(Node n, ArrayList<Node> visitedList1, ArrayList<Node> visitedList2){
        if (visitedList1.contains(n) && visitedList2.contains(n)){
            intersectionNode = n;

            System.out.println("Intersection node: " + intersectionNode);

            System.out.println("Intersection node parent: " + intersectionNode.parent);
            System.out.println("Intersection node secondParent: " + intersectionNode.secondParent);
            intersectionNode.setAsIntersectedPath();
            return true;
        } else{
            return false;
        }
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

    public void backTrackPathToNode(Node start, Node goal, boolean isFast) throws InterruptedException {
        Node cur = start;

        if (goal.isStart){
            cur = cur.parent;
        } else if (goal.isGoal){
            cur = cur.secondParent;
        }


        if (!cur.isGoal && !cur.isStart){
            cur.setAsPath();
            numOfNodesToGoal += 1;
        }

        while (cur != goal){
            if(isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(10);
            }


            cur = cur.parent;

            if (cur != goal){
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
        return "BFS";
    }


}
