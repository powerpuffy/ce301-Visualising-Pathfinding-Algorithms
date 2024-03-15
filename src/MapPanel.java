import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class MapPanel extends JPanel {

    GridPanel gridPanel;

    public MapPanel(GridPanel gridPanel){

        this.gridPanel = gridPanel;

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

        this.setLayout(new GridLayout(2,2));

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

        //setting to maxRow... assuming it will always be a square
        int count = 0;
        int[][] ar = new int[gridPanel.maxCol][gridPanel.maxRow];
        for (int i = 0; i < gridPanel.maxCol; i++){
            for (int j = 0; j < gridPanel.maxRow; j++){
                ar[i][j] = Integer.parseInt(String.valueOf(s.charAt(count)));
                count++;
            }
        }
        System.out.println(Arrays.deepToString(ar));
        return ar;
    }

}
