package com.project.university.util;

import com.project.university.model.Degree;
import com.project.university.model.Department;
import com.project.university.model.DepartmentType;
import com.project.university.model.Lector;
import com.project.university.service.DepartmentService;
import com.project.university.service.LectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsoleUtilImpl implements ConsoleUtil, CommandLineRunner {

    private final LectorService lectorService;
    private final DepartmentService departmentService;

    @Override
    public void run(String... args) throws Exception {
        mainMenu();
    }

    @Override
    public void mainMenu() {
        mainMenu(new Scanner(System.in));
    }

    @Override
    public void mainMenu(Scanner scanner) {
        System.out.println("Welcome to university control console.\n"
            + "1 - Lectors\n"
            + "2 - Departments\n"
            + "Eny key - Exit");
        String command = scanner.nextLine();
        switch (command) {
            case ("1"):
                lectorMenu(scanner);
            case ("2"):
                selectDepartment(scanner);
            default:
                System.exit(0);
        }
    }

    @Override
    public void lectorMenu(Scanner scanner) {
        System.out.println("Lector menu:\n"
            + "1 - Search by template\n"
            + "Eny key - To main menu");
        String command = scanner.nextLine();
        switch (command) {
            case ("1"):
                searchByTemplate(scanner);
            default:
                mainMenu(scanner);
        }
    }

    @Override
    public void searchByTemplate(Scanner scanner) {
        System.out.println("Enter template:\n"
            + "1 - Lector menu");
        String command = scanner.nextLine();
        switch (command) {
            case ("1"):
                lectorMenu(scanner);
            default:
                getLector(command, scanner);
        }
    }

    private void getLector(String template, Scanner scanner) {
        List<Lector> result = lectorService.findLectorsByFirstNameContainingAndLastNameContaining(template);
        if (result.isEmpty()) {
            System.out.println("No lectors founded for this template.");
        } else {
            result.forEach(lector -> System.out.println(lector.getFirstName() + " " + lector.getLastName()));
        }
        searchByTemplate(scanner);
    }

    @Override
    public void selectDepartment(Scanner scanner) {
        System.out.println("Choose department name:");
        Arrays.stream(DepartmentType.values()).forEach(System.out::println);
        System.out.println("1 - To main menu");
        String command = scanner.nextLine();
        switch (command) {
            case ("1"):
                mainMenu(scanner);
            default:
                findDepartment(command, scanner);
        }
    }

    private void findDepartment(String departmentName, Scanner scanner) {
        DepartmentType departmentType = null;
        try {
            departmentType = DepartmentType.valueOf(departmentName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("No department with this name!!!");
            selectDepartment(scanner);
        }
        departmentService.findDepartmentByType(departmentType).ifPresentOrElse(department ->
            departmentMenu(department, scanner),
            () -> {
                System.out.println("No department with this name!!!");
                selectDepartment(scanner);
            });
    }

    @Override
    public void departmentMenu(Department department, Scanner scanner) {
        System.out.println(department.getType() + " department menu:\n"
            + "1 - Head of department\n"
            + "2 - Department statistic\n"
            + "3 - Average salary\n"
            + "4 - Count of employee\n"
            + "Eny key - To main menu");
        String command = scanner.nextLine();
        switch (command) {
            case ("1"):
                headOfDepartment(department, scanner);
            case ("2"):
                departmentStatistic(department, scanner);
            case ("3"):
                averageSalary(department, scanner);
            case ("4"):
                countOfEmployee(department, scanner);
            default:
                mainMenu(scanner);
        }
    }

    @Override
    public void headOfDepartment(Department department, Scanner scanner) {
        Lector head = department.getHead();
        System.out.println(head.getFirstName() + " " + head.getLastName());
        departmentMenu(department, scanner);
    }

    @Override
    public void departmentStatistic(Department department, Scanner scanner) {
        Map<Degree, Long> resultMap = department.getLectors().stream()
            .collect(Collectors.groupingBy(Lector::getDegree, Collectors.counting()));
        Arrays.stream(Degree.values()).forEach(degree -> System.out.println(degree + " - " + getCount(resultMap.get(degree))));
        departmentMenu(department, scanner);
    }

    private Long getCount(Long count) {
        return count == null ? 0 : count;
    }

    @Override
    public void averageSalary(Department department, Scanner scanner) {
        department.getLectors().stream()
            .map(Lector::getSalary)
            .mapToInt(BigInteger::intValue)
            .average()
            .ifPresentOrElse(value ->
                System.out.println("The average salary of " + department.getType() + " is " +
                    new BigDecimal(value).setScale(2, RoundingMode.DOWN)),
                () -> System.out.println("No data"));
        departmentMenu(department, scanner);
    }

    @Override
    public void countOfEmployee(Department department, Scanner scanner) {
        int size = department.getLectors().size();
        System.out.println(size);
        departmentMenu(department, scanner);
    }
}
