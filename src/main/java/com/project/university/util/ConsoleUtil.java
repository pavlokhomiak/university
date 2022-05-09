package com.project.university.util;

import com.project.university.model.Department;
import java.util.Scanner;

public interface ConsoleUtil {

    void mainMenu();

    void mainMenu(Scanner scanner);

    void lectorMenu(Scanner scanner);

    void selectDepartment(Scanner scanner);

    void departmentMenu(Department department, Scanner scanner);

    void searchByTemplate(Scanner scanner);

    void headOfDepartment(Department department, Scanner scanner);

    void departmentStatistic(Department department, Scanner scanner);

    void averageSalary(Department department, Scanner scanner);

    void countOfEmployee(Department department, Scanner scanner);

}
