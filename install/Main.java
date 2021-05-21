package hwjavacore.hw313inoutput.install;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //исходный путь к папке Games (ввести свой)
        String gamesPath = "/Users/alena/Games/";
        StringBuilder myLogs = new StringBuilder();

        //В папке Games создайте несколько директорий: src, res, savegames, temp
        myLogs.append(createDir(gamesPath, "src"));
        myLogs.append(createDir(gamesPath, "res"));
        myLogs.append(createDir(gamesPath, "savegames"));
        myLogs.append(createDir(gamesPath, "temp"));

        //В каталоге src создайте две директории: main, test
        String srcPath = gamesPath + "src/";
        myLogs.append(createDir(srcPath, "main"));
        myLogs.append(createDir(srcPath, "test"));

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        String dirMain = srcPath + "main/";
        myLogs.append(createFile(dirMain, "Main.java"));
        myLogs.append(createFile(dirMain, "Utils.java"));

        //В каталог res создайте три директории: drawables, vectors, icons.
        String resPath = gamesPath + "res/";
        myLogs.append(createDir(resPath, "drawables"));
        myLogs.append(createDir(resPath, "vectors"));
        myLogs.append(createDir(resPath, "icons"));

        //В директории temp создайте файл temp.txt
        String dirTemp = gamesPath + "temp/";
        myLogs.append(createFile(dirTemp, "temp.txt"));

        //StringBuilder текст запишите в файл temp.txt с помощью класса FileWriter
        File fileTemptxt = new File(dirTemp, "temp.txt");
        try (FileWriter writer = new FileWriter(fileTemptxt, false)) {
            writer.write(String.valueOf(myLogs));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static String createDir(String path, String name) {
        File dirSrc = new File(path + name);
        if (dirSrc.mkdir())
            return("Каталог" + dirSrc + " создан.\n");
        else
            return("Каталог" + dirSrc + " не был создан.\n");
    }

    public static String createFile(String path, String name) {
        File fileTemptxt = new File(path, name);
        try {
            if (fileTemptxt.createNewFile())
                return("Файл " + name + " создан.\n");
            else
                return("Файл " + name + " не был создан.\n");
        }
        catch (IOException ex) {
            return(ex.getMessage() + "\n");
        }
    }
}