import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Node extends JButton implements ActionListener, Comparable<Node>  {

    public Node parent;

    public Node secondParent;

    public Node frontierParent;
    public int col;
    public int row;
    public int gCost;
    public int hCost;
    public int fCost;

    public int weight;

    public double probabilityWeight;

    public int visitedCount;
    public boolean isStart;
    public boolean isGoal;

    public boolean isWall;

    public boolean isDefault;

    public boolean isSwamp;
    public boolean isPath;

    public boolean isSearched;

    public boolean isCostTextEnabled;

    public boolean isPositionTextEnabled;
    public boolean isOpen;
    public boolean isChecked;

    public Node(int col, int row){
        this.col = col;
        this.row = row;
        this.weight = 1;
        this.probabilityWeight = 2;
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
        isStart = true;
    }

    public void setAsGoal(){
        setAsDefault();
        setBackground(Color.red);
        isGoal = true;
    }

    public void setAsWall(){
        setAsDefault();
        setBackground(Color.black);
        isWall = true;
    }

    public void setAsSwamp(){
        setAsDefault();
        setBackground(new Color(113, 153, 44));
        this.weight = 5;
        isSwamp = true;
    }

    public void setAsDefault(){
        setBackground(Color.white);
        isDefault = true;
        isStart = false;
        isGoal = false;
        isWall = false;
        isSwamp = false;
        isPath = false;
        isSearched = false;
    }

    public void setAsPath(){
        setBackground(Color.blue);
        isPath = true;
    }

    public void setAsIntersectedPath(){
        setBackground(new Color(122, 214, 247));
        isPath = true;
    }

    public void setAsSearched(){

        this.isSearched = true;
        if (!this.isStart && !this.isGoal){
            if (this.isSwamp){
                setBackground(new Color(181, 245, 71));
            } else{
                setBackground(Color.orange);
            }

            if (isCostTextEnabled){
                this.setText("<html>G: "+this.gCost + "<br>H: "+ this.hCost + "<br>F: "+ this.fCost + "</html>");
            }

            if (isPositionTextEnabled){
                if (this.secondParent != null){
                    this.setText("<html>" + this.col +" "+ this.row + "<br>" + this.parent.col +" "+ this.parent.row + "  " + this.secondParent.col +" "+ this.secondParent.row + "</html>");
                }
                else{
                    this.setText("<html>" + this.col +" "+ this.row + "<br>" + this.parent.col +" "+ this.parent.row + "</html>");
                }
            }

        }
    }

    public void setAsSearched2(){

        if (!this.isStart && !this.isGoal){
            if (this.isSwamp){
                setBackground(new Color(181, 245, 71));
            } else{
                setBackground(Color.pink);
            }
            //this.setText("<html>G: "+this.gCost + "<br>H: "+ this.hCost + "<br>F: "+ this.fCost + "</html>");
            if (this.secondParent != null){
                this.setText("<html>" + this.col +" "+ this.row + "<br>" + this.parent.col +" "+ this.parent.row + "  " + this.secondParent.col +" "+ this.secondParent.row + "</html>");
            }
            else{
                this.setText("<html>" + this.col +" "+ this.row + "<br>" + this.parent.col +" "+ this.parent.row + "</html>");
            }
        }
    }

    public void disableText(){
        isCostTextEnabled = false;
        isPositionTextEnabled = false;
        this.deleteText();
    }

    public void enableCostText(){
        isPositionTextEnabled = false;
        isCostTextEnabled = true;
        if (isSearched && !this.isStart && !this.isGoal){
            this.setText("<html>G: "+this.gCost + "<br>H: "+ this.hCost + "<br>F: "+ this.fCost + "</html>");
        }
    }

    public void enablePositionText(){
        isCostTextEnabled = false;
        isPositionTextEnabled = true;
        if (isSearched && !this.isStart && !this.isGoal){
            if (this.secondParent != null){
                this.setText("<html>" + this.col +" "+ this.row + "<br>" + this.parent.col +" "+ this.parent.row + "  " + this.secondParent.col +" "+ this.secondParent.row + "</html>");
            }
            else{
                this.setText("<html>" + this.col +" "+ this.row + "<br>" + this.parent.col +" "+ this.parent.row + "</html>");
            }
        }
    }



    public void setAsCurrentRandomWalk(){
        if (!this.isStart && !this.isGoal){
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
