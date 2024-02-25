package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class controller {

    @FXML
    private Button button;

    @FXML
    private ProgressBar progressBar;

    @FXML
    void button_click(ActionEvent event) {
        progressBar.setProgress(progressBar.getProgress() + 0.1);

    }

    public void initialize() {
    }

}
