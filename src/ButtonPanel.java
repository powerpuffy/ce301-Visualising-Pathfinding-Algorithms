import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class ButtonPanel extends JPanel {

    public static int algoSpeed = 90;
    static String currentselection;

    public ButtonPanel(GridPanel gridPanel){

        JButton startNodeButton = new JButton("Start Node");
        startNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlPanel.currentselection = "start";
            }
        });

        JButton goalNodeButton = new JButton("Goal Node");
        goalNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlPanel.currentselection = "goal";
            }
        });

        JButton wallNodeButton = new JButton("Wall Node");
        wallNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlPanel.currentselection = "wall";
            }
        });

        JButton defaultNodeButton = new JButton("Clear Node");
        defaultNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlPanel.currentselection = "default";
            }
        });

        JButton swampNodeButton = new JButton("Swamp Node");
        swampNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlPanel.currentselection = "swamp";
            }
        });

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("A*");
        comboBox.addItem("Dijkstra");
        comboBox.addItem("BFS");
        comboBox.addItem("BFS - Bidirectional");
        comboBox.addItem("DFS");
        comboBox.addItem("DFS - Bidirectional");
        comboBox.addItem("Random Walk");

        JButton goButton = new JButton("GO");
        goButton.setPreferredSize(new Dimension(60,60));
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gridPanel.samSearch((String) comboBox.getSelectedItem());
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JRadioButton enableBorder = new JRadioButton("Enable borders",true);
        enableBorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.enableBorders();
            }
        });

        JRadioButton disableBorder = new JRadioButton("Disable borders");
        disableBorder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.disableBorders();
            }
        });

        ButtonGroup borderButtonGroup = new ButtonGroup();
        borderButtonGroup.add(enableBorder);
        borderButtonGroup.add(disableBorder);


        JRadioButton noText = new JRadioButton("No Text",true);
        noText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.disableText();
            }
        });

        JRadioButton costText = new JRadioButton("Display Costs");
        costText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.enableCostText();
            }
        });

        JRadioButton positionText = new JRadioButton("Display Coordinates");
        positionText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.enablePositionText();
            }
        });

        ButtonGroup textButtonGroup = new ButtonGroup();
        textButtonGroup.add(noText);
        textButtonGroup.add(costText);
        textButtonGroup.add(positionText);


        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty = 0.01;


        this.add(startNodeButton,c);

        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 0.01;
        this.add(goalNodeButton,c);

        c.gridx = 2;
        c.gridy = 0;
        c.weighty = 0.01;
        this.add(wallNodeButton,c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.01;
        this.add(defaultNodeButton,c);

        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 0.01;
        this.add(swampNodeButton,c);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.01;
        this.add(comboBox,c);

        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 1;
        this.add(goButton,c);


    }

}
