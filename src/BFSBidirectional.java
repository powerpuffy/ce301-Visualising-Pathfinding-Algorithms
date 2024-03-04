import java.util.ArrayList;

public class BFSBidirectional extends BFS{
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

}
