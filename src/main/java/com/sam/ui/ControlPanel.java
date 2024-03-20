package main.java.com.sam.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ControlPanel extends JPanel {

    public static int algoSpeed = 90;
    static String currentselection;
    int maxCol;

    int maxRow;
    int nodeSize;
    int screenWidth;
    int screenHeight;

    public TextPanel tp;

    public static String getCurrentselection() {
        return currentselection;
    }

    public ControlPanel(GridPanel gridPanel){
        this.maxCol = gridPanel.maxCol;
        this.maxRow = gridPanel.maxRow;
        this.nodeSize = gridPanel.nodeSize;
        this.screenWidth = gridPanel.screenWidth;
        this.screenHeight = gridPanel.screenHeight;



        this.setPreferredSize(new Dimension(400,screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(false);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.add(new ButtonPanel(gridPanel));

        this.add(new SliderPanel(gridPanel));

        this.add(new RadioPanel(gridPanel));

        this.add(new MazePanel(gridPanel));

        this.add(new MapPanel(gridPanel));

        this.add(new ComparisonPanel(gridPanel));

        this.add(this.tp = new TextPanel(gridPanel));



    }

    /*
    public Panels.ControlPanel(Panels.GridPanel gridPanel){
        this.maxCol = gridPanel.maxCol;
        this.maxRow = gridPanel.maxRow;
        this.nodeSize = gridPanel.nodeSize;
        this.screenWidth = gridPanel.screenWidth;
        this.screenHeight = gridPanel.screenHeight;

        this.setPreferredSize(new Dimension(400,screenHeight));
        this.setBackground(Color.LIGHT_GRAY);
        this.setFocusable(false);

        JButton startNodeButton = new JButton("Start main.java.com.sam.util.Node");
        startNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "start";
            }
        });

        JButton goalNodeButton = new JButton("Goal main.java.com.sam.util.Node");
        goalNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "goal";
            }
        });

        JButton wallNodeButton = new JButton("Wall main.java.com.sam.util.Node");
        wallNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "wall";
            }
        });

        JButton defaultNodeButton = new JButton("Clear main.java.com.sam.util.Node");
        defaultNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "default";
            }
        });

        JButton swampNodeButton = new JButton("Swamp main.java.com.sam.util.Node");
        swampNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "swamp";
            }
        });

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("A*");
        comboBox.addItem("main.java.sam.algorithms.Dijkstra");
        comboBox.addItem("main.java.sam.algorithms.BFS");
        comboBox.addItem("main.java.sam.algorithms.BFS - Bidirectional");
        comboBox.addItem("main.java.sam.algorithms.DFS");
        comboBox.addItem("main.java.sam.algorithms.DFS - Bidirectional");
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


        this.add(startNodeButton);
        this.add(goalNodeButton);
        this.add(wallNodeButton);
        this.add(defaultNodeButton);
        this.add(swampNodeButton);
        this.add(goButton);

        JTextField t = new JTextField(16);

        JButton saveButton = new JButton("Save Map");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t.getText() != null){
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter("saves.txt",true));

                        out.write("\n" + t.getText() + Arrays.deepToString(gridPanel.getMap()));
                        out.close();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        JButton retrieveButton = new JButton("Retrieve Map");
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t.getText() != null){
                    try {
                        // pass the path to the file as a parameter
                        File file = new File("saves.txt");
                        Scanner sc = new Scanner(file);

                        while (sc.hasNextLine()){
                            String nl = sc.nextLine();
                            if (Objects.equals(getID(nl), t.getText())){
                                gridPanel.wipeGrid();
                                gridPanel.fillMap(getMapArray(nl));
                            }
                        }



                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        JButton deleteButton = new JButton("Delete Map");



        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,
                1, 100, 10);

        //Create the label table
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put( 100 , new JLabel("Fast") );
        labelTable.put( 1, new JLabel("Slow") );
        speedSlider.setLabelTable( labelTable );
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                algoSpeed = 101 - source.getValue();
            }
        });

        JButton resetButton = new JButton("Reset Grid");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.wipeGrid();
            }
        });

        JButton mazeButton = new JButton("Generate Maze");
        mazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.java.sam.algorithms.RandomizedPrims newMaze = new main.java.sam.algorithms.RandomizedPrims(gridPanel.nodeArray,maxCol,maxRow);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            gridPanel.resetButtonText();
                            newMaze.generateMaze();

                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }).start();

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



        this.add(t);
        this.add(saveButton);
        this.add(retrieveButton);
        this.add(deleteButton);


        this.add(speedSlider);
        this.add(resetButton);

        this.add(mazeButton);

        this.add(enableBorder);
        this.add(disableBorder);

        this.add(comboBox);

        this.add(noText);
        this.add(costText);
        this.add(positionText);

        this.add(new Panels.ComparisonPanel(gridPanel));
    }

     */




    public String getID(String s){
        char[] ch = s.toCharArray();
        StringBuilder id= new StringBuilder();
        for (char c: ch){
            if (c == '['){
                break;
            } else{
                id.append(c);
            }
        }
        System.out.println("id :" + id.toString());
        return id.toString();
    }

    public int[][] getMapArray(String s){
        StringBuilder sb = new StringBuilder(s);
        String len = getID(s);
        int length = len.length();
        System.out.println(length);
        System.out.println(sb);
        for (int i = 0; i < length; i++){
            sb.deleteCharAt(0);
        }
        System.out.println(sb);



        s = sb.toString();
        s = s.replaceAll("[^\\d.]", "");
        System.out.println(s);

        //setting to maxRow... assuming it will always be a square
        int count = 0;
        int[][] ar = new int[maxRow][maxRow];
        for (int i = 0; i < maxRow; i++){
            for (int j = 0; j < maxRow; j++){
                ar[i][j] = Integer.parseInt(String.valueOf(s.charAt(count)));
                count++;
            }
        }
        System.out.println(Arrays.deepToString(ar));
        return ar;
    }

}
