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
                RandomizedPrims newMaze;

                if (seedinput.getText().length() == 0){
                    newMaze = new RandomizedPrims(gridPanel.nodeArray,gridPanel.maxCol,gridPanel.maxRow);
                } else{
                    newMaze = new RandomizedPrims(gridPanel.nodeArray,gridPanel.maxCol,gridPanel.maxRow,Long.parseLong(seedinput.getText()));
                }


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

        this.add(seedinput);
        this.add(mazeButton);


    }

}
