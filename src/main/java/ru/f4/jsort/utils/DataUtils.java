package ru.f4.jsort.utils;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class DataUtils {

    public static boolean isInteger(String line) {
        try{
            Integer.parseInt(line);
        }catch (NumberFormatException ex){
            return false;
        }
        return  true;
    }

    @SneakyThrows
    public static void moveAndDeleteTemp(Path src, Path target) {
        List<Path> list = Files.list(src).collect(Collectors.toList());
        Iterator<Path> iterator = list.iterator();
        if(iterator.hasNext()){
            Path p = iterator.next();
            Files.move(p, target, StandardCopyOption.REPLACE_EXISTING);
        }
        Files.delete(src);
    }
}
