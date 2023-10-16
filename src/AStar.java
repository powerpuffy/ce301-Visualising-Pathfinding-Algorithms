import java.util.ArrayList;
import java.util.Collections;

public class AStar {



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

    public int leastFIndex(ArrayList<Node> openList){

        int indexOfBestNode = 0;
        int maxFCost = 1000;

        for (Node n: openList){
            System.out.println(n);
            if (n.fCost < maxFCost){
                maxFCost = n.fCost;
                indexOfBestNode = openList.indexOf(n);
            }
        }

        return indexOfBestNode;
    }


    public void startSearch() throws InterruptedException {
        System.out.println("start search");


        openList.add(startNode);

        while (!openList.isEmpty()){
            Thread.sleep(10);
            System.out.println("in while loop");

            int indexOfBestNode = leastFIndex(openList);

            Node cur = openList.get(indexOfBestNode);

            if (cur.goal){
                backTrackPath();
                break;
            }

            cur.setAsSearched();

            openList.remove(indexOfBestNode);
            closedList.add(cur);

            System.out.println("successors");

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!closedList.contains(n)){
                    n.parent = cur;

                    n.fCost = calculateH(cur,goalNode);
                    openList.add(n);
                }
            }
            Collections.sort(openList);

        }


        System.out.println("ended");
    }

    public ArrayList<Node> getNeighbours(Node n){

        ArrayList<Node> neighbourList = new ArrayList<>();

        System.out.println(n);



        if (n.col - 1 >= 0){
            Node uppernode = nodeArray[n.col-1][n.row];
            if (!uppernode.wall){
                neighbourList.add(uppernode);
            }
        }

        if (n.col + 1 < maxCol){
            Node rightnode = nodeArray[n.col+1][n.row];
            if (!rightnode.wall){
                neighbourList.add(rightnode);

            }
        }

        if (n.row + 1 < maxRow){
            Node downnode = nodeArray[n.col][n.row+1];
            if (!downnode.wall){
                neighbourList.add(downnode);

            }
        }

        if (n.row - 1 >= 0){
            Node leftnode = nodeArray[n.col][n.row-1];
            if (!leftnode.wall){
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
            cur = cur.parent;

            if (cur != startNode){
                cur.setAsPath();
            }

        }

    }

    public int calculateH(Node cur, Node goalNode){
        return Math.abs(cur.col - goalNode.col) + Math.abs(cur.row - goalNode.row);
    }

    public int calculateH(int currentX, int currentY, int goalX, int goalY){
        return Math.abs(currentX - goalX) + Math.abs(currentY - goalY);
    }

}
