public class Manhattan extends Heuristic{

    Manhattan(){
        this.name = "Manhattan";
        this.description = "It is nothing but the sum of absolute values of differences in the goal’s x and y coordinates and the current cell’s x and y coordinates respectively";
    }

    public int calculateH(int currentX, int currentY, int goalX, int goalY){
        return Math.abs(currentX - goalX) + Math.abs(currentY - goalY);
    }


}
