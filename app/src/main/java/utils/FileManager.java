package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class FileManager {
    private static String file = "";

    public static void openFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Datei auswählen");
        File selectedFile = fileChooser.showOpenDialog(stage);
        file = selectedFile.toPath().toString();
    }

    public static void setFile(String file) {
        FileManager.file = file;
    }

    public static String getFile() {
        return file;
    }

    public static ArrayList<Integer> code = new ArrayList<>();
    private static ArrayList<String> commands = new ArrayList<>();

    public static ArrayList<String> getCommands() {
        return (ArrayList<String>) commands.clone();
    }

    public static ArrayList<String> ladeDatei(String datName) {
        commands = new ArrayList<>();
        ArrayList<String> list = new ArrayList<String>(); // Ich habe hier erstens eine Array list erstellt namens
                                                          // commands
        BufferedReader br = null; // liest Text aus Symbolen und puffert die Symbole, um Zeichen, Arrays und
                                  // Strings effizient einzulesen
        String command;

        int i;
        try {
            br = new BufferedReader(new java.io.FileReader(datName));
            String line = null;
            while ((line = br.readLine()) != null) {

                list.add(line); // Jede einzelne Zeile die ausgegeben wird, wird in die Liste abgespeichert
                command = line.substring(5, 9);

                if (!command.equals("    ")) {
                    i = Integer.parseInt(command, 16);
                    code.add(i);
                    commands.add(command);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
        }
        return list; // Die Liste wird dann am ende returned so das wir zugriff drauf haben, deswegen
                     // ist die Methode eine ArrayList und nicht mehr void
    }
}
