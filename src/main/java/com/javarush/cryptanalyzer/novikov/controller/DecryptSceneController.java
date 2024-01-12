package com.javarush.cryptanalyzer.novikov.controller;

import com.javarush.cryptanalyzer.novikov.services.DecryptFileService;
import com.javarush.cryptanalyzer.novikov.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class DecryptSceneController {


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
    protected TextField fileFieldInput;

    @FXML
    protected TextField directoryFieldOutput;

    @FXML
    protected TextField keyField;

    @FXML
    protected Button startEncryptBtn;

    @FXML
    protected Label statusDecrypt;

    @FXML
    protected Label validateKeyLabel;

    @FXML
    protected Label validateLabelFileInput;

    @FXML
    protected Label validateLabelDirectoryOutput;

    @FXML
    protected void onDecryptFileBtnClick() {
        if (!validateField()) return;

        DecryptFileService decryptFileService = new DecryptFileService(inputFile, outputDirectory, key);
        decryptFileService.decryptFile();
        statusDecrypt.setText("Готово!");
    }


    @FXML
    public void initialize() {
        keyField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                keyField.setText(newValue.replaceAll("\\D", ""));
            } else if (!newValue.isEmpty()) {
                key = Integer.parseInt(keyField.getText());
            }
        });
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
        statusDecrypt.setText("Ожидание");
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
        statusDecrypt.setText("Ожидание");
    }

    @FXML
    protected void onBackToMainBtnClick() {
        SceneManager.sceneChanger("main-scene.fxml", backToMainBtn);
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
        if (keyField.getText().isEmpty()) {
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

