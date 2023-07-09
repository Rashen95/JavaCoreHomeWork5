package Classes;

import java.io.*;
import java.util.Objects;

public class FileCopy {
    public static void makeBackUp(String pathname) throws IOException {
        File dir = new File(pathname);
        mkDir(pathname);
        for (File item : Objects.requireNonNull(dir.listFiles())) {
            if (item.isFile()) {
                copyFile(item, new File(String.format("%s/.backup/%s", pathname, item.getName())));
            }
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public static void mkDir(String pathname) {
        File directory = new File(String.format("%s/.backup", pathname));
        if (directory.exists()) {
            if (!directory.delete()) {
                System.out.println("Папка .backup обновлена");
            }
        } else {
            if (directory.mkdir()) {
                System.out.println("Папка .backup создана");
            }
        }
    }
}