import java.util.ArrayList;

public class RandomizedPrims {

    Node startNode;
    Node goalNode;
    Node currentNode;

    Node[][] nodeArray;

    int maxCol;
    int maxRow;


    public RandomizedPrims(Node[][] nodeArray, int maxCol, int maxRow) {
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }



    private void setAllToWall(){
        for (Node[] na: nodeArray){
            for(Node n: na){
                n.setAsWall();
            }
        }
    }

    private Node getRandomNode(){
        int count = 0;
        for (Node[] na: nodeArray){
            for(Node n: na){
                count++;
            }
        }

        int random = (int) (Math.random() * count);
        int row = Math.floorDiv(random,maxRow);
        int col = random - (row * maxCol);

        return nodeArray[row][col];
    }


    private ArrayList<Node> getFrontierNodes(Node n){
        ArrayList<Node> frontierList = new ArrayList<>();

        if (n.row - 2 >= 0){
            Node uppernode = nodeArray[n.col][n.row-2];
            if (uppernode.isWall){
                frontierList.add(uppernode);
            }
        }

        if (n.col + 2 < maxCol){
            Node rightnode = nodeArray[n.col+2][n.row];
            if (rightnode.isWall){
                frontierList.add(rightnode);
            }
        }

        if (n.row + 2 < maxRow){
            Node downnode = nodeArray[n.col][n.row+2];
            if (downnode.isWall){
                frontierList.add(downnode);
            }
        }

        if (n.col - 2 >= 0){
            Node leftnode = nodeArray[n.col-2][n.row];
            if (leftnode.isWall){
                frontierList.add(leftnode);
            }
        }

        return frontierList;
    }




    public void generateMaze(){
        setAllToWall();

        Node firstNode = getRandomNode();
        System.out.println(firstNode);
        firstNode.setAsDefault();

        ArrayList<Node> frontierList = getFrontierNodes(firstNode);
        for (Node n: frontierList){
            n.setAsDefault();
        }



    }





}
