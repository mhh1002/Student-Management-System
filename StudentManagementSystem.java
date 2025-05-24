import java.util.Scanner;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class StudentManagementSystem {
    public void startStudentSystem() {
        boolean exit = false;
        ArrayList<Student> list = new ArrayList<>();
        System.out.println("-------------Student Management System----------------");
        while (!exit) {
            System.out.println("1：Add Student");
            System.out.println("2：Delete Student's Detail");
            System.out.println("3：Modify Student's Info");
            System.out.println("4：Inquire Student's Info");
            System.out.println("5：Exit");
            System.out.println("Please Enter you option:");

            Scanner input1 = new Scanner(System.in);
            String choice = input1.next();

            switch (choice) {
                case "1":
                    addStudent(list);
                    break;

                case "2":
                    deleteStudent(list);
                    break;

                case "3":
                    modifyStudent(list);
                    break;

                case "4":
                    queryStudent(list);
                    break;

                case "5":
                    System.out.println("Exit");
                    exit = true;
                    break;

                default:
                    System.out.println("Option is Invalid");
            }
        }
    }

    // Add Student to ArrayList
    public static void addStudent(ArrayList<Student> students) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();

        // Set id
        System.out.println("Please enter Id:");
        // check if id is unique or not
        boolean isUnique = false;
        while (!isUnique) {
            String id = scanner.next();
            isUnique = isIdUnique(id, students);
            if (isUnique) {
                student.setId(id);
            } else {
                System.out.println("ID is used. Please enter a unique ID:");
            }
        }

        // Set name
        System.out.println("Please enter Name:");
        String name = scanner.next();
        student.setName(name);

        // Set Age
        System.out.println("Please enter Age:");
        boolean validAge = false;
        while (!validAge) {
            try {
                int age = scanner.nextInt();
                validAge = true;
                student.setAge(age);
            } catch (Exception e) {
                System.out.println("Please enter a valid age:");
            }
        }

        // Set Address
        System.out.println("Please enter Address:");
        String address = scanner.next();
        student.setAddress(address);

        // Add student object
        students.add(student);
        System.out.println("Successfully added a student");
    }

    // Delete Student to ArrayList
    public static void deleteStudent(ArrayList<Student> students) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the ID of the student you want to remove:");
        String id = input.next();

        Student s = idIsInList(id, students);
        if (s != null) {
            students.remove(s);
            System.out.println("Student has been successfully removed from system.\n");
        } else {
            System.out.println("ID doesn't exist, return to main menu.\n\n");
        }
    }

    // Modify Student to ArrayList
    public static void modifyStudent(ArrayList<Student> students) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the ID of the student you want to modify:");
        String id = input.next();

        Student s = idIsInList(id, students);
        if (s != null) {
            System.out.println("Please enter the modified ID you want:");
            id = input.next();
            s.setId(id);
            System.out.println("Student's ID has been successfully modified.\n");
        } else {
            System.out.println("ID doesn't exist, return to main menu.\n");
        }
    }

    // query Student to ArrayList
    public static void queryStudent(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No student is in the system currently. Please add student first before query.");
            return;
        }

        // Print headers
        System.out.printf("%-12s%-12s%-8s%-20s\n", "ID", "Name", "Age", "Address");
        for (Student s : students) {
            System.out.printf("%-12s%-12s%-8d%-20s\n", s.getId(), s.getName(), s.getAge(), s.getAddress());
        }
    }

    public static boolean isIdUnique(String id, ArrayList<Student> students) {
        for (Student s : students) {
            if (id.equals(s.getId())) {
                return false;
            }
        }
        return true;
    }

    public static Student idIsInList(String id, ArrayList<Student> students) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
}