/*Design a Java application to simulate a university’s student course registration 
system. 

This system should:
 - Register Undergraduate and Postgraduate students
 - Enforce course registration limits based on student type
 - Throw exceptions when course limits are exceeded
 - Sort and display student records
 - Demonstrate key OOP principles such as inheritance, abstract classes, interfaces, 
 - custom exceptions, encapsulation, and comparators

Implement the following:
--------------------------
✅ Interface: Printable
	Method: void printDetails()

✅ Class: Course
	Fields: code, title, credits
	Constructor and toString() → returns "code - title"

✅ Abstract Class: Student implements Printable
	Fields: name, id, List<Course>
	Abstract Method: int getMaxCourses()
	Method: registerCourse(Course) → throws CourseLimitExceededException 
	              if the limit is exceeded
	toString() → returns "name (id)"
	printDetails() → prints student info and registered courses

✅ Class: Undergraduate extends Student
	Method: 
		- getMaxCourses() returns 3

✅ Class: Postgraduate extends Student
	Method: 
		- getMaxCourses() returns 2

✅ Class: CourseLimitExceededException extends Exception
	Custom exception with a message

✅ Class: StudentNameComparator implements Comparator<Student>
	Sorts students alphabetically by name (case-insensitive)

✅ Class: University implements Printable
	Fields: name, List<Student>
	Methods:
		- addStudent(Student)
		- printAllStudents()
		- printSortedStudentsByName() → uses StudentNameComparator
 
Sample Input:
-------------
Tech University
1
UG Alice 101 4
CS101 DataStructures 4
CS102 AI 3
CS103 OS 4
CS104 DBMS 3


Sample Output:
-------------
Alice cannot register for more than 3 courses.
All students:
Alice (101)
  Registered: CS101 - DataStructures
  Registered: CS102 - AI
  Registered: CS103 - OS
Sorted students by name:
Alice (101)
  Registered: CS101 - DataStructures
  Registered: CS102 - AI
  Registered: CS103 - OS */
import java.util.*;

// 🚫 DO NOT MODIFY THIS MAIN CLASS
public class UniversityApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String universityName = sc.nextLine();
        int studentCount = Integer.parseInt(sc.nextLine());

        University university = new University(universityName);

        for (int i = 0; i < studentCount; i++) {
            String[] studentInfo = sc.nextLine().split(" ");
            String type = studentInfo[0];
            String name = studentInfo[1];
            int id = Integer.parseInt(studentInfo[2]);
            int courseCount = Integer.parseInt(studentInfo[3]);

            Student student = null;
            if (type.equalsIgnoreCase("UG")) {
                student = new Undergraduate(name, id);
            } else if (type.equalsIgnoreCase("PG")) {
                student = new Postgraduate(name, id);
            }

            for (int j = 0; j < courseCount; j++) {
                String[] courseInfo = sc.nextLine().split(" ");
                Course c = new Course(courseInfo[0], courseInfo[1], Integer.parseInt(courseInfo[2]));
                try {
                    student.registerCourse(c);
                } catch (CourseLimitExceededException e) {
                    System.out.println(e.getMessage());
                }
            }

            university.addStudent(student);
        }

        System.out.println("All students:");
        university.printAllStudents();

        System.out.println("\nSorted students by name:");
        university.printSortedStudentsByName();
    }
}

// Define the Printable interface with one method: void printDetails()
interface Printable {
    // void printDetails();
    void printDetails();

}


// Implement class Course with fields: code, title, credits
// Add constructor and override toString() to return "code - title"
class Course {
    // Declare fields and constructor
    //  Override toString()
    private String code;
    private String title;
    private int credits;
    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }
    @Override
    public String toString() {
        return code + " - " + title;
    }
}

// Create abstract class Student implementing Printable
// Include: name, id, list of courses, abstract int getMaxCourses()
// Method: registerCourse(Course) throws CourseLimitExceededException
// Override toString() to return "name (id)"
// Implement printDetails()
abstract class Student implements Printable {
    protected String name;
    protected int id;
    protected List<Course> courses;
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.courses = new ArrayList<>();
    }
    public abstract int getMaxCourses();
    public void registerCourse(Course course) throws CourseLimitExceededException {
        if (courses.size() >= getMaxCourses()) {
            throw new CourseLimitExceededException(name + " cannot register for more than " + getMaxCourses() + " courses.");
        }
        courses.add(course);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
    @Override
    public void printDetails() {
        System.out.println(this);
        if (courses.isEmpty()) {
            System.out.println("  No courses registered.");
        } else {
            for (Course course : courses) {
                System.out.println("  Registered: " + course);
            }
        }
    }

}

//  Implement Undergraduate class extending Student
// getMaxCourses() should return 3
class Undergraduate extends Student {
    public Undergraduate(String name, int id) {
        super(name, id);
    }  
    @Override
    public int getMaxCourses() {
        return 3;
    }

}

// Implement Postgraduate class extending Student
// getMaxCourses() should return 2
class Postgraduate extends Student {
    public Postgraduate(String name, int id) {
        super(name, id);
    }

    @Override
    public int getMaxCourses() {
        return 2;
    }
}

// Define custom exception class CourseLimitExceededException
class CourseLimitExceededException extends Exception {
    // Constructor accepting message
    public CourseLimitExceededException(String message) {
        super(message);
    }

}

// Implement Comparator<Student> to sort by student name
class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareToIgnoreCase(s2.name);
    }
}

// Implement University class implementing Printable
// Fields: name, list of students
// Methods: addStudent, printAllStudents, printSortedStudentsByName
class University implements Printable {
    String name;
    List<Student> students;
    public University(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    @Override
    public void printDetails() {
        System.out.println("University: " + name);
    }
    public void printAllStudents() {
        for (Student student : students) {
            student.printDetails();
        }
    }
    public void printSortedStudentsByName() {
        Collections.sort(students, new StudentNameComparator());
        for (Student student : students) {
            student.printDetails();
        }
    }
}
