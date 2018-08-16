package app.utill;

import javafx.scene.control.TextArea;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private FileUtils() {
    }

    public static String getExtension(File filename) {
        if (filename != null) {
            String[] name = filename.getName().split("\\.");
            int length = name.length;
            return length > 1 ? name[length - 1] : "";
        }
        return "";
    }

    public static boolean isFind(String search, File file) {
        try {
            FileInputStream stream = new FileInputStream(file.getAbsolutePath());
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(search)) return true;
            }
        } catch (IOException e) {
            System.out.println("Error: this is file: " + file.getName() + " not find");
        }
        return false;
    }

    public static List openFile(String url, TextArea textArea, String search) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fstream = new FileInputStream(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
        String text = stringBuilder.toString();
        textArea.setText(text);

        String[] strings = text.split(search);
        List list = new ArrayList();
        int count = 0;
        for (int i = 0; i < strings.length - 1; i++) {
            int length = strings[i].length();
            int lengthSearch = search.length();
            list.add(length + count);
            count += length + lengthSearch;
        }

        return list;
    }
}
