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
            }
        }
    }

    private Node getRandomNodeFromGrid(){
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

        for (Node x: frontierList){
            if (!x.isWall){
                System.out.println("not a wall");
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
        System.out.println(curNode);
        curNode.setAsDefault();

        ArrayList<Node> frontierList = getFrontierNodes(curNode);

        /*
        for (Node n: frontierList){
            n.setAsDefault();
        }

         */
        for (Node n : frontierList){
            n.setBackground(new Color(169, 100, 217));
        }




        while (!frontierList.isEmpty()){

            //Thread.sleep(ControlPanel.algoSpeed);
            //Thread.sleep(5000);

            int wallcount = 0;
            int othercount = 0;

            for (Node n : frontierList){
                if (n.isWall){
                    n.setBackground(new Color(169, 100, 217));
                    wallcount ++;
                } else{
                    othercount++;
                }


            }

            System.out.println("Number of wall nodes: " + wallcount + "     Number of other nodes: " + othercount);

            Node frontierNode = getRandomNode(frontierList);
            frontierList.remove(frontierNode);

            Thread.sleep(200);


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
            System.out.println(frontierList);
            //curNode = inbetweenNode;
        }





    }





}
