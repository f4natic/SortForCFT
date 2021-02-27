package ru.f4.jsort.utils;

/*
    простой компаратор, при А > Б возвращает 1, А < Б - -1, если А и Б равны - 0
 */
public final class SimpleComparator {

    public static int compareTo(int a, int b) {
        if(a > b) return 1;
        if(a < b) return -1;
        return 0;
    }

    //для уменьшения кода, сортируем строки по их длинам, если время будет, то сделаю по символам
    public static int compareTo(String a, String b) {
        if(a.length() > b.length()) return 1;
        if(a.length() < b.length()) return -1;
        return 0;
    }
}