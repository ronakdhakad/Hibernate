package com.skill.main;

import com.skill.config.JPAUtil;
import com.skill.dao.EmployeeDao;
import com.skill.dao.EmployeeSkillDao;
import com.skill.dao.SkillDao;
import com.skill.entity.Employee;
import com.skill.entity.EmployeeSkill;
import com.skill.entity.Skill;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    private static final EmployeeDao employeeDao = new EmployeeDao();
    private static final SkillDao skillDao = new SkillDao();
    private static final EmployeeSkillDao employeeSkillDao = new EmployeeSkillDao();

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  SKILL MANAGEMENT SYSTEM  ");
        System.out.println("=========================================");

        int choice;
        do {
            printMenu();
            choice = readInt("Choose option: ");

            switch (choice) {
                case 1 : addEmployee();break;
                case 2 : viewEmployees();break;
                case 3 : updateEmployee();break;
                case 4 : deleteEmployee();break;

                case 5 : addSkill();break;
                case 6 : viewSkills();break;
                case 7 : updateSkill();break;
                case 8 : deleteSkill();break;

                case 9 : assignSkill();break;
                case 10 : viewEmployeeSkills();break;
                case 11 : reportTopSkills();break;
//                case 12 : searchEmployeesBySkill();break;

                case 12: System.out.println(" Exiting...!");break;
                default : System.out.println(" Invalid option. Try again.");
            }

        } while (choice != 13);

        JPAUtil.shutdown();
    }

    private static void printMenu() {
        System.out.println("\n--------------- MENU ---------------");
        System.out.println("1.  Add Employee");
        System.out.println("2.  View Employees");
        System.out.println("3.  Update Employee");
        System.out.println("4.  Delete Employee");
        System.out.println("5.  Add Skill");
        System.out.println("6.  View Skills");
        System.out.println("7.  Update Skill");
        System.out.println("8.  Delete Skill");
        System.out.println("9.  Assign Skill to Employee");
        System.out.println("10. View Employee Skills");
        System.out.println("11. Report: Top Skills");
//        System.out.println("12. Search Employees by Skill Name");
        System.out.println("12.  Exit");
        System.out.println("------------------------------------");
    }

    // --------- Employee ---------
    private static void addEmployee() {
        System.out.println("\n[Add Employee]");

        String name = readNonEmpty("Enter Name (required): ");
        String email = readNonEmpty("Enter Email (required): ");
        String dept = readNonEmpty("Enter Department (required): ");

        try {
            employeeDao.add(new Employee(name, email, dept));
            System.out.println(" Employee added successfully.");
        } catch (Exception e) {
            System.out.println(" Failed (maybe duplicate email). Error: " + e.getMessage());
        }
    }

    private static void viewEmployees() {
        System.out.println("\n[Employee List]");
        List<Employee> list = employeeDao.findAll();
        if (list.isEmpty()) {
            System.out.println(" No employees found.");
            return;
        }
        list.forEach(e -> System.out.println("• " + e));
    }

    private static void updateEmployee() {
        System.out.println("\n[Update Employee]");
        viewEmployees();

        long id = readLong("Enter Employee ID to update: ");

        String name = readNonEmpty("New Name: ");
        String email = readNonEmpty("New Email: ");
        String dept = readNonEmpty("New Department: ");

        try {
            boolean ok = employeeDao.update(id, name, email, dept);
            System.out.println(ok ? " Employee updated." : " Employee not found.");
        } catch (Exception e) {
            System.out.println(" Update failed. Error: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {
        System.out.println("\n[Delete Employee]");
        viewEmployees();

        long id = readLong("Enter Employee ID to delete: ");
        boolean ok = employeeDao.delete(id);
        System.out.println(ok ? " Employee deleted." : " Employee not found.");
    }

    // --------- Skill ---------
    private static void addSkill() {
        System.out.println("\n[Add Skill]");
        String name = readNonEmpty("Enter Skill Name : ");

        try {
            skillDao.add(new Skill(name));
            System.out.println(" Skill added successfully.");
        } catch (Exception e) {
            System.out.println(" Failed. Error: " + e.getMessage());
        }
    }

    private static void viewSkills() {
        System.out.println("\n[Skill List]");
        List<Skill> list = skillDao.findAll();
        if (list.isEmpty()) {
            System.out.println(" No skills found.");
            return;
        }
        list.forEach(s -> System.out.println("• " + s));
    }

    private static void updateSkill() {
        System.out.println("\n[Update Skill]");
        viewSkills();

        long id = readLong("Enter Skill ID to update: ");
        String name = readNonEmpty("New Skill Name: ");

        try {
            boolean ok = skillDao.update(id, name);
            System.out.println(ok ? " Skill updated." : " Skill not found.");
        } catch (Exception e) {
            System.out.println(" Update failed. Error: " + e.getMessage());
        }
    }

    private static void deleteSkill() {
        System.out.println("\n[Delete Skill]");
        viewSkills();

        long id = readLong("Enter Skill ID to delete: ");
        boolean ok = skillDao.delete(id);
        System.out.println(ok ? " Skill deleted." : " Skill not found.");
    }


    private static void assignSkill() {
        System.out.println("\n[Assign Skill to Employee]");

 
        viewEmployees();
        long empId = readLong("Enter Employee ID: ");
        Employee emp = employeeDao.findById(empId);
        if (emp == null) {
            System.out.println(" Invalid Employee ID.");
            return;
        }

        viewSkills();
        long skillId = readLong("Enter Skill ID: ");
        Skill skill = skillDao.findById(skillId);
        if (skill == null) {
            System.out.println(" Invalid Skill ID.");
            return;
        }

        int prof = readIntRange("Enter Proficiency (1-10): ", 1, 10);
        double years = readDoubleMin("Enter Years of Experience (>=0): ", 0);

        boolean ok = employeeSkillDao.assignOrUpdate(emp, skill, prof, years);
        System.out.println(ok ? " Skill assigned/updated." : " Failed to assign.");
    }

    private static void viewEmployeeSkills() {
        System.out.println("\n[View Employee Skills]");
        viewEmployees();

        long empId = readLong("Enter Employee ID: ");
        List<EmployeeSkill> list = employeeSkillDao.skillsOfEmployee(empId);
        if (list.isEmpty()) {
            System.out.println(" No skills assigned to this employee.");
            return;
        }
        for (EmployeeSkill es : list) {
            System.out.println("• Skill: " + es.getSkill().getName()
                    + " | Proficiency: " + es.getProficiency()
                    + " | Years: " + es.getYears());
        }
    }

//    private static void searchEmployeesBySkill() {
//        System.out.println("\n[Search Employees by Skill Name]");
//        String q = readNonEmpty("Enter skill name contains: ");
//
//        List<Employee> list = employeeSkillDao.searchEmployeesBySkillName(q);
//        if (list.isEmpty()) {
//            System.out.println(" No employees found for skill: " + q);
//            return;
//        }
//        list.forEach(es-> System.out.println("• " + es));
//    }

    private static void reportTopSkills() {
        System.out.println("\n[Report: Top Skills]");
        List<Object[]> rows = employeeSkillDao.topSkills();
        if (rows.isEmpty()) {
            System.out.println(" No skill assignments found yet.");
            return;
        }
        System.out.println("Skill Name | Employee Count");
        System.out.println("---------------------------");
        for (Object[] r : rows) {
            System.out.println(r[0] + " | " + r[1]);
        }
    }

    private static String readLine(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static String readNonEmpty(String msg) {
        while (true) {
            String v = readLine(msg);
            if (!v.isEmpty()) return v;
            System.out.println(" This field is required. Please enter value.");
        }
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                String s = readLine(msg);
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.println(" Enter a valid integer.");
            }
        }
    }

    private static int readIntRange(String msg, int min, int max) {
        while (true) {
            int v = readInt(msg);
            if (v >= min && v <= max) return v;
            System.out.println(" Value must be between " + min + " and " + max + ".");
        }
    }

    private static long readLong(String msg) {
        while (true) {
            try {
                String s = readLine(msg);
                return Long.parseLong(s);
            } catch (Exception e) {
                System.out.println(" Enter a valid number.");
            }
        }
    }

    private static double readDoubleMin(String msg, double min) {
        while (true) {
            try {
                String s = readLine(msg);
                double v = Double.parseDouble(s);
                if (v >= min) return v;
                System.out.println(" Value must be >= " + min);
            } catch (Exception e) {
                System.out.println(" Enter a valid decimal number.");
            }
        }
    }
}
