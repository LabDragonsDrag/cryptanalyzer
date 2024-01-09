module com.javarush.cryptanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javarush.cryptanalyzer to javafx.fxml;
    exports com.javarush.cryptanalyzer;
    exports com.javarush.cryptanalyzer.controller;
    opens com.javarush.cryptanalyzer.controller to javafx.fxml;
}