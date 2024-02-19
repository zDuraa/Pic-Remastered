package GUI;

import utils.FileManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PicGUI {
    public JPanel mainPanal;
    //test
    private JButton stopButton;
    private JButton stepButton;
    private JButton resetButton;
    private JButton fileButton;
    private JPanel displayPanal;
    private JButton runButton;
    public ArrayList<JTextArea> textAreaList = new ArrayList<>();
    public ArrayList<JCheckBox> checkBoxBreakList = new ArrayList<>();

    public PicGUI() {
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileManager.openFile();
                displayManager();
            }
        });
    }

    public static JFrame frame = new JFrame("PIC16F48");

    public static void main(String[] args) {

        frame.setContentPane(new PicGUI().mainPanal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void displayManager() {
        String code = FileManager.getFile();
        String[] codeArray = FileManager.ladeDatei(code).toArray(new String[0]);

        displayPanal.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (var line : codeArray) {
            // creat Components
            JPanel rowPanel = new JPanel();
            JTextArea textArea = new JTextArea(line);
            JCheckBox checkBox = new JCheckBox();

            // config Components
            textArea.setEditable(false);
            textArea.setBorder(new EmptyBorder(2, 0, 0, 0));
            checkBox.setBorder(new EmptyBorder(0, 0, 10, 10));
            checkBox.setBackground(Color.WHITE);

            // add Components
            rowPanel.setLayout(new BorderLayout());
            if (isCode(line)) {
                rowPanel.add(checkBox, BorderLayout.WEST);
            }
            rowPanel.add(textArea, BorderLayout.CENTER);
            panel.add(rowPanel);

            // save Components in list
            if (isCode(line)) {
                textAreaList.add(textArea);
                checkBoxBreakList.add(checkBox);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        displayPanal.add(scrollPane);
        displayPanal.revalidate();
        displayPanal.repaint();
    }

    public boolean isCode(String line) {
        boolean ret = false;
        char beginning = line.charAt(0);

        if (beginning != ' ') {
            ret = true;
        }

        return ret;
    }
}
