import java.util.*;

class Employee implements Comparable<Employee> {
    private int id;
    private String name;
    private String department;
    private double salary;
    
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public int compareTo(Employee other) {
        int deptCompare = this.department.compareTo(other.department);
        if (deptCompare != 0) return deptCompare;

        int nameCompare = this.name.compareTo(other.name);
        if (nameCompare != 0) return nameCompare;

        return Double.compare(this.salary, other.salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice", "HR", 50000));
        employees.add(new Employee(102, "Bob", "IT", 60000));
        employees.add(new Employee(103, "Charlie", "Finance", 70000));
        employees.add(new Employee(104, "David", "IT", 55000));
        employees.add(new Employee(105, "Eve", "HR", 65000));

        System.out.println(" Sort by Department → Name → Salary ");
        Collections.sort(employees); // uses compareTo
        Iterator<Employee> it1 = employees.iterator();
        while (it1.hasNext()) System.out.println(it1.next());

        System.out.println("Sort by Salary Descending");
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e2.getSalary(), e1.getSalary());
            }
        });
        Iterator<Employee> it2 = employees.iterator();
        while (it2.hasNext()) System.out.println(it2.next());
    }
}
