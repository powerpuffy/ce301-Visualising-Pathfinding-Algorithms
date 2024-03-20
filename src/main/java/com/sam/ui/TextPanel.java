package main.java.com.sam.ui;

import main.java.com.sam.data.PathfindingData;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {


    JTextArea ta;
    JLabel algoLabel;
    JLabel totalNodesLabel;
    JLabel nodesVisitedLabel;
    JLabel nodesToGoalLabel;
    JLabel elapsedTimeLabel;
    JLabel seedLabel;

    int fontSize = 18;

    public TextPanel(GridPanel gridPanel){

        this.setLayout(new GridLayout(0,1));

        this.setFont(new Font("Serif", Font.PLAIN, 28));

        this.add(algoLabel = new JLabel());
        this.add(totalNodesLabel= new JLabel());
        this.add(nodesVisitedLabel= new JLabel());
        this.add(nodesToGoalLabel= new JLabel());
        this.add(elapsedTimeLabel= new JLabel());
        this.add(seedLabel= new JLabel());
        //this.add(ta);
        setAlgoLabel("a*");

    }

    public void setPanelData(PathfindingData data){
        setAlgoLabel(data.getAlgorithm());
        setTotalNodesLabel(String.valueOf(data.getTotalNumOfNodes()));
        setNodesVisitedLabel(String.valueOf(data.getNumOfNodesVisited()));
        setNodesToGoalLabel(String.valueOf(data.getNumOfNodesToGoal()));
        setElapsedTimeLabel(String.valueOf(data.getElapsedTime()));
        setSeedLabel(String.valueOf(data.getSeed()));
    }

    public void setAlgoLabel(String s){
        this.algoLabel.setText("Algorithm: "+ s);
        algoLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));

    }

    public void setTotalNodesLabel(String s){
        this.totalNodesLabel.setText("Total Nodes: " + s);
        totalNodesLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
    }

    public void setNodesVisitedLabel(String s){
        this.nodesVisitedLabel.setText("Nodes Visited: " + s);
        nodesVisitedLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
    }

    public void setNodesToGoalLabel(String s){
        this.nodesToGoalLabel.setText("Nodes to Goal: " + s);
        nodesToGoalLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
    }

    public void setElapsedTimeLabel(String s){
        this.elapsedTimeLabel.setText("Elapsed Time: " + s);
        elapsedTimeLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
    }

    public void setSeedLabel(String s){
        this.seedLabel.setText("Seed: " + s);
        seedLabel.setFont(new Font("Dialog", Font.BOLD, fontSize));
    }


}
