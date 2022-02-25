package tracker.Business;

import tracker.Persistence.StudentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {

    private StudentRepository studentRepository;


    public StudentService() {
        studentRepository = new StudentRepository();

    }

    public Student createStudent(String id, String fName, String lName, String email) {
        Student student = new Student();
        if (fName.isBlank() || fName.isEmpty()) {
            return null;
        }
        if (lName.isBlank() || lName.isEmpty()) {
            return null;
        }
        if (email.isBlank() || email.isEmpty()) {
            return null;
        }
        student.setId(id);
        student.setFirstName(fName);
        student.setLastName(lName);
        student.setEmail(email);
        Course course1 = new Course();
        Course course2 = new Course();
        Course course3 = new Course();
        Course course4 = new Course();
        course1.setCourseName(Course.JAVA);
        course2.setCourseName(Course.DSA);
        course3.setCourseName(Course.DATABASES);
        course4.setCourseName(Course.Spring);
        Map<String, Course> courses = new HashMap<>();
        courses.put(course1.getCourseName(), course1);
        courses.put(course2.getCourseName(), course2);
        courses.put(course3.getCourseName(), course3);
        courses.put(course4.getCourseName(), course4);
        student.setCourses(courses);

        return student;
    }



    public Student save(Student student) {
        studentRepository.addStudentWithId(student);
        return studentRepository.addStudent(student);
    }

    public Student findByEmail(String email) {
        return studentRepository.findStudent(email);
    }

    public Student findById(String id) {
        return studentRepository.findStudentWithId(id);
    }

    public boolean studentExistWithEmail(String email) {
        return studentRepository.studentExist(email);
    }

    public boolean studentExistWithId(String id) {
        return studentRepository.studentExistWithId(id);
    }

    public List<Student> allStudents() {
        return studentRepository.allStudents();
    }

    public void updateCoursePoints(Student student, List<Integer> points) {
        Map<String, Course> courses = new HashMap<>(student.getCourses());
        courses.get(Course.JAVA).setTotal(points.get(0));
        courses.get(Course.DSA).setTotal(points.get(1));
        courses.get(Course.DATABASES).setTotal(points.get(2));
        courses.get(Course.Spring).setTotal(points.get(3));
        studentRepository.update(student);
    }
}
