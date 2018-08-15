package app.controller;

import app.MainApp;
import app.utill.RenderingUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable, Controller {
    final FileChooser fileChooser = new FileChooser();
    final DirectoryChooser directoryChooser = new DirectoryChooser();
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TextField searhTf;
    @FXML
    private Button backBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField typeFileTf;
    private MainApp application;
    private TreeItem<String> root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBtn.setOnAction(event -> {
            File dir = directoryChooser.showDialog(application.getPrimaryStage());  //кинуть исключение
            if (dir != null) {
                root = new TreeItem<>(dir.getAbsolutePath());
                treeView.setRoot(RenderingUtils.printTree(dir, root, searhTf.getText().trim(), typeFileTf.getText().trim()));
                List<File> files = Arrays.asList(dir);
                printLog(textArea, files);
            }
        });
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.getValue());
            if(newValue.isLeaf())
            {
                System.out.println( newValue.getParent().getValue());
            }
        });

    }

    @Override
    public void setApp(MainApp app) {
        application = app;
    }

    private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }
}
