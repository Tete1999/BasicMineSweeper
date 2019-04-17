import javax.swing.*;
import java.awt.*;

public class Main {



    public static void main(String[] args) {
        JFrame frame = new JFrame("Mine Sweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        MSPanel ms = new MSPanel(4,4, 0.125);
        frame.setSize(new Dimension(1000, 1000));
        frame.add(ms);
        frame.getContentPane().add(ms);
        frame.pack();
        frame.setVisible(true);

    }
}
