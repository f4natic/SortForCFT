package ru.f4.jsort.model;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
    Класс для хранения ссылки на файл, который может сам себя сортировать
 */

@Data
public class FileUnit {
    private boolean exist; // если файл существует то true, иначе false. Понадобится для списка файлов
    private Path filePath;

    public FileUnit(String fileName) {
        filePath = Paths.get(fileName);

        if(Files.exists(filePath)) {
            exist = true;
        } else {
            exist = false;
        }
    }
}
