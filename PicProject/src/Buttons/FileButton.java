package Buttons;

import java.io.File;
import javax.swing.JFileChooser;


public class FileButton {

    private static String file = "";
    public static void openFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setName("fileJFch");
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            file = selectedFile.getAbsolutePath();
        }
    }

    public static void setFile(String file)
    {
        FileButton.file = file;
    }
    public static String getFile()
    {
        return file;
    }

}
