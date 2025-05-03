package package1;




class Person {
 String name;
 int age;

 
 public Person(String name, int age) {
     this.name = name;
     this.age = age;
 }

 
 public void displayInfo() {
     System.out.println("Name: " + name);
     System.out.println("Age: " + age);
 }
}


class Employee extends Person {
 String jobTitle;
 double salary;

 
 public Employee(String name, int age, String jobTitle, double salary) {
     super(name, age); 
     this.jobTitle = jobTitle;
     this.salary = salary;
 }

 
 @Override
 public void displayInfo() {
     super.displayInfo();
     System.out.println("Job Title: " + jobTitle);
     System.out.println("Salary: " + salary);
 }

 
 public void work() {
     System.out.println(name + " is working as a " + jobTitle + ".");
 }
}


class Manager extends Employee {
 int teamSize;

 
 public Manager(String name, int age, String jobTitle, double salary, int teamSize) {
     super(name, age, jobTitle, salary); 
     this.teamSize = teamSize;
 }

 
 @Override
 public void displayInfo() {
     super.displayInfo(); 
     System.out.println("Team Size: " + teamSize);
 }

 
 public void manage() {
     System.out.println(name + " is managing a team of " + teamSize + " people.");
 }
}

public class EmployeeClass {
 public static void main(String[] args) {
     
     Employee emp1 = new Employee("Ram", 30, "Software Developer", 80000);
     Manager mgr1 = new Manager("Krishan", 40, "Project Manager", 100000, 10);

   
     System.out.println("Employee Details:");
     emp1.displayInfo();
     emp1.work();

     System.out.println("Manager Details:");
     mgr1.displayInfo();
     mgr1.work();
     mgr1.manage();
 }
}
