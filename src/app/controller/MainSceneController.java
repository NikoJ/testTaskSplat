package app.controller;

import app.MainApp;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable, Controller {
    private MainApp application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @Override
    public void setApp(MainApp app) {
        application = app;
    }
}
