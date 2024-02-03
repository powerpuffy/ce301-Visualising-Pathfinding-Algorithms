import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends PathfindingAlgorithm{

    ArrayList<Node> visitedListStart = new ArrayList<>();
    ArrayList<Node> visitedListGoal = new ArrayList<>();

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

    public void startSearch() throws InterruptedException {
        queueStart.add(startNode);
        System.out.println(startNode);
        visitedListStart.add(startNode);

        queueGoal.add(goalNode);
        System.out.println(goalNode);
        visitedListGoal.add(goalNode);


        while (!queueStart.isEmpty() || !queueGoal.isEmpty()){

            Thread.sleep(ControlPanel.algoSpeed);

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

                if (intersects(curStart, visitedListStart, visitedListGoal)){
                    System.out.println("from start");
                    fromStart = true;
                    //backTrackPath();
                    backTrackPathToNode(intersectionNode,startNode);
                    backTrackPathToNode(intersectionNode,goalNode);
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

            //Thread.sleep(100);

            if (curGoal != null){

                curGoal.setAsSearched2();

                if (intersects(curGoal, visitedListStart, visitedListGoal)){
                    fromStart = false;
                    System.out.println("from goal");
                    //backTrackPath();

                    backTrackPathToNode(intersectionNode,startNode);
                    backTrackPathToNode(intersectionNode,goalNode);
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

    public void backTrackPath() throws InterruptedException {
        Node cur = intersectionNode;
        System.out.println("Intersection node: " + intersectionNode);


        while (cur != goalNode){
            Thread.sleep(10);
            System.out.println("Backtrack");
            System.out.println(cur);
            //System.out.println("DASdas");
            cur = cur.secondParent;
            System.out.println(cur);
            //cur.setAsPath();


            if (cur != goalNode){
                cur.setAsPath();
            }



        }

    }

    public void backTrackPathToNode(Node start, Node goal) throws InterruptedException {
        Node cur = start;
        //System.out.println("Intersection node: " + intersectionNode);
        if (goal.isStart){
            cur = cur.parent;
        } else if (goal.isGoal){
            cur = cur.secondParent;
        }

        while (cur != goal){
            Thread.sleep(10);
            System.out.println("Backtrack2");
            System.out.println(cur);
            //System.out.println("DASdas");


            cur = cur.parent;
            System.out.println(cur);
            //cur.setAsPath();


            if (cur != goal){
                cur.setAsPath();
            }



        }

    }

    @Override
    public String toString() {
        return "BFS";
    }


}
