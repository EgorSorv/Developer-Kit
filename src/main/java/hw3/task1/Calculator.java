package hw3.task1;

public class Calculator {
    /*
    Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
    sum(), multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа,
    над которыми должна быть произведена операция.
    */

   public static <T extends Number> double sum(T number1, T number2) {
        return number1.doubleValue() + number2.doubleValue();
   }

    public static <T extends Number> double multiply(T number1, T number2) {
        return number1.doubleValue() * number2.doubleValue();
    }

    public static <T extends Number> double divide(T number1, T number2) {
        if (number2.doubleValue() == 0)
            throw new ArithmeticException("Division by zero is not permitted");

        return number1.doubleValue() / number2.doubleValue();
    }

    public static <T extends Number> double subtract(T number1, T number2) {
        return number1.doubleValue() - number2.doubleValue();
    }
}
