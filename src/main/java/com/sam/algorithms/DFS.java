package main.java.com.sam.algorithms;

import main.java.com.sam.data.PathfindingData;
import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.ui.GridPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.Stack;

public class DFS extends PathfindingAlgorithm {


    public DFS(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super("DFS",startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
    }

    @Override
    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;

        stack.add(startNode);
        visitedList.add(startNode);

        while (!stack.isEmpty()){

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            System.out.println(stack);


            Node cur = stack.pop();
            visitedList.add(cur);

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
                    stack.push(n);
                    //visitedList.add(cur);
                }
            }

        }

        System.out.println("Stack is empty");

    }



    @Override
    public String toString() {
        return "main.java.sam.algorithms.DFS";
    }

}
