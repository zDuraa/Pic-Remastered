package TempName;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {

    public static ArrayList<Integer> code = new ArrayList<>();
    public static ArrayList<String> ladeDatei(String datName) {
        ArrayList<String> list = new ArrayList<String>();    //Ich habe hier erstens eine Array list erstellt namens commands
        File file = new File(datName);        //neues File erstellt
        BufferedReader br = null;       //liest Text aus Symbolen und puffert die Symbole, um Zeichen, Arrays und Strings effizient einzulesen
        String command;

        int i;

        try {
            br = new BufferedReader(new java.io.FileReader(datName));
            String line = null;
            while ((line = br.readLine()) != null) {

                list.add(line);                      //Jede einzelne Zeile die ausgegeben wird, wird in die Liste abgespeichert
                command = line.substring(5, 9);

                if (!command.equals("    ")) {
                    i = Integer.parseInt(command, 16);
                    code.add(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                }
        }
        return list;           //Die Liste wird dann am ende returned so das wir zugriff drauf haben, deswegen ist die Methode eine ArrayList und nicht mehr void
    }
}
