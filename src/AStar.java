import java.util.ArrayList;

public class AStar {

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> closedList = new ArrayList<>();

    Node startNode;
    Node goalNode;
    Node currentNode;

    Node[][] nodeArray;

    int maxCol;
    int maxRow;

    public AStar(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }


    public void startSearch(){
        System.out.println("start search");
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();
        ArrayList<Node> successorList = new ArrayList<>();

        openList.add(startNode);

        while (!openList.isEmpty()){
            System.out.println("in while loop");

            int indexOfBestNode = 0;
            int maxFCost = 1000;

            for (Node n: openList){
                System.out.println(n);
                if (n.fCost < maxFCost){
                    maxFCost = n.fCost;
                    indexOfBestNode = openList.indexOf(n);
                }
            }

            Node q = openList.get(indexOfBestNode);
            openList.remove(indexOfBestNode);

            System.out.println("successors");

            //CHECK THAT THE CORRECT NODES ARE BEING OPENED

            // Open the up node
            if (q.row - 1 >= 0){
                Node upperNode = nodeArray[q.col][q.row-1];
                upperNode.parent = q;

                successorList.add(upperNode);

            }

            // Open the left node
            if (q.col - 1 >= 0){
                Node leftNode = nodeArray[q.col-1][q.row];
                leftNode.parent = q;
                successorList.add(leftNode);
            }

            // Open the down node
            if (q.row + 1 < maxRow){
                Node downNode = nodeArray[q.col][q.row+1];
                downNode.parent = q;
                successorList.add(downNode);
            }

            // Open the right node
            if (q.col + 1 < maxCol){
                Node rightNode = nodeArray[q.col+1][q.row];
                rightNode.parent = q;
                successorList.add(rightNode);
            }

            for (Node n: successorList){
                System.out.println(n);
                if (n.goal){
                    System.out.println("goal reached");
                    break;
                } else {

                    // these will go
                    int distanceFromUpperNodeandQ = 2;
                    n.gCost = q.gCost + distanceFromUpperNodeandQ;

                    System.out.println("create heuristic");
                    Manhattan myheuristic = new Manhattan();
                    n.hCost = myheuristic.calculateH(n.row,n.col,goalNode.row,goalNode.col);

                }
            }
            closedList.add(q);
        }


        System.out.println("ended");
    }

}
