package hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDirectory {
    private final List<Employee> employeeDirectory = new ArrayList<>();

    // метод добавление нового сотрудника в справочник
    public void addEmployee(int personnelNumber, String phoneNumber, String name, int experience) {
        employeeDirectory.add(new Employee(personnelNumber, phoneNumber, name, experience));
    }

    // метод, который ищет сотрудника по стажу (может быть список)
    public List<Employee> findEmployeeByExperience(int experience) {
        List<Employee> employees = new ArrayList<>();

        for (Employee employee: employeeDirectory)
            if (experience == employee.experience())
                employees.add(employee);

        return employees;
    }

    // метод, который возвращает номер телефона сотрудника по имени (может быть список)
    public List<String> findNumberByName(String name) {
        List<String> numbers = new ArrayList<>();

        for (Employee employee: employeeDirectory)
            if (Objects.equals(name, employee.name()))
                numbers.add(employee.phoneNumber());

        return numbers;
    }

    // метод, который ищет сотрудника по табельному номеру
    public Employee findEmployeeByPersonnelNumber(int personnelNumber) {
        Employee employee = null;

        for (Employee employeeInList: employeeDirectory)
            if (personnelNumber == employeeInList.personnelNumber())
                employee = employeeInList;

        return employee;
    }

    public void print(List<Employee> employees) {
        System.out.print("[");

        for (int i = 0; i < employees.size() - 1; i++)
            System.out.println(employees.get(i));

        System.out.println(employees.getLast() + "]\n");
    }
}
