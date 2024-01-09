package com.javarush.cryptanalyzer.controller;

import com.javarush.cryptanalyzer.services.EncryptFileService;
import com.javarush.cryptanalyzer.util.Validator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

import static com.javarush.cryptanalyzer.util.SceneManager.sceneChanger;

public class EncryptSceneController {


    private File inputFile;
    private File outputDirectory;
    private int key;
    @FXML
    private Button backToMainBtn;

    @FXML
    protected Button btnSelectFileOutput;

    @FXML
    protected Button btnSelectFileInput;

    @FXML
    protected TextField fileFiledInput;

    @FXML
    protected TextField directoryFiledOutput;

    @FXML
    protected TextField keyField;

    @FXML
    protected Button startEncryptBtn;

    @FXML
    protected Label statusEncrypt;

    @FXML
    protected Label validateKeyLabel;

    @FXML
    protected Label validateLabelFileInput;

    @FXML
    protected Label validateLabelDirectoryOutput;

    @FXML
    protected void onEncryptFileBtnClick() {
        if(!validateField()) return;

        EncryptFileService encryptFileService = new EncryptFileService(inputFile, outputDirectory, key);
        encryptFileService.encryptFile();
    }


    @FXML
    public void initialize() {
        keyField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                keyField.setText(newValue.replaceAll("\\D", ""));
            }else if(!newValue.isEmpty()) {
                key = Integer.parseInt(keyField.getText());
            }
        });
    }


    @FXML
    protected void onSelectFileInputBtnClick() {
        fileFiledInput.setText("");
        validateLabelFileInput.setVisible(false);
        FileChooser fileChooser = new FileChooser();
        try {
            inputFile = fileChooser.showOpenDialog(btnSelectFileInput.getScene().getWindow());
            fileFiledInput.setText(inputFile.getName());
        } catch (RuntimeException ex) {
            validateLabelFileInput.setVisible(true);
            validateLabelFileInput.setText("Файл не выбран!");
        }
    }


    @FXML
    protected void onSelectDirectoryOutputBtnClick() {
        directoryFiledOutput.setText("");
        validateLabelDirectoryOutput.setVisible(false);
        DirectoryChooser directoryChooser = new DirectoryChooser();
        try {
            outputDirectory = directoryChooser.showDialog(btnSelectFileOutput.getScene().getWindow());
            directoryFiledOutput.setText(outputDirectory.getName());
        } catch (RuntimeException ex) {
            validateLabelDirectoryOutput.setVisible(true);
            validateLabelDirectoryOutput.setText("Директория  не выбран!");
        }

    }

    @FXML
    protected void onBackToMainBtnClick() {
        sceneChanger("main-scene.fxml", backToMainBtn);
    }


    private boolean validateField(){
        boolean isValid = true;
        if (!Validator.isFileNotEmptyOrExist(inputFile)) {
            validateLabelFileInput.setVisible(true);
            validateLabelFileInput.setText("Файл не выбран или некорректен");
           isValid = false;
        }
        if (!Validator.isFileNotEmptyOrExist(outputDirectory)) {
            validateLabelDirectoryOutput.setVisible(true);
            validateLabelDirectoryOutput.setText("Директория не выбрана или некорректна");
            isValid = false;
        }
        if(keyField.getText().isEmpty()){
            validateKeyLabel.setVisible(true);
            validateKeyLabel.setText("Ключ не введен");
            isValid = false;
        }
        return  isValid;
    }


}