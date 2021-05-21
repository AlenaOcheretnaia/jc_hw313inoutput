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
        File dirSrc = new File(gamesPath+"src");
        if (dirSrc.mkdir())
            myLogs.append("Каталог" + dirSrc + " создан.\n");

        File dirRes = new File(gamesPath + "res");
        if (dirRes.mkdir())
            myLogs.append("Каталог" + dirRes + " создан.\n");

        File dirSavegames = new File(gamesPath + "savegames");
        if (dirSavegames.mkdir())
            myLogs.append("Каталог" + dirSavegames + " создан.\n");

        File dirTemp = new File(gamesPath + "temp");
        if (dirTemp.mkdir())
            myLogs.append("Каталог" + dirTemp + " создан.\n");

        //В каталоге src создайте две директории: main, test
        String srcPath = gamesPath + "src/";
        File dirMain = new File(srcPath + "main");
        if (dirMain.mkdir())
            myLogs.append("Каталог" + dirMain + " создан.\n");

        File dirTest = new File(srcPath + "test");
        if (dirTest.mkdir())
            myLogs.append("Каталог" + dirTest + " создан.\n");

        //В подкаталоге main создайте два файла: Main.java, Utils.java.
        File fileMainjava = new File(dirMain, "Main.java");
        try {
            if (fileMainjava.createNewFile())
                myLogs.append("Файл " + fileMainjava + " создан.\n"); }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
                myLogs.append(ex.getMessage() + "\n");
        }

        File fileUtilsjava = new File(dirMain, "Utils.java");
        try {
            if (fileUtilsjava.createNewFile())
                myLogs.append("Файл " + fileUtilsjava + " создан.\n"); }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            myLogs.append(ex.getMessage() + "\n");
        }

        //В каталог res создайте три директории: drawables, vectors, icons.
        String resPath = gamesPath + "res/";
        File dirDrawables = new File(resPath+"drawables");
        if (dirDrawables.mkdir())
            myLogs.append("Каталог" + dirDrawables + " создан.\n");

        File dirVectors = new File(resPath + "vectors");
        if (dirVectors.mkdir())
            myLogs.append("Каталог" + dirVectors + " создан.\n");

        File dirIcons = new File(resPath + "icons");
        if (dirIcons.mkdir())
            myLogs.append("Каталог" + dirIcons + " создан.\n");

        //В директории temp создайте файл temp.txt
        File fileTemptxt = new File(dirTemp, "temp.txt");
        try {
            if (fileTemptxt.createNewFile())
                myLogs.append("Файл " + fileTemptxt + " создан.\n"); }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            myLogs.append(ex.getMessage() + "\n");
        }

        //StringBuilder текст запишите в файл temp.txt с помощью класса FileWriter
        try (FileWriter writer = new FileWriter(fileTemptxt, false)) {
            writer.write(String.valueOf(myLogs));
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

