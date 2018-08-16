package app.controller;

import app.MainApp;
import app.utill.FileUtils;
import app.utill.RenderingUtils;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable, Controller {
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private int count;
    private List<Integer> list = new ArrayList<>();
    private String search;
    private String type;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private TextField searchTf;
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
    @FXML
    private StackPane mainPane;
    private MainApp application;
    private TreeItem<String> root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String fileIcon = String.valueOf(getClass().getResource("/img/file.png"));
        String catalogIcon = String.valueOf(getClass().getResource("/img/catalog.png"));
        mainPane.getStylesheets().add(String.valueOf(getClass().getResource("/style/application.css")));

        searchBtn.setOnAction(event -> {
            textArea.setText("");
            nextBtn.setDisable(true);
            backBtn.setDisable(true);
            search = searchTf.getText().trim();
            type = typeFileTf.getText().trim();

            File dir = directoryChooser.showDialog(application.getPrimaryStage());  //кинуть исключение
            if (dir != null) {
                root = new TreeItem<>(dir.getAbsolutePath(), new ImageView(new Image(catalogIcon)));
                treeView.setRoot(RenderingUtils.printTree(dir, root, search, type, fileIcon, catalogIcon));
            }
        });

        treeView.getSelectionModel().selectedItemProperty().addListener(this::changed);
    }

    @Override
    public void setApp(MainApp app) {
        application = app;
    }

    private void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
        String path = RenderingUtils.printPath(newValue, newValue.getValue());
        count = 0;

        if (Objects.nonNull(path)) {
            if (new File(path).isFile()) {
                try {
                    list = FileUtils.openFile(path, textArea, search);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (list.size() > 1 && !search.equals("")) {
                    nextBtn.setDisable(false);
                }
                textArea.selectRange(list.get(count), list.get(count) + search.length());

                nextBtn.setOnAction(event -> {
                    if (count == 0) backBtn.setDisable(false);
                    if (count < list.size() - 1) {
                        count++;
                        int position = list.get(count);
                        textArea.selectRange(position, position + search.length());
                        if (count == list.size() - 1) {
                            nextBtn.setDisable(true);
                            backBtn.requestFocus();
                        }
                    }
                });

                backBtn.setOnAction(event -> {
                    count--;
                    nextBtn.setDisable(false);
                    int position = list.get(count);
                    textArea.selectRange(position, position + search.length());
                    if (count == 0) {
                        backBtn.setDisable(true);
                    }
                });
            }
        }
    }
}
