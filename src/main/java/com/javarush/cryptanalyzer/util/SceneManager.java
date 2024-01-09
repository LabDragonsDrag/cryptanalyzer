package com.javarush.cryptanalyzer.util;

import com.javarush.cryptanalyzer.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    public static void sceneChanger(String sceneName, Node node){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(sceneName));
            Stage window = (Stage) node.getScene().getWindow();
            window.setScene(new Scene(fxmlLoader.load(), 800, 500));
        }catch (IOException ex){
            System.err.println(ex.getMessage());
        }

    }
}
