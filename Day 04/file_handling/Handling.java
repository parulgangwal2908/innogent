package file_handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Class {
    int id;
    String name;

    Class(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Student {
    int id;
    String name;
    int classId;
    int marks;
    String gender;
    int age;

    Student(int id, String name, int classId, int marks, String gender, int age) {
        this.id = id;
        this.name = name;
        this.classId = classId;
        this.marks = marks;
        this.gender = gender;
        this.age = age;
    }
}

class Address {
    int id;
    int pincode;
    String city;
    int studentId;

    Address(int id, int pincode, String city, int studentId) {
        this.id = id;
        this.pincode = pincode;
        this.city = city;
        this.studentId = studentId;
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}

public class Handling {

    // read file
    static void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(fileName + " not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write classes to file
    static void writeClassesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("classes.txt"))) {
            bw.write("ID,Name");
            bw.newLine();
            for (Class c : classes) {
                bw.write(c.id + "," + c.name);
                bw.newLine();
            }
            System.out.println("Created classes.txt successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write students to file
    static void writeStudentsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("students.txt"))) {
            bw.write("ID,Name,ClassID,Marks,Gender,Age");
            bw.newLine();
            for (Student s : students) {
                bw.write(s.id + "," + s.name + "," + s.classId + "," + s.marks + "," + s.gender + "," + s.age);
                bw.newLine();
            }
            System.out.println("Created students.txt successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write addresses to file
    static void writeAddressesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("addresses.txt"))) {
            bw.write("ID,Pincode,City,StudentID");
            bw.newLine();
            for (Address a : addresses) {
                bw.write(a.id + "," + a.pincode + "," + a.city + "," + a.studentId);
                bw.newLine();
            }
            System.out.println("Created addresses.txt successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // write top 5 students to file
    static void writeTop5ToFile() {
        Collections.sort(students, (a, b) -> b.marks - a.marks);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("top5_students.txt"))) {
            bw.write("Rank,ID,Name,ClassID,Marks,Gender,Age");
            bw.newLine();

            int limit = Math.min(5, students.size());
            for (int i = 0; i < limit; i++) {
                Student s = students.get(i);
                bw.write((i + 1) + "," + s.id + "," + s.name + "," + s.classId + "," + s.marks + "," + s.gender + ","
                        + s.age);
                bw.newLine();
            }

            System.out.println("top5_students.txt successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void deleteStudent(int studentId) throws NotFoundException {
        boolean removed = students.removeIf(s -> s.id == studentId);
        if (!removed) {
            throw new NotFoundException("Student with ID " + studentId + " does not exist.");
        } else {
            System.out.println("Student with ID " + studentId + " deleted successfully.");
        }
    }

    // Delete a class by ID
    static void deleteClass(int classId) throws NotFoundException {
        boolean removed = classes.removeIf(c -> c.id == classId);
        if (!removed) {
            throw new NotFoundException("Class with ID " + classId + " does not exist.");
        } else {
            System.out.println("Class with ID " + classId + " deleted successfully.");
        }
    }

    static List<Class> classes = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static List<Address> addresses = new ArrayList<>();

    static void addStudent(Student s) throws InvalidAgeException, InvalidMarksException {
        if (s.age > 20) {
            throw new InvalidAgeException("Student " + s.name + " not allowed (Age > 20)");
        }
        if (s.marks < 0 || s.marks > 100) {
            throw new InvalidMarksException(s.name + " has invalid marks: " + s.marks);
        }
        students.add(s);
    }

    static void addClass(Class c) {
        classes.add(c);
    }

    static void addAddress(Address a) {
        addresses.add(a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // input classes details
        addClass(new Class(1, "A"));
        addClass(new Class(2, "B"));
        addClass(new Class(3, "C"));
        addClass(new Class(4, "D"));
        System.out.print("Enter number of classes: ");
        int numClasses = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numClasses; i++) {
            System.out.println("Enter details for class " + (i + 1));
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            addClass(new Class(id, name));
        }
        writeClassesToFile();
        readFile("classes.txt");

        // input student details
        try {
            addStudent(new Student(1, "stud1", 1, 88, "F", 10));
            addStudent(new Student(2, "stud2", 1, 70, "F", 11));
            addStudent(new Student(3, "stud3", 2, 88, "M", 12));
            addStudent(new Student(4, "stud4", 2, 55, "M", 13));
            addStudent(new Student(5, "stud5", 1, 30, "F", 14));
            addStudent(new Student(6, "stud6", 3, 30, "F", 13));
            addStudent(new Student(7, "stud7", 3, 10, "F", 12));
            addStudent(new Student(8, "stud8", 3, 0, "M", 11));
        } catch (InvalidAgeException | InvalidMarksException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Enter number of students: ");
        int numStudents = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numStudents; i++) {
            try {
                System.out.println("Enter details for student " + (i + 1));
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Class ID: ");
                int classId = sc.nextInt();
                System.out.print("Marks: ");
                int marks = sc.nextInt();
                sc.nextLine();
                System.out.print("Gender (M/F): ");
                String gender = sc.nextLine();
                System.out.print("Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                addStudent(new Student(id, name, classId, marks, gender, age));
            } catch (InvalidAgeException | InvalidMarksException e) {
                System.out.println(e.getMessage());
            }
        }
        writeStudentsToFile();
        readFile("students.txt");

        writeTop5ToFile();
        readFile("top5_students.txt");

        // input address details
        addAddress(new Address(1, 452002, "Indore", 1));
        addAddress(new Address(2, 422002, "Delhi", 1));
        addAddress(new Address(3, 442002, "Indore", 2));
        addAddress(new Address(4, 462002, "Delhi", 3));
        addAddress(new Address(5, 472002, "Indore", 4));
        addAddress(new Address(6, 452002, "Indore", 5));
        addAddress(new Address(7, 452002, "Delhi", 5));
        addAddress(new Address(8, 482002, "Mumbai", 6));
        addAddress(new Address(9, 482002, "Bhopal", 7));
        addAddress(new Address(10, 482002, "Indore", 8));
        System.out.print("Enter number of addresses: ");
        int numAddresses = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < numAddresses; i++) {
            System.out.println("Enter details for address " + (i + 1));
            System.out.print("ID: ");
            int id = sc.nextInt();
            System.out.print("Pincode: ");
            int pincode = sc.nextInt();
            sc.nextLine();
            System.out.print("City: ");
            String city = sc.nextLine();
            System.out.print("Student ID: ");
            int studentId = sc.nextInt();
            sc.nextLine();

            addAddress(new Address(id, pincode, city, studentId));
        }

        sc.close();

        writeAddressesToFile();
        readFile("addresses.txt");
    }

}
