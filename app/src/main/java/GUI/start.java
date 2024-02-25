package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class start {

    @FXML
    private Button buttonOpenFile;

    @FXML
    void buttonOpenFileClick(ActionEvent event) {
        System.out.println("select file");
        FileManager.openFile();

        Stage secondStage = new Stage();
        try {
            Parent root = FXMLLoader.load(start.class.getResource("./../views/main.fxml"));
            Scene scene = new Scene(root);
            secondStage.setScene(scene);

        } catch (Exception e) {
            System.out.println("loading the file went wrong\n" + e);
        }
        secondStage.show();

        Stage currentStage = (Stage) buttonOpenFile.getScene().getWindow();
        currentStage.close();
    }

}
