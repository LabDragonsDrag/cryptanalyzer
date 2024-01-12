module com.javarush.cryptanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javarush.cryptanalyzer.novikov to javafx.fxml;
    exports com.javarush.cryptanalyzer.novikov;
    exports com.javarush.cryptanalyzer.novikov.controller;
    opens com.javarush.cryptanalyzer.novikov.controller to javafx.fxml;
}