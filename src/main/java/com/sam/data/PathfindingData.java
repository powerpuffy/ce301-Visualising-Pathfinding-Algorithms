package main.java.com.sam.data;

public class PathfindingData {

    int run;
    String algorithm;

    int maxCol;
    int maxRow;
    String map;

    int totalNumOfNodes;

    int totalNumOfVisitableNodes;
    int numOfNodesVisited;
    int numOfNodesToGoal;

    double elapsedTime;

    long seed;

    public PathfindingData(String algorithm, int totalNumOfNodes, int numOfNodesVisited, int numOfNodesToGoal, double elapsedTime) {
        this.algorithm = algorithm;
        this.totalNumOfNodes = totalNumOfNodes;
        this.totalNumOfVisitableNodes = totalNumOfVisitableNodes;
        this.numOfNodesVisited = numOfNodesVisited;
        this.numOfNodesToGoal = numOfNodesToGoal;
        this.elapsedTime = elapsedTime;
    }

    @Override
    public String toString() {
        return "Data.PathfindingData{" +
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

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getTotalNumOfNodes() {
        return totalNumOfNodes;
    }

    public void setTotalNumOfNodes(int totalNumOfNodes) {
        this.totalNumOfNodes = totalNumOfNodes;
    }

    public int getTotalNumOfVisitableNodes() {
        return totalNumOfVisitableNodes;
    }

    public void setTotalNumOfVisitableNodes(int totalNumOfVisitableNodes) {
        this.totalNumOfVisitableNodes = totalNumOfVisitableNodes;
    }

    public int getNumOfNodesVisited() {
        return numOfNodesVisited;
    }

    public void setNumOfNodesVisited(int numOfNodesVisited) {
        this.numOfNodesVisited = numOfNodesVisited;
    }

    public int getNumOfNodesToGoal() {
        return numOfNodesToGoal;
    }

    public void setNumOfNodesToGoal(int numOfNodesToGoal) {
        this.numOfNodesToGoal = numOfNodesToGoal;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public String toCSV() {
        return run + "," + algorithm + "," + maxCol + "x" + maxRow + "," + totalNumOfNodes + "," + numOfNodesVisited + "," + numOfNodesToGoal + "," + elapsedTime + "," + seed;
    }
}
