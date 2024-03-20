package main.java.com.sam.algorithms;

import main.java.com.sam.data.PathfindingData;
import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.ui.GridPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;

public class BFSBidirectional extends BFS {
    public BFSBidirectional(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super(startNode, goalNode, currentNode, nodeArray, maxCol, maxRow);
    }

    public BFSBidirectional(GridPanel gridPanel) {
        super(gridPanel);
    }

    public void startSearch(boolean isFast) throws InterruptedException {

        startTime = System.nanoTime();
        endTime = 0.0;

        totalNumOfNodes = this.maxCol * this.maxRow;
        numOfNodesVisited = 0;
        numOfNodesToGoal = 0;

        elapsedTime = 0.0;


        queueStart.add(startNode);
        System.out.println(startNode);
        visitedListStart.add(startNode);

        queueGoal.add(goalNode);
        System.out.println(goalNode);
        visitedListGoal.add(goalNode);


        while (!queueStart.isEmpty() || !queueGoal.isEmpty()){

            if (isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            Node curStart = null;
            Node curGoal = null;


            if (!queueStart.isEmpty()){
                curStart = queueStart.remove();
            }

            if (!queueGoal.isEmpty()){
                curGoal = queueGoal.remove();
            }


            if (curStart != null){

                curStart.setAsSearched();
                numOfNodesVisited += 1;
                if (intersects(curStart, visitedListStart, visitedListGoal)){
                    System.out.println("from start");
                    endTime = System.nanoTime();
                    elapsedTime = (double) (endTime - startTime) / 1000000000;

                    numOfNodesToGoal += 2;
                    backTrackPathToNode(intersectionNode,startNode, isFast);
                    backTrackPathToNode(intersectionNode,goalNode, isFast);
                    break;
                }

                ArrayList<Node> neighbourList = getNeighbours(curStart);

                for (Node n: neighbourList){
                    if (!visitedListStart.contains(n)){
                        if (n.parent == null){
                            n.parent = curStart;
                        } else {
                            n.secondParent = n.parent;
                            n.parent = curStart;
                        }
                        queueStart.add(n);
                        visitedListStart.add(n);
                    }
                }
            }

            if (curGoal != null){

                curGoal.isFromGoal = true;
                curGoal.setAsSearched();
                numOfNodesVisited += 1;
                if (intersects(curGoal, visitedListStart, visitedListGoal)){
                    System.out.println("from goal");
                    endTime = System.nanoTime();
                    elapsedTime = (double) (endTime - startTime) / 1000000000;

                    numOfNodesToGoal += 2;
                    backTrackPathToNode(intersectionNode,startNode, isFast);
                    backTrackPathToNode(intersectionNode,goalNode, isFast);
                    break;
                }

                ArrayList<Node> neighbourList2 = getNeighbours(curGoal);

                for (Node n: neighbourList2){
                    if (!visitedListGoal.contains(n)){
                        if (n.parent == null){
                            n.parent = curGoal;
                        } else {
                            n.secondParent = curGoal;
                        }

                        queueGoal.add(n);
                        visitedListGoal.add(n);
                    }
                }
            }
        }
        System.out.println("Queue is empty");
    }

    public boolean intersects(Node n, ArrayList<Node> visitedList1, ArrayList<Node> visitedList2){
        if (visitedList1.contains(n) && visitedList2.contains(n)){
            intersectionNode = n;

            System.out.println("Intersection node: " + intersectionNode);

            System.out.println("Intersection node parent: " + intersectionNode.parent);
            System.out.println("Intersection node secondParent: " + intersectionNode.secondParent);
            intersectionNode.setAsIntersectedPath();
            return true;
        } else{
            return false;
        }
    }

    public void backTrackPathToNode(Node start, Node goal, boolean isFast) throws InterruptedException {
        Node cur = start;

        if (goal.isStart){
            cur = cur.parent;
        } else if (goal.isGoal){
            cur = cur.secondParent;
        }


        if (!cur.isGoal && !cur.isStart){
            cur.setAsPath();
            numOfNodesToGoal += 1;
        }

        while (cur != goal){
            if(isFast){
                Thread.sleep(1);
            } else{
                Thread.sleep(10);
            }


            cur = cur.parent;

            if (cur != goal){
                cur.setAsPath();
                numOfNodesToGoal += 1;
            }
        }
    }

    public PathfindingData getPathfindingDataObject(){
        return new PathfindingData(this.algorithm, this.totalNumOfNodes, this.numOfNodesVisited, this.numOfNodesToGoal, this.elapsedTime);
    }

}
