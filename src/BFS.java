import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

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


    public void startSearch(){

        queue.add(startNode);
        visitedList.add(startNode);

        while (!queue.isEmpty()){


            Node cur = queue.remove();

            if (cur.goal){
                break;
            }

            cur.setAsSearched();
            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!visitedList.contains(n)){
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


}
