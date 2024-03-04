import java.util.ArrayList;

public class DFSBidirectional extends DFS{
    public DFSBidirectional(Node startNode, Node goalNode, Node currentNode, Node[][] nodeArray, int maxCol, int maxRow) {
        super(startNode, goalNode, currentNode, nodeArray, maxCol, maxRow);
    }

    public DFSBidirectional(GridPanel gridPanel) {
        super(gridPanel);
    }


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


}
