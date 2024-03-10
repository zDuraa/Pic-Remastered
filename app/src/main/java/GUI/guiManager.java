package GUI;

import Model.Pic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.text.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

import static utils.converter.intToHex;

import javafx.stage.Stage;
import utils.FileManager;

public class guiManager {

    @FXML
    private Button buttonGo;

    @FXML
    private Label labeINTCON;

    @FXML
    private Label labelC;

    @FXML
    private Label labelDC;

    @FXML
    private Label labelLaufzeit;
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
    private Label labelQuarzfrequenz;

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
    private Slider sliderQuarzfrequenz;

    @FXML
    private Pane paneSFRversteckt;
    @FXML
    private Button buttonFile;

    @FXML
    private Button buttonLED;
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
    private Label labelVT;

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
                if (debugSelected()) {
                    Platform.runLater(() -> {
                        buttonGo.setText("Go");
                        controle = false;
                    });
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
    void buttonResetOnClick(ActionEvent event) {
        pic = new Pic(FileManager.getCommands());
        updateGUI();
    }

    @FXML
    void buttonStepInOnClick(ActionEvent event) {
        pic.next();
        updateGUI();
    }

    @FXML
    void buttonFileOnClick(ActionEvent event) {
        FileManager.resetFile();
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

        Stage currentStage = (Stage) buttonFile.getScene().getWindow();
        currentStage.close();
        initialize();
    }

    ledManager lM;

    @FXML
    void buttonLEDOnClick(ActionEvent event) {
        Stage ledStage = new Stage();
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(start.class.getResource("./../views/led.fxml"));
        try {

            root = loader.load();
            Scene scene = new Scene(root);
            ledStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Typisiere den Controller, um auf die getController-Methode zugreifen zu
        // können
        lM = loader.getController();
        lM.addGM(this);
        ledStage.show();

    }

    @FXML
    void clickWDT(ActionEvent event) {
        if (radioWDT.isSelected()) {
            pic.watchdog.start();
        } else {
            pic.watchdog.stop();
        }
    }

    @FXML
    void onDragDetectedQuarz(MouseEvent event) {
        pic.runtime.setQuarzfrequenz(sliderQuarzfrequenz.getValue());
        labelQuarzfrequenz.setText(""+(int)sliderQuarzfrequenz.getValue()+"μs");
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
            fieldRATris[x].setText("i");
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
            fieldRAPin[x].setId("RA" + x);
            fieldRAPin[x].getStyleClass().add("TFSty");
            paneRA.getChildren().add(fieldRAPin[x]);
            resize += 27;
        }
        manageRPins(fieldRAPin, 5);
    }

    private void manageRPins(TextField felder[], int register) {

        for (int i = 0; i < 8; i++) {
            int finalI = i;
            felder[i].setOnMouseClicked(e -> {
                if (felder[finalI].getText().equals("0")) {
                    pic.ram.addToReg(register, (0b1 << finalI));
                    System.out.println("set1" + finalI);
                    updateGUI();

                } else {
                    pic.ram.setReg(register, 0);
                    System.out.println("set0" + finalI);
                    updateGUI();
                }
                checkRA4(e);
            });
        }
    }

    private void checkRA4(MouseEvent e) {
        TextField tempTF = (TextField) e.getSource();
        int val = Integer.parseInt(tempTF.getText());
        if (tempTF.getId().equals("RA4")) {
            if (((pic.ram.getOpt() & 0b10000) >> 4) == 0 && val == 1) {
                pic.RA4();
            } else if (((pic.ram.getOpt() & 0b10000) >> 4) == 1 && val == 0) {
                pic.RA4();
            }
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
            fieldRBTris[x].setText("i");
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
            fieldRBPin[x].setEditable(false);
            fieldRBPin[x].setId("RB" + x);
            fieldRBPin[x].setAlignment(Pos.CENTER);
            fieldRBPin[x].getStyleClass().add("TFSty");
            paneRB.getChildren().add(fieldRBPin[x]);
            resize += 27;
        }
        manageRPins(fieldRBPin, 6);
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
                lookupTable.add(Integer.parseInt(line.substring(0, 4), 16));
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

        updateSichtbar();
        updateVersteckt();
        updateStatus();
        updateOption();
        updateIntCon();

        updateRB();
        updateRA();
        highLightLine();

        if (lM != null)
            lM.manageImage();

    }

    private void updateSichtbar() {
        labelWReg.setText(intToHex(pic.w));
        labelFSR.setText(intToHex(pic.ram.getReg(4)));
        labelPCL.setText(intToHex(pic.pCounter.get()));
        labelPCLATH.setText(intToHex(pic.ram.getReg(10)));
        labelStatus.setText(intToHex(pic.ram.getReg(3)));
    }

    private void updateVersteckt() {
        labelPC.setText(intToHex(pic.pCounter.get()));
        labelWDT.setText("" + pic.watchdog.get());
        labelStackpointer.setText("" + pic.stack.getPointer());
        radioWDT.setSelected(pic.watchdog.WTD);
        labelVT.setText("" + pic.prescaler.getPrescaler());
        labelLaufzeit.setText(""+pic.runtime.getRuntime());
    }

    private void updateStatus() {
        labelC.setText("" + (pic.ram.getReg(3) & 0b001));
        labelDC.setText("" + ((pic.ram.getReg(3) & 0b010) >> 1));
        labelZ.setText("" + ((pic.ram.getReg(3) & 0b100) >> 2));
        labelPD.setText("" + ((pic.ram.getReg(3) & 0b1000) >> 3));
        labelTO.setText("" + ((pic.ram.getReg(3) & 0b10000) >> 4));
        labelRP0.setText("" + ((pic.ram.getReg(3) & 0b100000) >> 5));
        labelRP1.setText("" + ((pic.ram.getReg(3) & 0b1000000) >> 6));
        labelIRP.setText("" + ((pic.ram.getReg(3) & 0b10000000) >> 7));
    }

    private void updateOption() {
        labelPS0.setText("" + (pic.ram.getOpt() & 0b001));
        labelPS1.setText("" + ((pic.ram.getOpt() & 0b010) >> 1));
        labelPS2.setText("" + ((pic.ram.getOpt() & 0b100) >> 2));
        labelPSA.setText("" + ((pic.ram.getOpt() & 0b1000) >> 3));
        labelT0SE.setText("" + ((pic.ram.getOpt() & 0b10000) >> 4));
        labelT0CS.setText("" + ((pic.ram.getOpt() & 0b100000) >> 5));
        labelIntEdg.setText("" + ((pic.ram.getOpt() & 0b1000000) >> 6));
        labelRBP.setText("" + ((pic.ram.getOpt() & 0b10000000) >> 7));
    }

    private void updateIntCon() {
        labelRBIF.setText("" + (pic.ram.getReg(11) & 0b001));
        labelINTF.setText("" + ((pic.ram.getReg(11) & 0b010) >> 1));
        labelT0IF.setText("" + ((pic.ram.getReg(11) & 0b100) >> 2));
        labelRBIF.setText("" + ((pic.ram.getReg(11) & 0b1000) >> 3));
        labelINTE.setText("" + ((pic.ram.getReg(11) & 0b10000) >> 4));
        labelT0IE.setText("" + ((pic.ram.getReg(11) & 0b100000) >> 5));
        labelPIE.setText("" + ((pic.ram.getReg(11) & 0b1000000) >> 6));
        labelGIE.setText("" + ((pic.ram.getReg(11) & 0b10000000) >> 7));
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
        for (int i = 0; i < lookupTable.size(); i++) {
            if (lookupTable.get(i) == pic.pCounter.get()) {
                nowHigh = codePanes.get(i);
                break;
            }
        }
        nowHigh.setStyle("-fx-background-color: #00b8e6;");
    }

    private void updateRB() {
        for (int i = 0; i < 8; i++) {
            if(pic.ram.getBuffer(134) == 255){
                fieldRBTris[i].setText("i");
            }else{
                fieldRBTris[i].setText("o");
            }
            fieldRBPin[i].setText("" + ((pic.ram.getReg(6) & (0b1 << i)) >> i));
        }
    }

    private void updateRA() {
        for (int i = 0; i < 8; i++) {
            fieldRAPin[i].setText("" + ((pic.ram.getReg(5) & (0b1 << i)) >> i));
        }
    }

    public TextField getPinB(int i) {
        return fieldRBPin[i];
    }

    private boolean debugSelected() {
        boolean ret = false;
        for (int i = 0; i < debugPoints.size(); i++) {
            if (lookupTable.get(i) == pic.pCounter.get()) {
                ret = debugPoints.get(i).isSelected();
                break;
            }
        }

        return ret;
    }


}
