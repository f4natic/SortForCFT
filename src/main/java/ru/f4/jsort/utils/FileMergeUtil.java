package ru.f4.jsort.utils;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Path;

import static ru.f4.jsort.utils.SimpleComparator.compareTo;

public final class FileMergeUtil {
    private static long id;
    private static Path temp;
    private static BufferedReader fr;
    private static BufferedReader sr;
    private static BufferedWriter writer;

    private static int order = 1;

    @SneakyThrows
    public static void merge(Path dir, Path first, Path second, String sortOrder, String type){
        id++;
        String p= "Merge" + id + ".txt";
        temp = dir.resolve(p);

        if(sortOrder.equals("descending")){
            order = -1;
        }

        fr = new BufferedReader(new FileReader(first.toFile()));
        sr = new BufferedReader(new FileReader(second.toFile()));
        writer = new BufferedWriter(new FileWriter(temp.toFile()));

        String ln1 = fr.readLine();
        String ln2 = sr.readLine();
        if(type.equals("integer")) {
            mergeInteger(ln1, ln2);
        } else {
            mergeString(ln1, ln2);
        }
        fr.close();
        sr.close();
        writer.close();
    }

    private static void mergeString(String ln1, String ln2) throws IOException {
        while(true) {
            if(ln1 != null && ln2 != null) {
                if(compareTo(ln1, ln2) == order) {
                    writer.write(ln2 + "\n");
                    ln2 = sr.readLine();
                }else if(compareTo(ln1, ln2) == (-1 * order)) {
                    writer.write(ln1 + "\n");
                    ln1 = fr.readLine();
                } else {
                    writer.write(ln1 + "\n");
                    writer.write(ln2 + "\n");
                    ln1 = fr.readLine();
                    ln2 = sr.readLine();
                }
            }else {
                if(ln1 == null && ln2 != null) {
                    writer.write(ln2 + "\n");
                    ln2 = sr.readLine();
                }
                if(ln1 != null && ln2 == null) {
                    writer.write(ln1 + "\n");
                    ln1 = fr.readLine();
                }
                if(ln1 == null && ln2 == null) {
                    break;
                }
            }
        }
    }

    private static void mergeInteger(String ln1, String ln2) throws IOException {
        while(true) {
            if(ln1 != null && ln2 != null) {
                if(compareTo(Integer.parseInt(ln1), Integer.parseInt(ln2)) == order) {
                    writer.write(ln2 + "\n");
                    ln2 = sr.readLine();
                }else if(compareTo(Integer.parseInt(ln1), Integer.parseInt(ln2)) == (-1 * order)) {
                    writer.write(ln1 + "\n");
                    ln1 = fr.readLine();
                } else {
                    writer.write(ln1 + "\n");
                    writer.write(ln2 + "\n");
                    ln1 = fr.readLine();
                    ln2 = sr.readLine();
                }
            }else {
                if(ln1 == null && ln2 != null) {
                    writer.write(ln2 + "\n");
                    ln2 = sr.readLine();
                }
                if(ln1 != null && ln2 == null) {
                    writer.write(ln1 + "\n");
                    ln1 = fr.readLine();
                }
                if(ln1 == null && ln2 == null) {
                    break;
                }
            }
        }
    }
}
