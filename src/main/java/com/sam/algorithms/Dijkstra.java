package main.java.com.sam.algorithms;

import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.ui.GridPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra extends AStar {
    public Dijkstra(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super("Dijkstra",startNode, goalNode, currentNode, nodeArray, maxCol, maxRow);
    }

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

            int indexOfBestNode = leastFIndex(openList);

            Node cur = openList.get(indexOfBestNode);

            if (cur.isGoal){
                endTime = System.nanoTime();
                elapsedTime = (double) (endTime - startTime) / 1000000000;

                numOfNodesToGoal += 1;
                backTrackPath(isFast);
                break;
            }

            cur.setAsSearched();
            numOfNodesVisited += 1;

            openList.remove(indexOfBestNode);
            //System.out.println("open list size after remove: " + openList.size());
            closedList.add(cur);

            //System.out.println("successors");

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!closedList.contains(n) && !openList.contains(n)){
                    n.parent = cur;

                    n.gCost =  n.weight + cur.gCost;

                    n.fCost = n.gCost;
                    openList.add(n);
                }
            }
            Collections.sort(openList);

        }


        System.out.println("ended");
    }

}
