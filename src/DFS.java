import java.util.ArrayList;
import java.util.Stack;

public class DFS extends PathfindingAlgorithm{

    ArrayList<Node> visitedListStart = new ArrayList<>();

    ArrayList<Node> visitedListGoal = new ArrayList<>();

    Stack<Node> stackStart = new Stack<>();

    Stack<Node> stackGoal = new Stack<>();

    Node startNode;
    Node goalNode;
    Node currentNode;
    Node intersectionNode;
    Node[][] nodeArray;
    int maxCol;
    int maxRow;



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

    /*
    public void startSearch() throws InterruptedException {

        stack.add(startNode);
        visitedList.add(startNode);

        while (!stack.isEmpty()){
            Thread.sleep(ControlPanel.algoSpeed);
            System.out.println(stack);


            Node cur = stack.pop();
            visitedList.add(cur);

            if (cur.isGoal){
                backTrackPath();
                break;
            }

            cur.setAsSearched();
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

     */

    public void startSearch(boolean isFast) throws InterruptedException {

        stackStart.add(startNode);
        visitedListStart.add(startNode);

        stackGoal.add(goalNode);
        visitedListGoal.add(goalNode);

        while (!stackStart.isEmpty() || !stackGoal.isEmpty()){

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            System.out.println(stackStart);

            Node curStart = null;
            Node curGoal = null;

            if (!stackStart.isEmpty()){
                curStart = stackStart.pop();
                visitedListStart.add(curStart);
            }

            if (!stackGoal.isEmpty()){
                curGoal = stackGoal.pop();
                visitedListGoal.add(curGoal);
            }

            if (curStart != null){

                curStart.setAsSearched();
                if (intersects(curStart, visitedListStart, visitedListGoal)){
                    System.out.println("from start");
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
                        stackStart.add(n);
                        visitedListStart.add(n);
                    }
                }
            }

            if (curGoal != null){

                curGoal.isFromGoal = true;
                curGoal.setAsSearched();
                if (intersects(curGoal, visitedListStart, visitedListGoal)){
                    System.out.println("from goal");
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

                        stackGoal.add(n);
                        visitedListGoal.add(n);
                    }
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

    public void backTrackPathToNode(Node start, Node goal, boolean isFast) throws InterruptedException {
        Node cur = start;

        if (goal.isStart){
            cur = cur.parent;
        } else if (goal.isGoal){
            cur = cur.secondParent;
        }

        if (!cur.isGoal && !cur.isStart){
            cur.setAsPath();
        }


        while (cur != goal){
            if(isFast){
                Thread.sleep(2);
            } else{
                Thread.sleep(10);
            }

            cur = cur.parent;

            if (cur != goal){
                cur.setAsPath();
            }
        }
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

    @Override
    public String toString() {
        return "DFS";
    }

}
