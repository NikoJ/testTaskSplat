package app.controller;

import app.MainApp;
import app.utill.FileUtils;
import app.utill.RenderingUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable, Controller {
    private final DirectoryChooser directoryChooser = new DirectoryChooser();

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
        String fileIcon = String.valueOf(getClass().getResource("/img/file.png"));
        String catalogIcon = String.valueOf(getClass().getResource("/img/catalog.png"));

        searchBtn.setOnAction(event -> {
            File dir = directoryChooser.showDialog(application.getPrimaryStage());  //кинуть исключение
            if (dir != null) {
                root = new TreeItem<>(dir.getAbsolutePath(), new ImageView(new Image(catalogIcon)));
                treeView.setRoot(RenderingUtils.printTree(dir, root, searhTf.getText().trim(), typeFileTf.getText().trim(), fileIcon, catalogIcon));
            }
        });
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String path = RenderingUtils.printPath(newValue, newValue.getValue());
            List<Integer> list = new ArrayList<>();
            if (new File(path).isFile()) {
                try {
                    list = FileUtils.openFile(path, textArea, searhTf.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                backBtn.setDisable(true);
                nextBtn.setDisable(true);
                textArea.positionCaret(list.get(0));
                textArea.selectRange(list.get(0), list.get(0) + searhTf.getText().length());
            }
        });


    }

    @Override
    public void setApp(MainApp app) {
        application = app;
    }
}
