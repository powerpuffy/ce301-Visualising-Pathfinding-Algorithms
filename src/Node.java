import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Node extends JButton implements ActionListener, Comparable<Node>  {

    public Node parent;
    public int col;
    public int row;
    public int gCost;
    public int hCost;
    public int fCost;

    public int weight;

    public int visitedCount;
    public boolean start;
    public boolean goal;
    public boolean wall;

    public boolean swamp;
    public boolean path;
    public boolean open;
    public boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
        this.weight = 1;
        this.setFocusable(false);

        //setFont(new Font("Arial", Font.PLAIN, 4));

        setBackground(Color.white);
        setForeground(Color.black);

        addActionListener(this);
    }

    public void setAsStart(){
        setAsDefault();
        setBackground(Color.green);
        this.weight = 0;
        start = true;
    }

    public void setAsGoal(){
        setAsDefault();
        setBackground(Color.red);
        goal = true;
    }

    public void setAsWall(){
        setAsDefault();
        setBackground(Color.black);
        wall = true;
    }

    public void setAsSwamp(){
        setAsDefault();
        setBackground(new Color(113, 153, 44));
        this.weight = 5;
        swamp = true;
    }

    public void setAsDefault(){
        setBackground(Color.white);
        start = false;
        goal = false;
        wall = false;
        swamp = false;
        path = false;
    }

    public void setAsPath(){
        setBackground(Color.blue);
        path = true;
    }

    public void setAsSearched(){

        if (!this.start && !this.goal){
            if (this.swamp){
                setBackground(new Color(181, 245, 71));
            } else{
                setBackground(Color.orange);
            }
            this.setText("<html>G: "+this.gCost + "<br>H: "+ this.hCost + "<br>F: "+ this.fCost + "</html>");
        }
    }

    public void setAsCurrentRandomWalk(){
        if (!this.start && !this.goal){
            setBackground(new Color(255,0,255));
            this.setText("<html>G: "+this.gCost + "<br>H: "+ this.hCost + "<br>F: "+ this.fCost + "</html>");
        }
    }

    public void setShadeOfBlue(int normalisedVisitedCount){
        //int scale= (int) (Math.random() * 100);

        //int scale = visitedCount;
        setBackground(new Color(200-normalisedVisitedCount,200-normalisedVisitedCount,255));
    }

    public void deleteText(){
        this.setText("");
    }


    /*
    @Override
    public String toString() {
        return "Node{" +
                ", col=" + col +
                ", row=" + row +
                ", gCost=" + gCost +
                ", hCost=" + hCost +
                ", fCost=" + fCost +
                '}';
    }

     */

    @Override
    public String toString() {
        return "N: " + col + "," + row;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Objects.equals(ControlPanel.currentselection, "start")){
            setAsStart();
        }

        if (Objects.equals(ControlPanel.currentselection, "goal")){
            setAsGoal();
        }

        if (Objects.equals(ControlPanel.currentselection, "wall")){
            setAsWall();
        }

        if (Objects.equals(ControlPanel.currentselection, "swamp")){
            setAsSwamp();
        }

    }

    public int getVisitedCount() {
        return visitedCount;
    }


    @Override
    public int compareTo(Node o) {
        if(this.fCost > o.fCost){
            return 1;
        }else if(this.fCost < o.fCost){
            return -1;
        } else{
            return 0;
        }

    }
}
