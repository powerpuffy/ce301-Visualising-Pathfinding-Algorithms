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
    public boolean start;
    public boolean goal;
    public boolean wall;
    public boolean path;
    public boolean open;
    public boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
        this.weight = 1;
        this.setFocusable(false);

        setBackground(Color.white);
        setForeground(Color.black);

        addActionListener(this);
    }

    public void setAsStart(){
        setBackground(Color.green);
        start = true;
    }

    public void setAsGoal(){
        setBackground(Color.red);
        goal = true;
    }

    public void setAsWall(){
        setBackground(Color.black);
        wall = true;
    }

    public void setAsDefault(){
        setBackground(Color.white);
        wall = false;
        path = false;
    }

    public void setAsPath(){
        setBackground(Color.blue);
        path = true;
    }

    public void setAsSearched(){
        if (!this.start && !this.goal){
            setBackground(Color.orange);
            System.out.println("plz repaint");

        }
    }



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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!start && Objects.equals(ControlPanel.currentselection, "start")){
            setAsStart();
        }

        if (!goal && Objects.equals(ControlPanel.currentselection, "goal")){
            setAsGoal();
        }

        if (!this.start && !this.goal && Objects.equals(ControlPanel.currentselection, "wall")){
            setAsWall();
        }

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
