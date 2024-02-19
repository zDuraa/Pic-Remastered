import GUI.*;
import javax.swing.*;

public class Main {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("PIC16F8");
        frame.setContentPane(new PicGUI().mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
