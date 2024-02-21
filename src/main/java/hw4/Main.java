package hw4;

import java.util.List;

public class Main {
    /*
    Создать справочник сотрудников
    Необходимо:
    Создать класс справочник сотрудников, который содержит внутри
    коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
    Табельный номер
    Номер телефона
    Имя
    Стаж
    Добавить метод, который ищет сотрудника по стажу (может быть список)
    Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
    Добавить метод, который ищет сотрудника по табельному номеру
    Добавить метод добавление нового сотрудника в справочник
     */

    public static void main(String[] args) {
        EmployeeDirectory employeeDirectory = new EmployeeDirectory();

        employeeDirectory.addEmployee(1, "+7(777)777-77-77", "Ivan", 5);
        employeeDirectory.addEmployee(2, "+7(666)666-66-66", "Anna", 10);
        employeeDirectory.addEmployee(3, "+7(555)555-55-55", "Oleg", 3);
        employeeDirectory.addEmployee(4, "+7(444)444-44-44", "Alena", 6);
        employeeDirectory.addEmployee(5, "+7(333)333-33-33", "Peter", 5);
        employeeDirectory.addEmployee(6, "+7(222)222-22-22", "Ivan", 7);
        employeeDirectory.addEmployee(7, "+7(111)111-11-11", "Victor", 20);
        employeeDirectory.addEmployee(8, "+7(888)888-88-88", "Ivan", 8);
        employeeDirectory.addEmployee(9, "+7(999)999-99-99", "Julia", 10);
        employeeDirectory.addEmployee(10, "+7(777)777-00-00", "Elena", 5);

        List<Employee> employees = employeeDirectory.findEmployeeByExperience(5);
        employeeDirectory.print(employees);

        List<String> phoneNumbers = employeeDirectory.findNumberByName("Ivan");
        System.out.println(phoneNumbers + "\n");

        Employee employee = employeeDirectory.findEmployeeByPersonnelNumber(7);
        System.out.println(employee);
    }
}
