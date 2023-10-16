import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel  extends JPanel {

    static String currentselection;
    final int maxCol = 25;

    final int maxRow = 25;
    final int nodeSize = 30;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    public ControlPanel(GridPanel gridPanel){
        this.setPreferredSize(new Dimension(400,screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(false);

        JButton startNodeButton = new JButton("Start Node");
        startNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "start";
            }
        });

        JButton goalNodeButton = new JButton("Goal Node");
        goalNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "goal";
            }
        });

        JButton wallNodeButton = new JButton("Wall Node");
        wallNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "wall";
            }
        });

        JButton goButton = new JButton("GO");
        goButton.setPreferredSize(new Dimension(60,60));
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gridPanel.samSearch();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.add(startNodeButton);
        this.add(goalNodeButton);
        this.add(wallNodeButton);
        this.add(goButton);

    }

}
