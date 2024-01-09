package com.javarush.cryptanalyzer.controller;

import com.javarush.cryptanalyzer.services.BruteforceFileService;
import com.javarush.cryptanalyzer.util.CryptoAnalyzerTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

import static com.javarush.cryptanalyzer.util.SceneManager.sceneChanger;

public class BruteforceSceneController {


    private File inputFile;
    private File outputDirectory;
    private int keyStart;
    private int keyEnd;
    private BruteforceFileService bruteforceFileService;
    @FXML
    private Button backToMainBtn;

    @FXML
    private Button btnSelectFileInput;

    @FXML
    private Button btnSelectFileOutput;

    @FXML
    private TextField directoryFieldOutput;

    @FXML
    private TextField fileFieldInput;

    @FXML
    private TextField keyFieldEnd;

    @FXML
    private TextField keyFieldStart;

    @FXML
    private ListView<String> bruteList;

    @FXML
    private Label validateKeyLabel;

    @FXML
    private Label validateLabelDirectoryOutput;

    @FXML
    private Label validateLabelFileInput;

    @FXML
    private Label statusBruteforce;


    @FXML
    public void initialize() {
        keyFieldStart.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                keyFieldStart.setText(newValue.replaceAll("\\D", ""));
            } else if (!newValue.isEmpty()) {
                keyStart = Integer.parseInt(keyFieldStart.getText());
            }
        });
        keyFieldEnd.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                keyFieldEnd.setText(newValue.replaceAll("\\D", ""));
            } else if (!newValue.isEmpty()) {
                keyEnd = Integer.parseInt(keyFieldEnd.getText());
            }
        });
        bruteList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
                bruteforceFileService.decryptFile(newValue);
                statusBruteforce.setText("Готово!");
        });
    }

    @FXML
    protected void onBruteforceFileBtnClick() {
        if (!validateField()) return;
        bruteforceFileService = new BruteforceFileService(inputFile, outputDirectory, keyStart, keyEnd);
        fillBruteListResult(bruteforceFileService.bruteForce());
        statusBruteforce.setText("Ожидание");

    }

    private void fillBruteListResult(String[] results) {
        bruteList.getItems().clear();
        ObservableList<String> listResult = FXCollections.observableArrayList(results);
        bruteList.setItems(listResult);
    }


    @FXML
    protected void onSelectFileInputBtnClick() {
        fileFieldInput.setText("");
        validateLabelFileInput.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        try {
            inputFile = fileChooser.showOpenDialog(btnSelectFileInput.getScene().getWindow());
            fileFieldInput.setText(inputFile.getName());
        } catch (RuntimeException ex) {
            validateLabelFileInput.setVisible(true);
            validateLabelFileInput.setText("Файл не выбран!");
        }
    }


    @FXML
    protected void onSelectDirectoryOutputBtnClick() {
        directoryFieldOutput.setText("");
        validateLabelDirectoryOutput.setVisible(false);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        try {
            outputDirectory = directoryChooser.showDialog(btnSelectFileOutput.getScene().getWindow());
            directoryFieldOutput.setText(outputDirectory.getName());
        } catch (RuntimeException ex) {
            validateLabelDirectoryOutput.setVisible(true);
            validateLabelDirectoryOutput.setText("Директория  не выбран!");
        }

    }

    @FXML
    protected void onBackToMainBtnClick() {
        sceneChanger("main-scene.fxml", backToMainBtn);
    }


    private boolean validateField() {
        boolean isValid = true;
        if (isFileNotEmptyOrExist(inputFile)) {
            validateLabelFileInput.setVisible(true);
            validateLabelFileInput.setText("Файл не выбран или некорректен");
            isValid = false;
        }
        if (isFileNotEmptyOrExist(outputDirectory)) {
            validateLabelDirectoryOutput.setVisible(true);
            validateLabelDirectoryOutput.setText("Директория не выбрана или некорректна");
            isValid = false;
        }
        if (keyFieldStart.getText().isEmpty() || keyFieldEnd.getText().isEmpty()) {
            validateKeyLabel.setVisible(true);
            validateKeyLabel.setText("Ключ не введен");
            isValid = false;
        }
        return isValid;
    }
    private  boolean isFileNotEmptyOrExist(File file){
        return file == null || !file.exists();
    }

}
