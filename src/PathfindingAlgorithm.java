public abstract class PathfindingAlgorithm {

    public GridPanel gridpanel;
    private String algorithm;

    int totalNumOfNodes;

    int numOfNodesVisited;

    int numOfNodesToGoal;

    float elapsedTime;

    long seed;


    abstract void startSearch(boolean isFast) throws InterruptedException;



    public PathfindingData getPathfindingDataObject(){
        return new PathfindingData(this.algorithm, this.totalNumOfNodes, this.numOfNodesVisited, this.numOfNodesToGoal, this.elapsedTime);
    }

    void setGridpanel(GridPanel gridpanel){
        System.out.println("wow");
        this.gridpanel = gridpanel;
        System.out.println(this.gridpanel);
    }

}
