
package managestudentsapplication;

import java.io.IOException;
import java.util.Scanner;

public class ManageStudentsApplication {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DBContext dbManager = new DBContext();

        while (true) {
            System.out.println("##############################");
            System.out.println("1. Add a student to the list");
            System.out.println("2. Show the students list");
            System.out.println("3. Search for a student");
            System.out.println("4. Edit student information");
            System.out.println("5. Delete a student");
            System.out.println("0. Exit");
            System.out.println("##############################");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addStudent(dbManager, scanner);
                    break;
                case 2:
                    dbManager.displayAllStudents();
                    break;
                case 3:
                    searchStudent(dbManager, scanner);
                    break;
                case 4:
                    editStudent(dbManager, scanner);
                    break;    
                case 5:
                    deleteStudent(dbManager, scanner);
                    break;    
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

 
    public static void addStudent(DBContext dbManager, Scanner scanner) {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's ID: ");
        String id = scanner.nextLine();
        Student student = new Student(name, id);
        dbManager.insert(student);
        System.out.println("Student added successfully.");
    }

    public static void searchStudent(DBContext dbManager, Scanner scanner) {
        System.out.print("Enter student's ID to search: ");
        String id = scanner.nextLine();
        Student student = dbManager.search(id);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println("Name: " + student.getName());
            System.out.println("ID: " + student.getId());
        } else {
            System.out.println("Student not found.");
        }
    }
    
    public static void deleteStudent(DBContext dbManager, Scanner scanner) {
        System.out.print("Enter student's ID to delete: ");
        String id = scanner.nextLine();
        dbManager.delete(id);
    }
    
    public static void editStudent(DBContext dbManager, Scanner scanner) {
        System.out.print("Enter student's ID to edit: ");
        String id = scanner.nextLine();
        Student existingStudent = dbManager.search(id);
        if (existingStudent != null) {
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        existingStudent.setName(newName);
        existingStudent.setId(id);
        dbManager.edit(id, existingStudent);
        } else {
        System.out.println("Student with ID " + id + " does not exist.");
        }
    }
}

    
