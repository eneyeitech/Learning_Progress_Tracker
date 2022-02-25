package tracker;

import tracker.Business.Course;
import tracker.Business.Student;
import tracker.Business.StudentService;
import tracker.Helper.IDGenerator;
import tracker.Persistence.StudentRepository;

import java.util.*;

public class Tracker {

    private final Scanner scanner;
    private List<Student> studentList;
    private final StudentService studentService;
    private final IDGenerator idGenerator;

    public Tracker() {
        this.scanner = new Scanner(System.in);
        studentList = new ArrayList<>();
        studentService = new StudentService();
        idGenerator = new IDGenerator();
    }

    public void run() {
        options();
    }

    public void options() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            String sel = scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (sel) {
                case "exit":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case "list":

                    Set<Student> studentList = new TreeSet<>(studentService.allStudents());
                    if (studentList.size() > 0) {
                        System.out.println("Students:");
                        for (Student s: studentList) {
                            System.out.println(s.getId());
                        }
                    } else {
                        System.out.println("No students found");
                    }
                    break;
                case "find":
                    System.out.println("Enter an id or 'back' to return");
                    int xy = 1;
                    do {
                        String string = scanner.nextLine();
                        if (string.equalsIgnoreCase("back")) {
                            xy = 0;
                        } else {
                            if (!studentService.studentExistWithId(string)) {
                                System.out.printf("\nNo student is found for id=%s\n", string);
                            } else {
                                Student foundStudent = studentService.findById(string);
                                Map<String, Course> courses = new HashMap<>(foundStudent.getCourses());
                                Course java = courses.get(Course.JAVA);
                                Course dsa = courses.get(Course.DSA);
                                Course databases = courses.get(Course.DATABASES);
                                Course spring = courses.get(Course.Spring);
                                System.out.printf("\n%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", string, java.getTotal(), dsa.getTotal(), databases.getTotal(), spring.getTotal());
                            }
                        }
                    } while (xy != 0);
                    break;
                case "add points":
                    System.out.println("Enter an id and points or 'back' to return");
                    int xx = 1;
                    do {
                        String string = scanner.nextLine();
                        if (string.equalsIgnoreCase("back")) {
                            xx = 0;
                        } else {
                            String[] input = string.split(" ");
                            if (input.length == 5) {
                                String id = input[0];


                                try {
                                    int p1 = Integer.parseInt(input[1]);
                                    int p2 = Integer.parseInt(input[2]);
                                    int p3 = Integer.parseInt(input[3]);
                                    int p4 = Integer.parseInt(input[4]);
                                    if (!studentService.studentExistWithId(id)) {
                                        System.out.printf("\nNo student is found for id=%s\n", id);
                                    } else {
                                        if(p1 < 0 || p2 < 0 || p3 < 0 || p4 < 0 ) {
                                            System.out.println("Incorrect points format");
                                        } else {
                                            Student foundStudent = studentService.findById(id);
                                            List<Integer> points = List.of(p1, p2, p3, p4);
                                            studentService.updateCoursePoints(foundStudent, points);
                                            System.out.println("Points updated");
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Incorrect points format");
                                }


                            } else {
                                System.out.println("Incorrect points format");
                            }
                        }

                    } while (xx != 0);
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program");
                    break;
                case "":
                    System.out.println("no input");
                    break;
                case "add students":
                    System.out.println("Enter student credentials or 'back' to return:");
                    int ex = 1;
                    do {
                        String string = scanner.nextLine();
                        //System.out.println(string);
                        String[] input = string.split(" ");
                        if (input.length >= 3) {
                            String firstName = input[0];
                            String lastName = "";
                            String email = input[input.length-1];
                            for (int i = 0; i < input.length; i++) {
                                if (i != 0 && i != input.length - 1) {
                                    lastName = lastName + input[i] + " ";
                                }
                            }
                            lastName = lastName.strip();
                            boolean valid = true;
                            if (firstName.length() <= 1 || !firstName.matches("[a-zA-Z'\\-]*") || firstName.startsWith("'") || firstName.startsWith("-") || firstName.endsWith("-") || firstName.endsWith("'") || firstName.contains("'-") || firstName.contains("-'") || firstName.contains("--") || firstName.contains("''")){
                                System.out.println("Incorrect first name.");
                                valid = false;
                            }
                            if (lastName.length() <= 1 || !lastName.matches("[a-zA-Z'\\- ]*") || lastName.startsWith("'") || lastName.startsWith("-") || lastName.endsWith("-") || lastName.endsWith("'") || lastName.contains("'-") || lastName.contains("-'") || lastName.contains("--") || lastName.contains("''")){
                                System.out.println("Incorrect last name.");
                                valid = false;
                                //System.out.println(lastName);
                            }

                            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]+$")) {
                                System.out.println("Incorrect email.");
                                valid = false;
                            }


                            if (valid){
                                String id = idGenerator.generate4Digits();
                                //System.out.println(id);
                                if (studentService.studentExistWithEmail(email)) {
                                    System.out.println("This email is already taken.");
                                } else {
                                    Student createdStudent = studentService.createStudent(id, firstName, lastName, email);
                                    if (createdStudent != null) {
                                        add(createdStudent);
                                        System.out.println("The student has been added.");
                                    }
                                }

                            }

                        } else {
                            if (input.length == 1 && string.equalsIgnoreCase("back")) {
                                int noOfStudent = addAfterExit();
                                System.out.printf("\nTotal %s students have been added.\n", noOfStudent);

                                ex = 0;
                            } else {
                                System.out.println("Incorrect credentials.");
                            }
                        }

                    } while (ex != 0);
                    break;
                default:
                    if (sel.isBlank() || sel.isEmpty()) {
                        System.out.println("no input");
                    } else {
                        System.out.println("Unknown command!");
                    }
                    break;
            }
        }
    }

    public void add(Student s){
        studentList.add(s);
        studentService.save(s);
    }

    public int addAfterExit() {
        for (Student s: studentList) {
            //studentService.save(s);
        }
        int total = studentList.size();
        studentList = new ArrayList<>();
        return total;
    }
}
