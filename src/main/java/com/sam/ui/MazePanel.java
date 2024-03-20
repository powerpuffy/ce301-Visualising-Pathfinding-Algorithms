package main.java.com.sam.ui;

import main.java.com.sam.algorithms.RandomizedPrims;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazePanel extends JPanel {

    public MazePanel(GridPanel gridPanel){
        JTextField seedinput = new JTextField(16);

        JButton mazeButton = new JButton("Generate Maze");
        mazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomizedPrims newMaze = null;

                try {
                    if (seedinput.getText().length() == 0){
                        newMaze = new RandomizedPrims(gridPanel.nodeArray,gridPanel.maxCol,gridPanel.maxRow);
                    } else if (Long.parseLong(seedinput.getText()) < 9223372036854775807L){
                        newMaze = new RandomizedPrims(gridPanel.nodeArray,gridPanel.maxCol,gridPanel.maxRow,Long.parseLong(seedinput.getText()));
                    }
                } catch (Exception ex){
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Seed is invalid");
                    return;
                }



                RandomizedPrims finalNewMaze = newMaze;
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            gridPanel.resetButtonText();
                            assert finalNewMaze != null;
                            finalNewMaze.generateMaze();

                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }).start();

            }
        });

        this.add(seedinput);
        this.add(mazeButton);


    }

}
