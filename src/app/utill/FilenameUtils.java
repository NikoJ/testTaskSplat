package app.utill;

import java.io.File;

public class FilenameUtils {
    private FilenameUtils() {
    }

    public static String getExtension(File filename) {
        String[] name = filename.getName().split("\\.");
        int length = name.length;
        return length > 1 ? name[length - 1] : "";
    }
}
