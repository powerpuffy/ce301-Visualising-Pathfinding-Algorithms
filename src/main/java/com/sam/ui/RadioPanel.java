package main.java.com.sam.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioPanel extends JPanel {

    public RadioPanel(GridPanel gridPanel){

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
        c.weighty = 1;


        this.add(enableBorder,c);

        c.gridx = 1;
        c.gridy = 0;
        c.weighty = 1;
        this.add(disableBorder,c);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 1;
        this.add(costText,c);

        c.gridx = 1;
        c.gridy = 2;
        c.weighty = 1;
        this.add(positionText,c);

        c.gridx = 2;
        c.gridy = 2;
        c.weighty = 1;
        this.add(noText,c);




    }


}
