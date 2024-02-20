public class PathfindingData {
    String algorithm;
    String map;

    int totalNumOfNodes;
    int numOfNodesVisited;
    int numOfNodesToGoal;

    double elapsedTime;

    public PathfindingData(String algorithm, int totalNumOfNodes, int numOfNodesVisited, int numOfNodesToGoal, double elapsedTime) {
        this.algorithm = algorithm;
        this.totalNumOfNodes = totalNumOfNodes;
        this.numOfNodesVisited = numOfNodesVisited;
        this.numOfNodesToGoal = numOfNodesToGoal;
        this.elapsedTime = elapsedTime;
    }


    @Override
    public String toString() {
        return "PathfindingData{" +
                "algorithm='" + algorithm + '\'' +
                ", totalNumOfNodes='" + totalNumOfNodes + '\'' +
                ", numOfNodesVisited=" + numOfNodesVisited +
                ", numOfNodesToGoal=" + numOfNodesToGoal +
                ", elapsedTime=" + elapsedTime +
                '}';
    }

    public String toCSV() {
        return algorithm + "," + totalNumOfNodes + "," + numOfNodesVisited + "," + numOfNodesToGoal + "," + elapsedTime;
    }
}
