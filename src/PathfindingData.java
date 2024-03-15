import java.util.Arrays;

public class PathfindingData {

    int run;
    String algorithm;

    int maxCol;
    int maxRow;
    String map;

    int totalNumOfNodes;
    int numOfNodesVisited;
    int numOfNodesToGoal;

    double elapsedTime;

    long seed;

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
                "run=" + run +
                ", algorithm='" + algorithm + '\'' +
                ", maxCol=" + maxCol +
                ", maxRow=" + maxRow +
                ", map='" + map + '\'' +
                ", totalNumOfNodes=" + totalNumOfNodes +
                ", numOfNodesVisited=" + numOfNodesVisited +
                ", numOfNodesToGoal=" + numOfNodesToGoal +
                ", elapsedTime=" + elapsedTime +
                ", seed=" + seed +
                '}';
    }

    public String toCSV() {
        return run + "," + algorithm + "," + maxCol + "x" + maxRow + "," + totalNumOfNodes + "," + numOfNodesVisited + "," + numOfNodesToGoal + "," + elapsedTime + "," + seed;
    }
}
