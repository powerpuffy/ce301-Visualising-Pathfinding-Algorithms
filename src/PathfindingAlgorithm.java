public abstract class PathfindingAlgorithm {

    public GridPanel gridpanel;


    abstract void startSearch(boolean isFast) throws InterruptedException;

    abstract PathfindingData getPathfindingDataObject();



    void setGridpanel(GridPanel gridpanel){
        System.out.println("wow");
        this.gridpanel = gridpanel;
        System.out.println(this.gridpanel);
    }

}
