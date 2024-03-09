package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class FileManager {
    private static String filePath = "";
    public static ArrayList<Integer> codeInt = new ArrayList<>();
    private static ArrayList<String> commandsString = new ArrayList<>();
    private static ArrayList<String> textList = new ArrayList<>();

    public static void openFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("FileChooser");
        File selectedFile = fileChooser.showOpenDialog(stage);
        filePath = selectedFile.toPath().toString();
        ladeDatei();
    }

    public static void setFilePath(String file) {
        FileManager.filePath = file;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static ArrayList<String> getCommands() {
        return (ArrayList<String>) commandsString.clone();
    }

    public static void ladeDatei() {
        commandsString = new ArrayList<>();
        BufferedReader br = null; // liest Text aus Symbolen und puffert die Symbole, um Zeichen, Arrays und
                                  // Strings effizient einzulesen
        String command;

        int i;
        try {
            br = new BufferedReader(new java.io.FileReader(filePath));
            String line = null;
            int before = -1;
            while ((line = br.readLine()) != null) {

                textList.add(line); // Jede einzelne Zeile die ausgegeben wird, wird in die Liste abgespeichert
                command = line.substring(5, 9);

                if (!command.equals("    ")) {
                    int lineNumber = Integer.parseInt(line.substring(0, 4), 16);
                    // if line numbers get skiped
                    if (lineNumber - 1 != before) {
                        fillNops(commandsString, lineNumber - before - 1);
                    }

                    i = Integer.parseInt(command, 16);
                    codeInt.add(i);
                    commandsString.add(command);
                    before = lineNumber;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static ArrayList<String> getText() {
        return textList;
    }

    public static boolean checkForCode(String line) {
        Boolean ret = false;
        if (line.charAt(6) != ' ') {
            ret = true;
        }
        return ret;
    }

    private static void fillNops(ArrayList<String> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add("0000");
        }
    }

}
