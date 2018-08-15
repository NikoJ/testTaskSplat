package app.utill;

import javafx.scene.control.TreeItem;

import java.io.File;
import java.util.Objects;

public class RenderingUtils {
    private RenderingUtils() {
    }

    public static TreeItem<String> printTree(File dir, TreeItem<String> root) {
        return printTree(dir, root, "", "");
    }

    public static TreeItem<String> printTree(File dir, TreeItem<String> root, String search, String type) {
        File[] file = dir.listFiles();
        if (file != null) {
            root.setExpanded(true);
            for (File f : file) {
                if (f.isFile()) {
                    if (Objects.equals(FilenameUtils.getExtension(f), "log")) {
                        root.getChildren().add(new TreeItem<>(f.getName()));
                    }
                } else if (f.isDirectory()) {
                    TreeItem r = new TreeItem<>(dir.getName());
                    root.getChildren().addAll(printTree(f, r, search, type));
                }
            }
        }
        return root;
    }
}
