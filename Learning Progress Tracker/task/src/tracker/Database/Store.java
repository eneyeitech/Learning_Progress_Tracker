package tracker.Database;

import tracker.Business.Student;

import java.util.*;

public class Store {
    private static Store instance;

    private Map<String, Student> studentMap;
    private Map<String, Student> studentMapWithId;

    private Store() {
        studentMap = new HashMap<>();
        studentMapWithId = new TreeMap<>();
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }


    public Student addStudent(Student student) {
        return studentMap.put(student.getEmail(), student);
    }

    public Student findStudent(String email) {
        return studentMap.get(email);
    }

    public boolean studentExist(String email) {
        return studentMap.containsKey(email);
    }

    public List<Student> all() {
        List<Student> students = new ArrayList<>();
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            students.add(entry.getValue());
        }
        return students;
    }

    public Student addStudentWithId(Student student) {
        return studentMapWithId.put(student.getId(), student);
    }

    public Student findStudentWithId(String id) {
        return studentMapWithId.get(id);
    }

    public boolean studentExistWithId(String id) {
        return studentMapWithId.containsKey(id);
    }

    public List<Student> allWithId() {
        List<Student> students = new ArrayList<>();
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            students.add(entry.getValue());
        }
        return students;
    }

    public Student update(Student student){
        studentMap.put(student.getEmail(), student);
        studentMapWithId.put(student.getId(), student);
        return student;
    }

    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }
}

