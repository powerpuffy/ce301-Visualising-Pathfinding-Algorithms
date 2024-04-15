package main.java.com.sam.algorithms;

import main.java.com.sam.algorithms.PathfindingAlgorithm;
import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.util.Node;

import java.util.ArrayList;
import java.util.Comparator;

public class RandomWalk extends PathfindingAlgorithm {



    public RandomWalk(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super("Random Walk",startNode,goalNode,currentNode,nodeArray,maxCol,maxRow);
    }

    @Override
    public void startSearch(boolean isFast) throws InterruptedException {
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        System.out.println("start search");

        // Maybe add array of startnodes so you can search from multiple points
        //openList.add(startNode);

        // Something I think would be cool would be to track how many times each node was visisted and then display that as a shade of a colour so you can see the most popular nodes

        Node cur = startNode;
        Node parent = null;

        int x = 0;

        while (!cur.isGoal){
            x++;
            System.out.println(x);
            cur.setAsCurrentRandomWalk();
            if (!visitedList.contains(cur)){
                visitedList.add(cur);
            }

            cur.visitedCount += 1;

            if (isFast){
                Thread.sleep(0);
            } else{
                Thread.sleep(ControlPanel.algoSpeed);
            }

            ArrayList<Node> neighbourList = getNeighbours(cur);
            cur.setAsSearched();


            parent = cur;
            //System.out.println(parent);
            //System.out.println(neighbourList);
            //getRandomNodeBiased(neighbourList,parent);

            cur  = getRandomNodeBiased(neighbourList,cur);
            //cur = getRandomNode(neighbourList);

            cur.parent = parent;
        }


        System.out.println("ended");

        createHeatMap();
    }

    public void createHeatMap(){
        Comparator<Node> byVisitedCount = Comparator
                .comparing(Node::getVisitedCount);
        visitedList.sort(byVisitedCount);
        int minimumVisitedCount = visitedList.get(0).visitedCount;
        int maximumVisitedCount = visitedList.get(visitedList.size()-1).visitedCount;

        System.out.println(minimumVisitedCount);
        System.out.println(maximumVisitedCount);

        for (Node n: visitedList){
            int normaliseVisitedCount = (int) normaliseVisitedCount(n.visitedCount,minimumVisitedCount,maximumVisitedCount);
            n.setShadeOfBlue(normaliseVisitedCount);
        }
    }


    public double normaliseVisitedCount(int visitedCount, int minimumVisitedCount, int maximumVisitedCount){
        return ((double)(visitedCount - minimumVisitedCount) / (double)(maximumVisitedCount - minimumVisitedCount)) * 200;
    }

    public Node getRandomNode (ArrayList<Node> nodeList){
        int random = (int) (Math.random() * (nodeList.size()));
        System.out.println("Random: " + random );
        return  nodeList.get(random);
    }



    public static Node getRandomNodeBiased(ArrayList<Node> nodeList, Node cur){

        // Reset all their probability weights back to 2
        for (Node n : nodeList){
            n.probabilityWeight = 2;
        }

        double totalProbabilityWeights = 0;

        // Set the parent of the current node's probabilityWeight to 0.1
        for (Node n : nodeList){
            if (n.equals(cur.parent)){
                n.probabilityWeight = 0.1;
                //System.out.println("setting to 1");
            }
            totalProbabilityWeights += n.probabilityWeight;
        }

        // Create list of probabilities of each of the neighbouring nodes
        ArrayList<Double> probabilityList = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++){
            probabilityList.add((double) (nodeList.get(i).probabilityWeight/totalProbabilityWeights));
        }

        // Create the random num from 0 to 1
        double random = Math.random();
        System.out.println("Random : " + random);


        double cumulativeProbability = 0;
        System.out.println("Probability list" + probabilityList);
        for (int i = 0; i < probabilityList.size(); i++){
            cumulativeProbability += probabilityList.get(i);
            System.out.println("Cumulative probability:" + cumulativeProbability);

            if (random < cumulativeProbability){


                if (nodeList.get(i) == cur.parent){
                    System.out.println("Going BACK");
                }
                return nodeList.get(i);
            }
        }

        System.out.println("Returning null");
        return null;
    }

}
