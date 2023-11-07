import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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

        JButton swampNodeButton = new JButton("Swamp Node");
        swampNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentselection = "swamp";
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


        this.add(t);
        this.add(saveButton);
        this.add(retrieveButton);
        this.add(deleteButton);


    }

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

        int count = 0;
        int[][] ar = new int[25][25];
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
                ar[i][j] = Integer.parseInt(String.valueOf(s.charAt(count)));
                count++;
            }
        }
        System.out.println(Arrays.deepToString(ar));
        return ar;
    }

}
