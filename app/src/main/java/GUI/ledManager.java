package GUI;

import Model.Pic;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class ledManager {

    @FXML
    private GridPane gridPane;

    private Image redLED = new Image("./png/red-led-off-hi.png");
    private Image greenLED = new Image("./png/green-led-off-hi.png");
    private guiManager gm;

    public void addGM(guiManager gm){
        this.gm = gm;
    }

    public void initialize() {
        addImage();
    }

    private ImageView[] iV = new ImageView[8];
    private void addImage() {
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 8; i++) {

            iV[i] = new ImageView(redLED);
            iV[i].setFitHeight(30);
            iV[i].setFitWidth(30);
            gridPane.setHalignment(iV[i], javafx.geometry.HPos.CENTER);
            gridPane.setValignment(iV[i], javafx.geometry.VPos.CENTER);

            gridPane.add(iV[i], i, 0);
        }
    }

    public void manageImage(){
        for (int i = 0; i < 8; i++) {
           String content = gm.getPinB(i).getText();

            if(content.equals("0")){
                iV[i].setImage(redLED);
            }else{
                iV[i].setImage(greenLED);
            }
        }
    }

}

