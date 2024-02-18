package GUI;

import Buttons.FileButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PicGUI {
    private JPanel mainPanal;
    private JButton fileButton;
    private JButton resetButton;
    private JButton stepButton;
    private JButton runButton;
    private JButton stopButton;

    public PicGUI() {
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileButton.openFile();
            }
        });
    }


    public static JFrame frame = new JFrame("PIC16F48");
    public static void main(String[] args){

        frame.setContentPane(new PicGUI().mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
