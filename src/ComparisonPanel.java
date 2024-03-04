import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComparisonPanel extends JPanel {

    public static int algoSpeed = 90;
    static String currentselection;
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

        this.setPreferredSize(new Dimension(400,screenHeight));

        JComboBox<String> comaprisonBox1 = new JComboBox<>();
        comaprisonBox1.addItem("A*");
        comaprisonBox1.addItem("Dijkstra");
        comaprisonBox1.addItem("BFS");
        comaprisonBox1.addItem("BFS - Bidirectional");
        comaprisonBox1.addItem("DFS");
        comaprisonBox1.addItem("DFS - Bidirectional");
        comaprisonBox1.addItem("Random Walk");

        JComboBox<String> comaprisonBox2 = new JComboBox<>();
        comaprisonBox2.addItem("A*");
        comaprisonBox2.addItem("Dijkstra");
        comaprisonBox2.addItem("BFS");
        comaprisonBox2.addItem("BFS - Bidirectional");
        comaprisonBox2.addItem("DFS");
        comaprisonBox2.addItem("DFS - Bidirectional");
        comaprisonBox2.addItem("Random Walk");

        JTextField t2 = new JTextField(16);

        JButton startComparisonButton = new JButton("Start Comparison");
        startComparisonButton.setPreferredSize(new Dimension(150,60));

        startComparisonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfRuns = 1;
                    if (!(t2.getText().length() == 0)){
                        numberOfRuns = Integer.parseInt(t2.getText());
                    }

                    gridPanel.samSearchComparison((String) comaprisonBox1.getSelectedItem(),(String) comaprisonBox2.getSelectedItem(), numberOfRuns);
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
