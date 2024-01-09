package com.javarush.cryptanalyzer.controller;

import com.javarush.cryptanalyzer.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.javarush.cryptanalyzer.util.SceneManager.sceneChanger;

public class MainSceneController {
    @FXML
    protected Button encryptSceneBtn;
    @FXML
    protected Button decryptSceneBtn;
    @FXML
    protected Button bruteforceSceneBtn;


    @FXML
    protected void switchToEncryptionScene(){
       encryptSceneBtn.setStyle("-fx-background-color: #ffe500;");
         sceneChanger("encrypt-scene.fxml" , encryptSceneBtn);
       encryptSceneBtn.setStyle("-fx-background-color: #feda6a;");
    }
    @FXML
    protected void switchToDecryptionScene(){
        decryptSceneBtn.setStyle("-fx-background-color: #ffe500;");
        sceneChanger("decrypt-scene.fxml", decryptSceneBtn);
        decryptSceneBtn.setStyle("-fx-background-color: #feda6a;");
    }
    @FXML
    protected void switchToBruteforceScene() throws InterruptedException {
        bruteforceSceneBtn.setStyle("-fx-background-color: #ffe500;");
        sceneChanger("bruteforce-scene.fxml", bruteforceSceneBtn);
        bruteforceSceneBtn.setStyle("-fx-background-color: #feda6a;");
    }




}
