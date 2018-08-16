package app.utill;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

public class RenderingUtils {

    private RenderingUtils() {
    }

    public static TreeItem<String> printTree(File dir, TreeItem<String> root, String search, String type, String fileIcon, String catalogIcon) {
        if (type.equals("")) type = "log";
        File[] file = dir.listFiles();
        if (file != null) {
            root.setExpanded(true);
            for (File f : file) {
                if (f.isFile()) {
                    if (Objects.equals(FileUtils.getExtension(f), type) & FileUtils.isFind(search, f)) {
                        root.getChildren().add(new TreeItem<>(f.getName(), new ImageView(new Image(fileIcon))));
                    }
                } else if (f.isDirectory()) {
                    root.getChildren().add(printTree(f, new TreeItem<>(f.getName(), new ImageView(new Image(catalogIcon))), search, type, fileIcon, catalogIcon));
                }
            }
        }
        return root;
    }

    public static String printPath(TreeItem<String> treeItem, String str) {
        if (Objects.nonNull(treeItem)) {
            if (Objects.nonNull(treeItem.getParent())) {
                return printPath(treeItem.getParent(), treeItem.getParent().getValue()) + "\\" + str;
            }
        }
        return str;
    }
}
