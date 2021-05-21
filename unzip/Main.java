package hwjavacore.hw313inoutput.unzip;

import hwjavacore.hw313inoutput.tozip.GameProgress;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String gamesPath = "/Users/alena/Games/";   //исходный путь к папке Games (ввести свой)
        String savegamesPath = gamesPath + "savegames/";
        String zipPath = savegamesPath + "mySaveGames.zip";    //путь к архиву

        //Произвести распаковку архива в папке savegames.
        openZip(zipPath, savegamesPath);

        //Произвести считывание и десериализацию одного из разархивированных файлов save.dat.
        File savegamesFiles = new File(savegamesPath);
        for (int i = 0; i < savegamesFiles.listFiles().length; i++){
            if (savegamesFiles.listFiles()[i].getName().contains("dat")) {
                try (FileInputStream fis = new FileInputStream(savegamesFiles.listFiles()[i]); ObjectInputStream ois = new ObjectInputStream(fis)) {
                    // десериализуем объект и скастим его в класс
                    GameProgress gameProgress = (GameProgress) ois.readObject();
        //Вывести в консоль состояние сохранненой игры.
                    System.out.println(gameProgress.toString());
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            }
        }
    }

    public static void openZip(String zipPath, String destinationFolder){
        File directory = new File(destinationFolder);
        File zipFile = new File(zipPath);
        // if the output directory doesn't exist, create it
        if(!directory.exists())
            directory.mkdirs();

        byte[] buffer = new byte[2048];

        try {
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zipInput = new ZipInputStream(fis);

            ZipEntry entry = zipInput.getNextEntry();

            while(entry != null){
                String entryName = entry.getName();
                File file = new File(destinationFolder + File.separator + entryName);

                System.out.println("Unzip file " + entryName + " to " + file.getAbsolutePath());

                FileOutputStream fos = new FileOutputStream(file);
                int count = 0;
                while ((count = zipInput.read(buffer)) > 0) {
                    // write 'count' bytes to the file output stream
                    fos.write(buffer, 0, count);
                }
                fos.close();
                // close ZipEntry and take the next one
                zipInput.closeEntry();
                entry = zipInput.getNextEntry();
            }

            // close the last ZipEntry
            zipInput.closeEntry();
            zipInput.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


