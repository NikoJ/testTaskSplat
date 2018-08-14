package app;

import app.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static final String APP_TITLE = "Тестовое задание";
    private static final String ROOT_FXML = "view/MainScene.fxml";
    private Stage primaryStage;


    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle(APP_TITLE);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(ROOT_FXML));
        Parent personOverview = loader.load();
        primaryStage.setScene(new Scene(personOverview));
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setApp(this);
    }
}

