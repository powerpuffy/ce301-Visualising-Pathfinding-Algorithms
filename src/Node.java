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
    public boolean solid;
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

    public void setAsSolid(){
        setBackground(Color.black);
        solid = true;
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
            setAsSolid();
        }

    }


}
