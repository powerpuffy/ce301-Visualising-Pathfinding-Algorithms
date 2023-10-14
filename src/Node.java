import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {

    public Node parent;
    public int col;
    public int row;
    public int gCost;
    public int hCost;
    public int fCost;
    public boolean start;
    public boolean goal;
    public boolean wall;
    public boolean path;
    public boolean open;
    public boolean checked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
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

        if (!this.start && !this.goal){
            setAsWall();
        }

    }


}
