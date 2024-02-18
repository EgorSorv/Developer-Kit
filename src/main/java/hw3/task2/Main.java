package hw3.task2;

import static hw3.task2.Compare.compareArrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3, 4};
        Integer[] array2 = {1, 2, 3, 4};
        Integer[] array3 = {4, 3, 2, 1};
        Integer[] array4 = {1, 2, 3};
        String[] array5 = {"1", "2", "3", "4"};

        boolean result1 = compareArrays(array1, array2); // true
        boolean result2 = compareArrays(array1, array3); // true
        boolean result3 = compareArrays(array1, array4); // false
        boolean result4 = compareArrays(array1, array5); // false

        System.out.println(result1 + "\n" + result2 + "\n" + result3 + "\n" + result4);
    }
}
