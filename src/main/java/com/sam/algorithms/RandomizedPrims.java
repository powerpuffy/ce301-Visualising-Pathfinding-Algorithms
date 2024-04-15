package main.java.com.sam.algorithms;

import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.util.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class RandomizedPrims {

    Node startNode;
    Node goalNode;
    Node currentNode;

    Node[][] nodeArray;

    int maxCol;
    int maxRow;

    public long seed;

    Random rng;

    public RandomizedPrims(Node[][] nodeArray, int maxCol, int maxRow) {
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;

        Random temprng =  new Random();
        this.seed = temprng.nextLong();

        this.rng = new Random(seed);
    }

    public RandomizedPrims(Node[][] nodeArray, int maxCol, int maxRow, long seed) {
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        this.rng = new Random(seed);
        System.out.println(this.rng);
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

        //System.out.println(maxCol);
        //System.out.println(maxRow);
        int numberOfNodes = maxCol * maxRow;

        //int random = (int) (Math.random() * numberOfNodes);
        int random = (int) (rng.nextFloat() * numberOfNodes);
        int row = random % maxRow;
        int col = random % maxCol;

        //System.out.println(row);
        //System.out.println(col);

        return nodeArray[col][row];
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
        //int random = (int) (Math.random() * (nodeList.size()));
        int random = (int) (rng.nextFloat() * nodeList.size());
        //System.out.println("Random: " + random );
        return  nodeList.get(random);
    }

    public Node getInbetweenNode(Node firstNode, Node lastNode){
        int inbetweenNodeY = firstNode.row - lastNode.row;
        int inbetweenNodeX = firstNode.col - lastNode.col;

        System.out.println("("+inbetweenNodeX+","+inbetweenNodeY+")");

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
            //System.out.println("First main.java.com.sam.util.Node: " + frontierNode.frontierParent + "   Inbetween main.java.com.sam.util.Node: " + inbetweenNode + "   Frontier main.java.com.sam.util.Node" + frontierNode);
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

        System.out.println("Seed:" + seed);

        setAllToWall();

        Node curNode = getRandomNodeFromGrid();
        curNode.setAsDefault();

        ArrayList<Node> frontierList = getFrontierNodes(curNode);

        /*
        for (main.java.com.sam.util.Node n : frontierList){
            n.setBackground(new Color(169, 100, 217));
        }

         */

        while (!frontierList.isEmpty()){



            /*
            for (main.java.com.sam.util.Node n : frontierList){
                n.setBackground(new Color(169, 100, 217));
            }

             */

            Node frontierNode = getRandomNode(frontierList);
            frontierList.remove(frontierNode);




            frontierNode.setAsDefault();


            Node inbetweenNode = getInbetweenNode(frontierNode.frontierParent,frontierNode);
            //System.out.println("First main.java.com.sam.util.Node: " + frontierNode.frontierParent + "   Inbetween main.java.com.sam.util.Node: " + inbetweenNode + "   Frontier main.java.com.sam.util.Node" + frontierNode);
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
