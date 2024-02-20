import java.awt.*;
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
                //n.setBorderPainted(false);
            }
        }
    }

    private Node getRandomNodeFromGrid(){

        int numberOfNodes = maxCol * maxRow;
        int random = (int) (Math.random() * numberOfNodes);
        int row = Math.floorDiv(random,maxRow);
        int col = random - (row * maxCol);

        return nodeArray[row][col];
    }


    private ArrayList<Node> getFrontierNodes(Node n){
        ArrayList<Node> frontierList = new ArrayList<>();

        if (n.row - 2 >= 0){
            Node uppernode = nodeArray[n.col][n.row-2];
            if (uppernode.isWall){
                uppernode.frontierParent = n;
                frontierList.add(uppernode);
            }
        }

        if (n.col + 2 < maxCol){
            Node rightnode = nodeArray[n.col+2][n.row];
            if (rightnode.isWall){
                rightnode.frontierParent = n;
                frontierList.add(rightnode);
            }
        }

        if (n.row + 2 < maxRow){
            Node downnode = nodeArray[n.col][n.row+2];
            if (downnode.isWall){
                downnode.frontierParent = n;
                frontierList.add(downnode);
            }
        }

        if (n.col - 2 >= 0){
            Node leftnode = nodeArray[n.col-2][n.row];
            if (leftnode.isWall){
                leftnode.frontierParent = n;
                frontierList.add(leftnode);
            }
        }

        return frontierList;
    }

    public Node getRandomNode(ArrayList<Node> nodeList){
        int random = (int) (Math.random() * (nodeList.size()));
        //System.out.println("Random: " + random );
        return  nodeList.get(random);
    }

    public Node getInbetweenNode(Node firstNode, Node lastNode){
        int inbetweenNodeY = firstNode.row - lastNode.row;
        int inbetweenNodeX = firstNode.col - lastNode.col;

        if (inbetweenNodeY < 0){
            return nodeArray[firstNode.col][firstNode.row+1];
        } else if (inbetweenNodeY > 0) {
            return nodeArray[firstNode.col][firstNode.row-1];
        } else if (inbetweenNodeX < 0) {
            return nodeArray[firstNode.col+1][firstNode.row];
        } else if  (inbetweenNodeX > 0){
            return nodeArray[firstNode.col-1][firstNode.row];
        }

        return null;

    }




    public void generateMaze() throws InterruptedException {
        setAllToWall();

        Node curNode = getRandomNodeFromGrid();
        curNode.setAsDefault();

        ArrayList<Node> frontierList = getFrontierNodes(curNode);

        for (Node n : frontierList){
            n.setBackground(new Color(169, 100, 217));
        }

        while (!frontierList.isEmpty()){



            for (Node n : frontierList){
                n.setBackground(new Color(169, 100, 217));
            }

            Node frontierNode = getRandomNode(frontierList);
            frontierList.remove(frontierNode);

            Thread.sleep(ControlPanel.algoSpeed);
            //Thread.sleep(2000);



            frontierNode.setAsDefault();


            Node inbetweenNode = getInbetweenNode(frontierNode.frontierParent,frontierNode);
            //System.out.println("First Node: " + frontierNode.frontierParent + "   Inbetween Node: " + inbetweenNode + "   Frontier Node" + frontierNode);
            inbetweenNode.setAsDefault();

            //frontierList.addAll(getFrontierNodes(frontierNode));

            for (Node f: getFrontierNodes(frontierNode)){
                if (!frontierList.contains(f)){
                    frontierList.add(f);
                }
            }

        }

    }

    public void generateMazeQuick() throws InterruptedException {
        setAllToWall();

        Node curNode = getRandomNodeFromGrid();
        curNode.setAsDefault();

        ArrayList<Node> frontierList = getFrontierNodes(curNode);

        /*
        for (Node n : frontierList){
            n.setBackground(new Color(169, 100, 217));
        }

         */

        while (!frontierList.isEmpty()){



            /*
            for (Node n : frontierList){
                n.setBackground(new Color(169, 100, 217));
            }

             */

            Node frontierNode = getRandomNode(frontierList);
            frontierList.remove(frontierNode);




            frontierNode.setAsDefault();


            Node inbetweenNode = getInbetweenNode(frontierNode.frontierParent,frontierNode);
            //System.out.println("First Node: " + frontierNode.frontierParent + "   Inbetween Node: " + inbetweenNode + "   Frontier Node" + frontierNode);
            inbetweenNode.setAsDefault();

            //frontierList.addAll(getFrontierNodes(frontierNode));

            for (Node f: getFrontierNodes(frontierNode)){
                if (!frontierList.contains(f)){
                    frontierList.add(f);
                }
            }

        }

    }





}
