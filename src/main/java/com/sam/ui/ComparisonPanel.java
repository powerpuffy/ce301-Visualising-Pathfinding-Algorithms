package main.java.com.sam.ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ComparisonPanel extends JPanel {

    public static int algoSpeed = 90;

    int maxCol;

    int maxRow;
    int nodeSize;
    int screenWidth;
    int screenHeight;

    public ComparisonPanel(GridPanel gridPanel){

        this.maxCol = gridPanel.maxCol;
        this.maxRow = gridPanel.maxRow;
        this.nodeSize = gridPanel.nodeSize;
        this.screenWidth = gridPanel.screenWidth;
        this.screenHeight = gridPanel.screenHeight;

        this.setPreferredSize(new Dimension(400,80));

        JComboBox<String> comaprisonBox1 = new JComboBox<>();
        comaprisonBox1.addItem("A* - Manhattan");
        comaprisonBox1.addItem("A* - Euclidean");
        comaprisonBox1.addItem("Dijkstra");
        comaprisonBox1.addItem("BFS");
        comaprisonBox1.addItem("BFS - Bidirectional");
        comaprisonBox1.addItem("DFS");
        comaprisonBox1.addItem("DFS - Bidirectional");
        comaprisonBox1.addItem("Random Walk");

        JComboBox<String> comaprisonBox2 = new JComboBox<>();
        comaprisonBox2.addItem("A* - Manhattan");
        comaprisonBox2.addItem("A* - Euclidean");
        comaprisonBox2.addItem("Dijkstra");
        comaprisonBox2.addItem("BFS");
        comaprisonBox2.addItem("BFS - Bidirectional");
        comaprisonBox2.addItem("DFS");
        comaprisonBox2.addItem("DFS - Bidirectional");
        comaprisonBox2.addItem("Random Walk");

        JTextField t2 = new JTextField(16);

        JButton startComparisonButton = new JButton("Start Comparison");
        startComparisonButton.setPreferredSize(new Dimension(150,30));

        startComparisonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfRuns = 1;
                    if (!(t2.getText().length() == 0)){
                        numberOfRuns = Integer.parseInt(t2.getText());
                    }

                    File file = null;
                    JFileChooser fileChooser = new JFileChooser();

                    // restrict the user to select files of all types
                    fileChooser.setAcceptAllFileFilterUsed(false);



                    // only allow files of .txt extension
                    FileNameExtensionFilter restrict = new FileNameExtensionFilter("CSV File", "csv");
                    fileChooser.addChoosableFileFilter(restrict);

                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        file = new File(fileChooser.getSelectedFile() + ".csv");

                    }

                    gridPanel.samSearchComparison((String) comaprisonBox1.getSelectedItem(),(String) comaprisonBox2.getSelectedItem(), numberOfRuns, file);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.add(comaprisonBox1);
        this.add(comaprisonBox2);

        this.add(t2);

        this.add(startComparisonButton);



    }

}
