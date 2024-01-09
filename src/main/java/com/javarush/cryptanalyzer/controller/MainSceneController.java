package com.javarush.cryptanalyzer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static com.javarush.cryptanalyzer.util.SceneManager.sceneChanger;

public class MainSceneController {
    @FXML
    protected Button encryptSceneBtn;
    @FXML
    protected Button decryptSceneBtn;
    @FXML
    protected Button bruteforceSceneBtn;


    @FXML
    protected void switchToEncryptionScene() {

        sceneChanger("encrypt-scene.fxml", encryptSceneBtn);

    }

    @FXML
    protected void switchToDecryptionScene() {

        sceneChanger("decrypt-scene.fxml", decryptSceneBtn);

    }

    @FXML
    protected void switchToBruteforceScene() {

        sceneChanger("bruteforce-scene.fxml", bruteforceSceneBtn);

    }


}
