package main.java.com.sam.algorithms;

import main.java.com.sam.data.PathfindingData;
import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.ui.GridPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends PathfindingAlgorithm {



    public BFS(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super("BFS",startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
    }

    public BFS(GridPanel gridPanel) {
        super("DFS");
        this.startNode = gridPanel.startNode;
        this.goalNode = gridPanel.goalNode;
        this.currentNode = gridPanel.currentNode;
        this.nodeArray = gridPanel.nodeArray;
        this.maxCol = gridPanel.maxCol;
        this.maxRow = gridPanel.maxRow;
    }

    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;

        queue.add(startNode);
        System.out.println(startNode);
        visitedList.add(startNode);

        while (!queue.isEmpty()){

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            Node cur = queue.remove();

            if (cur.isGoal){
                endTime = System.nanoTime();
                elapsedTime = (double) (endTime - startTime) / 1000000000;

                numOfNodesToGoal += 1;
                backTrackPath(isFast);
                break;
            }


            cur.setAsSearched();
            numOfNodesVisited += 1;

            ArrayList<Node> neighbourList = getNeighbours(cur);

            for (Node n: neighbourList){
                if (!visitedList.contains(n)){
                    n.parent = cur;
                    queue.add(n);
                    visitedList.add(n);
                }
            }

        }

        System.out.println("Queue is empty");
    }

    @Override
    public String toString() {
        return "main.java.sam.algorithms.BFS";
    }


}
