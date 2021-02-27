package ru.f4.jsort.utils;

import static ru.f4.jsort.utils.SimpleComparator.compareTo;

/*
    Класс для сортировки массивов данных слиянием
 */

public final class DataSort {
        public static int order = 1; // переменная для типа сортировки (по возрастанию или по убыванию)

        public static String[] sort(String[] array, String sortOrder) {
            if (sortOrder.equals("descending")) {
                order = -1;
            }
            return sort(array); // пробрасываем массив в следующий метод по сортировке и возвращаем уже отсортированный
        }

        public static Integer[] sort(Integer[] array, String sortOrder) {
            if (sortOrder.equals("descending")) {
                order = -1;
            }
            return sort(array);
        }

        // Разделение массива на массивы меньшего размера, пока не получаться массивы с одним элементом
        public static String[] sort(String[] array) {
            if (array == null) return null;

            int size = array.length;
            int half = size / 2;

            if (size < 2) return array;

            String[] left = new String[half];
            String[] right = new String[size - half];
            System.arraycopy(array, 0, left, 0, half);
            System.arraycopy(array, half, right, 0, size - half);

            left = sort(left);
            right = sort(right);

            return merge(left, right);
        }

        public static Integer[] sort(Integer[] array) {
            if (array == null) return null;

            int size = array.length;
            int half = size / 2;

            if (size < 2) return array;

            Integer[] left = new Integer[half];
            Integer[] right = new Integer[size - half];
            System.arraycopy(array, 0, left, 0, half);
            System.arraycopy(array, half, right, 0, size - half);

            left = sort(left);
            right = sort(right);

            return merge(left, right);
        }

        // склеиваем массивы в один
        public static String[] merge(String[] left, String[] right) {
            String[] array = new String[left.length + right.length];
            int l = 0, r = 0;
            for (int i = 0; i < array.length; i++) {
                if (l < left.length && r < right.length) {
                    if (compareTo(left[l], right[r]) == order) {
                        array[i] = right[r++];
                    } else {
                        array[i] = left[l++];
                    }
                } else if (l < left.length) {
                    array[i] = left[l++];
                } else {
                    array[i] = right[r++];
                }
            }
            return array;
        }

        public static Integer[] merge(Integer[] left, Integer[] right) {
            Integer[] array = new Integer[left.length + right.length];
            int l = 0, r = 0;
            for (int i = 0; i < array.length; i++) {
                if (l < left.length && r < right.length) {
                    if (compareTo(left[l], right[r]) == order) {
                        array[i] = right[r++];
                    } else {
                        array[i] = left[l++];
                    }
                } else if (l < left.length) {
                    array[i] = left[l++];
                } else {
                    array[i] = right[r++];
                }
            }
            return array;
        }
    }
