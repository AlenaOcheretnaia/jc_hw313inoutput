package hwjavacore.hw313inoutput.tozip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        //исходный путь к папке Games (ввести свой)
        String gamesPath = "/Users/alena/Games/";
        String savegamesPath = gamesPath + "savegames/";

        //Создать три экземпляра класса GameProgress.
        GameProgress progress1 = new GameProgress(10,1,1,2.55);
        GameProgress progress2 = new GameProgress(8,3,3,5.43);
        GameProgress progress3 = new GameProgress(6,5,5,7.76);

        //Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
        saveGame(savegamesPath + "save1.dat", progress1);
        saveGame(savegamesPath + "save2.dat", progress2);
        saveGame(savegamesPath + "save3.dat", progress3);

        //Созданные файлы сохранений из папки savegames запаковать в архив zip.
        String archivePath = savegamesPath + "/mySaveGames.zip";

        File dirSaveGames = new File(savegamesPath);
        if (dirSaveGames.isDirectory())
            zipFiles(archivePath, dirSaveGames.listFiles());
        else
            System.out.println("Запрашиваемая директория не существует");

        //Удалить файлы сохранений, лежащие вне архива.
        File archive = new File(archivePath);
        deleteFiles(dirSaveGames.listFiles(), archive.getName());
    }


    public static void saveGame(String path, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
            System.out.println("New file was created for " + progress.toString());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String archivePath, File[] filesList){
        try {
            byte[] buffer = new byte[1024];
            FileOutputStream fos = new FileOutputStream(archivePath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for (int i=0; i < filesList.length; i++) {
                //File srcFile = new File(filesList[i]);
                FileInputStream fis = new FileInputStream(filesList[i]);
                ZipEntry entry = new ZipEntry(filesList[i].getName());
                zos.putNextEntry(entry);
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
                // закрываем InputStream
                fis.close();
                System.out.println("File " + filesList[i].getName() + " was added to archive.");
            }
            // закрываем ZipOutputStream
            zos.close();
            File archive = new File(archivePath);
            System.out.println("Archive " + archive.getName() + " was succesfully created");
        }
        catch (IOException ioe) {
            System.out.println("Error creating zip file: " + ioe);
        }
    }

    public static void deleteFiles(File[] filesList, String archiveName) {
        for (int i=0; i < filesList.length; i++) {
            if (!filesList[i].getName().equals(archiveName)) {
                if (filesList[i].delete()) {
                    System.out.println("File " + filesList[i].getName() + " was deleted");
                }
            }
        }
    }

}
