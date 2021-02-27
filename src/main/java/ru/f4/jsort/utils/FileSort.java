package ru.f4.jsort.utils;

import lombok.SneakyThrows;
import ru.f4.jsort.model.FileUnit;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class FileSort {

    private static int buferSize = 10;
    private static long id;
    private static Path tempDir;

    /*
        Метод принимает файл, потом вызывает метод, который поделит на файлы с данными из буфера
        и потом объединит их в один с отсортированными данными
     */
    @SneakyThrows
    public static void sortFile(FileUnit unit, String order, String type) {
        tempDir = Paths.get("Temp");
        if (!Files.exists(tempDir)) {
            Files.createDirectory(tempDir);
        }
        BufferedReader reader = new BufferedReader(new FileReader(unit.getFilePath().toFile()));

        if (type.equals("string")) {
            readStringFile(reader, order, type);
        }

        if (type.equals("integer")) {
            readIntegerFile(reader, order, type);
        }
        reader.close();
    }

    // Чтение данных из файла и запись их в буфер
    @SneakyThrows
    private static void readIntegerFile(BufferedReader reader, String order, String type) {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("wrongData.txt"), true));
        String line = null;
        Integer[] buffer = new Integer[buferSize];
        int count = 0;
        while ((line = reader.readLine()) != null) {
            if (DataUtils.isInteger(line)) {
                buffer[count] = Integer.parseInt(line);
                if (count == (buferSize - 1)) {
                    sortAndWrite(buffer, order, type);
                    mergeFile(order, type);
                    count = 0;
                    for (int i = 0; i < buffer.length; i++) {
                        buffer[i] = null;
                    }
                    continue;
                }
                count++;
            } else {
                writer.write(line + "\n");
            }
        }
        sortAndWrite(buffer, order, type);
        mergeFile(order, type);
        writer.close();
    }

    @SneakyThrows
    private static void readStringFile(BufferedReader reader, String order, String type) {
        String line = null;
        String[] buffer = new String[buferSize];
        int count = 0;
        while ((line = reader.readLine()) != null) {
            buffer[count] = line;
            if (count == (buferSize - 1)) {
                sortAndWrite(buffer, order, type);
                mergeFile(order, type);
                count = 0;
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = null;
                }
                continue;
            }
            count++;
        }
        sortAndWrite(buffer, order, type);
        mergeFile(order, type);
    }


    // сортировка данных в буфере и запись его в файл
    @SneakyThrows
    public static void sortAndWrite(Integer[] buffer, String order, String type) {

        if (buffer[0] == null || buffer == null) {
            return;
        }
        Path temp = tempDir.resolve("temp" + (id++) + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp.toFile()));
        buffer = removeNull(buffer);
        buffer = DataSort.sort(buffer, order);
        for (Integer integer : buffer) {
            writer.write(integer + "\n");
        }
        writer.close();
    }

    @SneakyThrows
    public static void sortAndWrite(String[] buffer, String order, String type) {

        if(buffer[0] == null || buffer == null) {
            return;
        }
        Path temp = tempDir.resolve("temp" + (id++) + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp.toFile()));
        buffer = DataSort.sort(removeNull(buffer), order);
        for(String line : buffer) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    // удаление null из буфера если он незаполнен
    public static Integer[] removeNull(Integer[] buffer) {
        int size = 0;
        while (size < buferSize) {
            if (buffer[size] == null) {
                break;
            }
            size++;
        }
        Integer[] array = new Integer[size];
        System.arraycopy(buffer, 0, array, 0, size);
        return array;
    }

    public static String[] removeNull(String[] buffer) {
        int size = 0;
        while (size < buferSize) {
            if (buffer[size] == null) {
                break;
            }
            size++;
        }
        String[] array = new String[size];
        System.arraycopy(buffer, 0, array, 0, size);
        return array;
    }

    // Магия =) слияние нескольких файлов в один
    @SneakyThrows
    private static void mergeFile(String order, String type) {
        List<Path> list = null;
        Iterator iterator = null;
        list = Files.list(FileSort.tempDir).collect(Collectors.toList());
        iterator = list.listIterator();
        if(list.size() > 1) {
            while(iterator.hasNext()) {
                Path p1 = (Path)iterator.next();
                if(iterator.hasNext()) {
                    Path p2 = (Path)iterator.next();
                    FileMergeUtil.merge(tempDir, p1, p2, order, type);
                    Files.delete(p1);
                    Files.delete(p2);
                }
            }
        }
    }
}
