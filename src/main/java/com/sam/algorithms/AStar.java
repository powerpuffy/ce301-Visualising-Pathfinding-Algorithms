package main.java.com.sam.algorithms;

import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.heuristics.Heuristic;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.Collections;

public class AStar extends PathfindingAlgorithm {

    Heuristic heuristic;

    public AStar(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow, Heuristic heuristic) {
        super("A* - " + heuristic.toString(),startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
        this.heuristic = heuristic;
    }

    public AStar(String s, Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super(s,startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
    }

    public int leastFIndex(ArrayList<Node> openList){

        int indexOfBestNode = 0;
        int maxFCost = 1000;
        int maxHCost = 1000;

        for (Node n: openList){
            //System.out.println(n);
            if (n.fCost < maxFCost){
                maxFCost = n.fCost;
                if(n.hCost < maxHCost){

                    maxHCost = n.hCost;
                    indexOfBestNode = openList.indexOf(n);
                }

            }
        }

        System.out.println(openList.get(indexOfBestNode));
        return indexOfBestNode;
    }


    @Override
    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;



        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        System.out.println("start search");

        // Maybe add array of startnodes so you can search from multiple points
        openList.add(startNode);

        while (!openList.isEmpty()){
            System.out.println("SPEED : "  + ControlPanel.algoSpeed);

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }


            //System.out.println("in while loop");
            //System.out.println("open list size: " + openList.size());
            //System.out.println(openList);

            //int indexOfBestNode = leastFIndex(openList);
            //System.out.println(indexOfBestNode);

            //main.java.com.sam.util.Node cur = openList.get(indexOfBestNode);
            Node cur = openList.get(0);

            if (cur.isGoal){
                endTime = System.nanoTime();
                elapsedTime = (double) (endTime - startTime) / 1000000000;

                numOfNodesToGoal += 1;
                backTrackPath(isFast);
                break;
            }

            cur.setAsSearched();
            numOfNodesVisited += 1;

            openList.remove(0);
            //System.out.println("open list size after remove: " + openList.size());
            closedList.add(cur);

            //System.out.println("successors");

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!closedList.contains(n) && !openList.contains(n)) {

                    int gCost = n.weight + cur.gCost;

                    int hCost = 0;
                    if (goalNode != null) {
                        hCost = heuristic.calculateHeuristic(n, goalNode);
                    }


                    int fCost = gCost + hCost;
                    System.out.println(fCost);
                    System.out.println(n.fCost);

                    if (fCost < n.fCost){
                        System.out.println("Pwow");
                        n.parent = cur;
                        n.gCost = gCost;
                        n.hCost = hCost;
                        n.fCost = fCost;
                        openList.add(n);

                        n.setAsOpen();  // Comment out if you dont want the blue open nodes shown

                    }



                    //n.fCost = n.gCost;
                    //n.fCost = n.hCost;

                } else if (!closedList.contains(n) && openList.contains(n)){
                    System.out.println("moo");
                    int fcost2 = cur.fCost + cur.weight;
                    int gcost2 = cur.gCost + cur.weight;
                    if (fcost2 < n.fCost || gcost2 < n.gCost){
                        n.gCost = gcost2;
                        n.fCost = n.gCost + n.hCost;
                        openList.remove(n);
                        //Collections.sort(openList);
                        openList.add(n);
                        //Collections.sort(openList);
                        System.out.println("bark");
                        n.parent = cur;
                    }
                }
            }
            Collections.sort(openList);

        }


        System.out.println("ended");
    }


    public int calculateHManhattan(Node cur, Node goalNode){
        return Math.abs(cur.col - goalNode.col) + Math.abs(cur.row - goalNode.row);
    }
    public int calculateHEuclidean(Node cur, Node goalNode){
        return (int) Math.sqrt((Math.pow(cur.col - goalNode.col,2) + Math.pow(cur.row - goalNode.row,2)));
    }

    @Override
    public String toString() {
        return "A*";
    }
}
