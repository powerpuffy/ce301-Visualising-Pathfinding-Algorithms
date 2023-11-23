import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setLayout(new BorderLayout());

        GridPanel gp = new GridPanel();
        window.add(gp, BorderLayout.CENTER);
        window.add(new ControlPanel(gp), BorderLayout.EAST);
        window.setFocusable(false);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);


    }




}
