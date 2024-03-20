package main.java.com.sam.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class SliderPanel extends JPanel {


    public SliderPanel(GridPanel gridPanel){

        this.setBackground(Color.LIGHT_GRAY);
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
                ControlPanel.algoSpeed = 101 - source.getValue();

            }
        });

        JButton resetButton = new JButton("Reset Grid");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridPanel.wipeGrid();
            }
        });

        this.add(speedSlider);
        this.add(resetButton);

    }
}
