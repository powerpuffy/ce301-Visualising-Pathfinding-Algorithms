import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RandomWalk {

    Node startNode;
    Node goalNode;
    Node currentNode;

    Node[][] nodeArray;

    int maxCol;
    int maxRow;

    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> closedList = new ArrayList<>();
    ArrayList<Node> successorList = new ArrayList<>();

    ArrayList<Node> visitedList = new ArrayList<>();

    public RandomWalk(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.currentNode = currentNode;
        this.nodeArray = nodeArray;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }

    public void startSearch() throws InterruptedException {
        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        System.out.println("start search");

        // Maybe add array of startnodes so you can search from multiple points
        //openList.add(startNode);

        // Something I think would be cool would be to track how many times each node was visisted and then display that as a shade of a colour so you can see the most popular nodes

        Node cur = startNode;
        Node parent = null;

        while (!cur.goal){

            cur.setAsCurrentRandomWalk();
            if (!visitedList.contains(cur)){
                visitedList.add(cur);
            }

            cur.visitedCount += 1;
            Thread.sleep(ControlPanel.algoSpeed);
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

    public static double normaliseVisitedCount(int visitedCount, int minimumVisitedCount, int maximumVisitedCount){
        //System.out.println(visitedCount);
        //System.out.println(minimumVisitedCount);
        //System.out.println(maximumVisitedCount);

        double d =  (double) ((double)(visitedCount - minimumVisitedCount) / (double)(maximumVisitedCount - minimumVisitedCount)) * 200;
        System.out.println(d);
        return d;
    }

    public Node getRandomNode(ArrayList<Node> nodeList){
        int random = (int) (Math.random() * (nodeList.size()));
        System.out.println("Random: " + random );
        return  nodeList.get(random);
    }



    public static Node getRandomNodeBiased(ArrayList<Node> nodeList, Node cur){
        //nodeList.remove(previousNode);
        //previousNode.probabilityWeight = 1;

        // Reset all their probability weights back to 2
        for (Node n : nodeList){
            n.probabilityWeight = 2;
        }

        double totalProbabilityWeights = 0;

        // Set the parent of the current node's probabilityWeight to 0.1
        for (Node n : nodeList){
            if (n.equals(cur.parent)){
                n.probabilityWeight = 0.1;
                System.out.println("setting to 1");
            }
            totalProbabilityWeights += n.probabilityWeight;
        }

        // Create list of probabilities of each of the neighbouring nodes
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++){
            list.add((double) (nodeList.get(i).probabilityWeight/totalProbabilityWeights));
        }

        // Create the random num from 0 to 1
        double random = Math.random();
        System.out.println("Random : " + random);


        double cumulativeProbability = 0;
        System.out.println("Probability list" + list);
        for (int i = 0; i < list.size(); i++){
            cumulativeProbability += list.get(i);
            System.out.println("Cumulative probability:" + cumulativeProbability);

            if (random < cumulativeProbability){

                return nodeList.get(i);
            }
        }


        /*
        double previousNodeProbabilityWeight = 1;
        double node1ProbabilityWeight = 2;
        double node2ProbabilityWeight = 2;
        double node3ProbabilityWeight = 2;

        // totalProbabilityWeights = (int) (previousNodeProbabilityWeight + node1ProbabilityWeight + node2ProbabilityWeight + node3ProbabilityWeight);


        double previousNodeProbability = previousNodeProbabilityWeight / totalProbabilityWeights;
        double node1Probability = node1ProbabilityWeight / totalProbabilityWeights;
        double node2Probability = node2ProbabilityWeight / totalProbabilityWeights;
        double node3Probability = node3ProbabilityWeight / totalProbabilityWeights;

        System.out.println(previousNodeProbability);
        System.out.println(node1Probability);
        System.out.println(node2Probability);
        System.out.println(node3Probability);

         */


        /*
        System.out.println(nodeList);

        if (random < (double) previousNode.probabilityWeight / totalProbabilityWeights){
            return previousNode;
        } else if(random < previousNodeProbability + node1Probability){
            return nodeList.get(0);
        } else if(random < previousNodeProbability + node1Probability + node2Probability){
            return nodeList.get(1);
        } else {
            return nodeList.get(2);
        }

         */
        System.out.println("Returning null");
        return null;
    }


    public ArrayList<Node> getNeighbours(Node n){

        ArrayList<Node> neighbourList = new ArrayList<>();

        if (n.row - 1 >= 0){
            Node uppernode = nodeArray[n.col][n.row-1];
            if (!uppernode.wall){
                neighbourList.add(uppernode);
            }
        }

        if (n.col + 1 < maxCol){
            Node rightnode = nodeArray[n.col+1][n.row];
            if (!rightnode.wall){
                neighbourList.add(rightnode);
            }
        }

        if (n.row + 1 < maxRow){
            Node downnode = nodeArray[n.col][n.row+1];
            if (!downnode.wall){
                neighbourList.add(downnode);
            }
        }

        if (n.col - 1 >= 0){
            Node leftnode = nodeArray[n.col-1][n.row];
            if (!leftnode.wall){
                neighbourList.add(leftnode);
            }
        }

        return neighbourList;
    }

    public void backTrackPath() throws InterruptedException {
        Node cur = goalNode;

        while (cur != startNode){
            Thread.sleep(10);
            //System.out.println(cur);
            cur = cur.parent;

            if (cur != startNode){
                cur.setAsPath();
            }

        }

    }

}
