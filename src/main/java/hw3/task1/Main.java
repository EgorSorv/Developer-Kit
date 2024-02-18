package hw3.task1;

import static hw3.task1.Calculator.*;

public class Main {
    public static void main(String[] args) {
        double sum = sum(1, 21);
        double multiply = multiply(40, 1);
        double divide = divide(30.15, 15);
        double subtract = subtract(2, -2);

        System.out.println(sum + "\n" + multiply + "\n" + divide + "\n" + subtract);
    }
}
