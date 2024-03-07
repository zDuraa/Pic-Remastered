package GUI;

import Model.Pic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.text.*;

import java.util.ArrayList;
import static utils.converter.intToHex;
import utils.FileManager;

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
    private Label labelStackpointer;

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
    private Label labelRP1;

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
    private Label labelWDT;

    @FXML
    private Label labelWReg;

    @FXML
    private Label labelZ;

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
    private GridPane paneControlls1;

    @FXML
    private GridPane paneControlls2;
    @FXML
    private Pane paneStatusRegister;

    @FXML
    private CheckBox radioWDT;

    @FXML
    private ScrollPane scrollPaneCode;

    @FXML
    private ScrollPane scrollPaneRam;
    private boolean controle = false;

    @FXML
    void buttonGoOnClick(ActionEvent event) {

        if (controle) {
            controle = false;
            buttonGo.setText("Go");
            return;
        } else {
            controle = true;
            buttonGo.setText("Is Running");
        }

        new Thread(() -> {
            while (controle) {
                if (debugPoints.get(pic.pCounter.get()).isSelected() == true) {
                    break;
                } else {
                    pic.next();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Platform.runLater(() -> {
                        updateGUI();
                    });
                }
            }
        }).start();
    }

    @FXML
    void buttonIgnoreOnClick(ActionEvent event) {

    }

    @FXML
    void buttonResetOnClick(ActionEvent event) {
        pic = new Pic(FileManager.getCommands());
        radioWDT.setSelected(false);
        updateGUI();
    }

    @FXML
    void buttonStepInOnClick(ActionEvent event) {
        pic.next();
        updateGUI();
    }

    @FXML
    void buttonStepOutOnClick(ActionEvent event) {

    }

    @FXML
    void buttonStepOverOnClick(ActionEvent event) {

    }

    @FXML
    void clickWDT(ActionEvent event) {
        if (radioWDT.isSelected()) {
            pic.watchdog.start();
        } else {
            pic.watchdog.stop();
        }
    }

    TextField[] field = new TextField[256];
    TextField[] fieldRATris = new TextField[8];
    TextField[] fieldRAPin = new TextField[8];
    TextField[] fieldRBTris = new TextField[8];
    TextField[] fieldRBPin = new TextField[8];

    Label[] labelStack = new Label[8];

    ArrayList<CheckBox> debugPoints = new ArrayList<>();
    ArrayList<GridPane> codePanes = new ArrayList<>();

    ArrayList<Integer> lookupTable = new ArrayList<>();

    Pic pic;

    public void initialize() {
        pic = new Pic(FileManager.getCommands());
        scrollPaneRam.setContent(CreateRamGrid());
        createRGrids();
        CreateStack();
        addCode();
        updateGUI();
    }

    private GridPane CreateRamGrid() {
        GridPane newGridPane = new GridPane();
        int column = 9;
        int row = 33;
        int i = 0;

        for (int x = 1; x < 9; x++) {
            Label label = new Label();
            label.setText(intToHex(x - 1));
            label.setAlignment(Pos.CENTER);
            label.setPrefWidth(30);
            label.setPrefHeight(30);
            label.getStyleClass().add("textSty");
            newGridPane.add(label, x, 0);
        }
        for (int y = 1; y < row; y++) {
            Label label = new Label();
            label.setText(intToHex((y - 1) * 8));
            label.setPrefWidth(30);
            label.setPrefHeight(30);
            label.setAlignment(Pos.CENTER);
            label.getStyleClass().add("textSty");
            newGridPane.add(label, 0, y);

            for (int x = 1; x < column; x++) {
                field[i] = new TextField();
                field[i].setMaxSize(30, 15);
                field[i].setAlignment(Pos.CENTER);
                field[i].getStyleClass().add("TFSty");
                newGridPane.add(field[i], x, y);
                i++;
            }
        }

        newGridPane.setAlignment(Pos.CENTER);
        return newGridPane;
    }

    private void createRGrids() {
        CreateRAGrid();
        CreateRBGrid();
    }

    private void CreateRAGrid() {
        int resize = 10;
        Label label = new Label();
        label.setText("Tris");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(9);
        label.setLayoutY(23);
        label.getStyleClass().add("textSty");
        paneRA.getChildren().add(label);

        int x;
        for (x = 0; x < 8; x++) {
            fieldRATris[x] = new TextField();
            fieldRATris[x].setPrefSize(28, 15);
            fieldRATris[x].setLayoutX(30 + resize);
            fieldRATris[x].setLayoutY(23);
            fieldRATris[x].setAlignment(Pos.CENTER);
            fieldRATris[x].getStyleClass().add("TFSty");
            paneRA.getChildren().add(fieldRATris[x]);
            resize += 27;
        }
        Label label1 = new Label();
        label1.setText("Pin");
        label1.setAlignment(Pos.CENTER);
        label1.setLayoutX(9);
        label1.setLayoutY(40);
        label1.getStyleClass().add("textSty");
        paneRA.getChildren().add(label1);
        resize = 0;
        for (x = 0; x < 8; x++) {
            fieldRAPin[x] = new TextField();
            fieldRAPin[x].setPrefSize(28, 15);
            fieldRAPin[x].setLayoutX(40 + resize);
            fieldRAPin[x].setLayoutY(50);
            fieldRAPin[x].setEditable(false);
            fieldRAPin[x].setAlignment(Pos.CENTER);
            fieldRAPin[x].getStyleClass().add("TFSty");
            paneRA.getChildren().add(fieldRAPin[x]);
            resize += 27;
        }
        manageRaPins();
    }


    private void manageRaPins()
    {
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            fieldRAPin[i].setOnMouseClicked(e -> {
                if(fieldRAPin[finalI].getText() == "0")
                {
                    pic.ram.addToReg(5,(0b1 << finalI));
                    System.out.println("set1" + finalI);
                    updateGUI();

                }else{
                    pic.ram.setReg(5,0);
                    System.out.println("set0" + finalI);
                    updateGUI();
                }
            });

        }
    }

    private void CreateRBGrid() {
        int resize = 10;
        Label label = new Label();
        label.setText("Tris");
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(9);
        label.setLayoutY(23);
        label.getStyleClass().add("textSty");
        paneRB.getChildren().add(label);

        int x;
        for (x = 0; x < 8; x++) {
            fieldRBTris[x] = new TextField();
            fieldRBTris[x].setPrefSize(28, 15);
            fieldRBTris[x].setLayoutX(30 + resize);
            fieldRBTris[x].setLayoutY(23);
            fieldRBTris[x].setAlignment(Pos.CENTER);
            fieldRBTris[x].getStyleClass().add("TFSty");
            paneRB.getChildren().add(fieldRBTris[x]);
            resize += 27;
        }
        Label label1 = new Label();
        label1.setText("Pin");
        label1.setAlignment(Pos.CENTER);
        label1.setLayoutX(9);
        label1.setLayoutY(40);
        label1.getStyleClass().add("textSty");
        paneRB.getChildren().add(label1);
        resize = 0;
        for (x = 0; x < 8; x++) {
            fieldRBPin[x] = new TextField();
            fieldRBPin[x].setPrefSize(28, 15);
            fieldRBPin[x].setLayoutX(40 + resize);
            fieldRBPin[x].setLayoutY(50);
            fieldRBPin[x].setAlignment(Pos.CENTER);
            fieldRBPin[x].getStyleClass().add("TFSty");
            paneRB.getChildren().add(fieldRBPin[x]);
            resize += 27;
        }
    }

    private void CreateStack() {
        int resize = 0;
        Label labelTitle = new Label();
        labelTitle.setText("Stack");
        labelTitle.setLayoutY(50);
        labelTitle.setLayoutX(28);
        labelTitle.getStyleClass().add("textSty");
        paneStack.getChildren().add(labelTitle);
        for (int i = 0; i < 8; i++) {
            Label label = new Label();
            label.setLayoutY(70 + resize);
            label.setLayoutX(28);
            label.setText("0000");
            label.getStyleClass().add("textSty");
            labelStack[i] = label;

            resize += 12;
            paneStack.getChildren().add(labelStack[i]);
        }
    }

    private void addCode() {
        ArrayList<String> file = FileManager.getText();
        GridPane bigGrid = new GridPane();
        int x = 0;
        for (String line : file) {
            GridPane gPane = new GridPane();
            gPane.getStyleClass().add("gGrid");
            Text t = new Text(line);
            t.getStyleClass().add("TStyle");
            gPane.add(t, 1, 0);

            if (FileManager.checkForCode(line)) {
                lookupTable.add(x);
                CheckBox debugPoint = new CheckBox();
                debugPoint.getStyleClass().add("CB");
                gPane.add(debugPoint, 0, 0);
                debugPoints.add(debugPoint);
                codePanes.add(gPane);
            } else {
                CheckBox invis = new CheckBox();
                invis.getStyleClass().add("CB");
                invis.visibleProperty().set(false);
                gPane.add(invis, 0, 0);
            }

            bigGrid.add(gPane, 0, x);

            x++;
        }

        scrollPaneCode.setContent(bigGrid);
    }

    private void updateGUI() {
        setRamIntoField();
        setPointerIntoField();
        labelWReg.setText(intToHex(pic.w));
        labelPC.setText(intToHex(pic.pCounter.get()));
        labelWDT.setText("" + pic.watchdog.get());
        labelStackpointer.setText("" + pic.stack.getPointer());
        labelC.setText("" + (pic.ram.getReg(3) & 0b001));
        labelDC.setText("" + ((pic.ram.getReg(3) & 0b010) >> 1));
        labelZ.setText("" + ((pic.ram.getReg(3) & 0b100) >> 2));
        updateRB();

        highLightLine();
    }

    private void setRamIntoField() {
        for (int i = 0; i < 256; i++) {
            field[i].setText(intToHex(pic.ram.getBuffer(i)));
        }
    }

    private void setPointerIntoField() {
        for (int i = 0; i < 8; i++) {
            labelStack[i].setText(intToHex(pic.stack.getBuffer(i)));
        }
    }

    private GridPane nowHigh = new GridPane();

    private void highLightLine() {
        nowHigh.setStyle("-fx-background-color: #00001a;");
        nowHigh = codePanes.get(pic.pCounter.get());
        nowHigh.setStyle("-fx-background-color: #00b8e6;");
    }

    private void updateRB()
    {
        for (int i = 0; i < 8; i++) {
            fieldRBPin[i].setText(""+ ((pic.ram.getReg(6) & (0b1 << i)) >> i));
        }
    }

    private void updateRA()
    {
        for (int i = 0; i < 8; i++) {
            fieldRAPin[i].setText(""+ ((pic.ram.getReg(5) & (0b1 << i)) >> i));
        }
    }

}
