package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;

import javax.swing.text.StyledEditorKit;

import static utils.converter.intToHex;

public class guiManager {

    @FXML
    private Button buttonGo;

    @FXML
    private Button buttonIgnore;

    @FXML
    private Button buttonReset;

    @FXML
    private Button buttonStepIn;

    @FXML
    private Button buttonStepOut;

    @FXML
    private Button buttonStepOver;

    @FXML
    private Label labeINTCON;

    @FXML
    private Label labelC;

    @FXML
    private Label labelDC;

    @FXML
    private Label labelFSR;

    @FXML
    private Label labelGIE;

    @FXML
    private Label labelINTE;

    @FXML
    private Label labelINTF;

    @FXML
    private Label labelIRP;

    @FXML
    private Label labelIntEdg;

    @FXML
    private Label labelOption;

    @FXML
    private Label labelPC;

    @FXML
    private Label labelPCL;

    @FXML
    private Label labelPCLATH;

    @FXML
    private Label labelPD;

    @FXML
    private Label labelPIE;

    @FXML
    private Label labelPS0;

    @FXML
    private Label labelPS1;

    @FXML
    private Label labelPS2;

    @FXML
    private Label labelPSA;

    @FXML
    private Label labelRBIE;

    @FXML
    private Label labelRBIF;

    @FXML
    private Label labelRBP;

    @FXML
    private Label labelRP;

    @FXML
    private Label labelRP0;

    @FXML
    private Label labelStatus;

    @FXML
    private Label labelT0CS;

    @FXML
    private Label labelT0IE;

    @FXML
    private Label labelT0IF;

    @FXML
    private Label labelT0SE;

    @FXML
    private Label labelTO;

    @FXML
    private Label labelWReg;

    @FXML
    private Label labelZ;

    @FXML
    private ListView<?> listViewCode;

    @FXML
    private Pane paneINTCONRegister;

    @FXML
    private Pane paneOptionRegister;

    @FXML
    private Pane paneRA;

    @FXML
    private Pane paneRB;

    @FXML
    private Pane paneSFRSichtbar;

    @FXML
    private Pane paneSFRversteckt;

    @FXML
    private Pane paneStack;

    @FXML
    private Pane paneStatusRegister;

    @FXML
    private CheckBox radioWDT;

    @FXML
    private ScrollPane scrollPaneCode;

    @FXML
    private ScrollPane scrollPaneRam;

    @FXML
    void buttonGoOnClick(ActionEvent event) {

    }

    @FXML
    void buttonIgnoreOnClick(ActionEvent event) {

    }

    @FXML
    void buttonResetOnClick(ActionEvent event) {

    }

    @FXML
    void buttonStepInOnClick(ActionEvent event) {

    }

    @FXML
    void buttonStepOutOnClick(ActionEvent event) {

    }

    @FXML
    void buttonStepOverOnClick(ActionEvent event) {

    }

    TextField[] field = new TextField[256];

    public void initialize() {
        scrollPaneRam.setContent(CreateRamGrid());
    }


    private GridPane CreateRamGrid() {
        GridPane newGridPane = new GridPane();
        int column = 9;
        int row = 33;
        int i = 0;

        for(int x = 1; x < 9; x++){
            Label label = new Label();
            label.setText(intToHex(x-1));
            label.setAlignment(Pos.CENTER);
            label.setPrefWidth(30);
            label.setPrefHeight(30);
            newGridPane.add(label, x, 0);
        }
        for(int y = 1; y < row; y++){
            Label label = new Label();
            label.setText(intToHex((y-1)*8));
            label.setPrefWidth(30);
            label.setPrefHeight(30);
            label.setAlignment(Pos.CENTER);
            newGridPane.add(label, 0, y);

            for(int x = 1; x < column; x++){
                field[i] = new TextField();
                field[i].setMaxSize(30, 15);
                field[i].setAlignment(Pos.CENTER);
                newGridPane.add(field[i], x, y);
                i++;
            }
        }

        newGridPane.setAlignment(Pos.CENTER);
        return newGridPane;
    }

}
