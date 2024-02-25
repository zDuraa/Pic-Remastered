package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import utils.*;

public class controller {

    @FXML
    private TextArea textArea;

    public void initialize() {
        String all = "";

        for (String str : FileManager.ladeDatei(FileManager.getFile())) {
            all += str + "\n";
        }

        textArea.setText(all);
    }
}
