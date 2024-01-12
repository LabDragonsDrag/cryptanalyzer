package com.javarush.cryptanalyzer.novikov.controller;

import com.javarush.cryptanalyzer.novikov.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainSceneController {
    @FXML
    protected Button encryptSceneBtn;
    @FXML
    protected Button decryptSceneBtn;
    @FXML
    protected Button bruteforceSceneBtn;


    @FXML
    protected void switchToEncryptionScene() {

        SceneManager.sceneChanger("encrypt-scene.fxml", encryptSceneBtn);

    }

    @FXML
    protected void switchToDecryptionScene() {

        SceneManager.sceneChanger("decrypt-scene.fxml", decryptSceneBtn);

    }

    @FXML
    protected void switchToBruteforceScene() {

        SceneManager.sceneChanger("bruteforce-scene.fxml", bruteforceSceneBtn);

    }


}
