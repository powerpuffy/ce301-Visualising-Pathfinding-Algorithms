package main.java.com.sam.application;

import main.java.com.sam.ui.ControlPanel;
import main.java.com.sam.ui.GridPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setTitle("Pathfinding Visualiser");
        ImageIcon img = new ImageIcon("res/maze.PNG");
        window.setIconImage(img.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        GridPanel gp = new GridPanel();
        window.add(gp, BorderLayout.CENTER);

        ControlPanel cp = new ControlPanel(gp);
        window.add(cp, BorderLayout.EAST);

        gp.setTextPanelReference(cp.tp);
        //window.add(new Panels.ComparisonPanel(gp), BorderLayout.WEST);
        window.setFocusable(false);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }




}
