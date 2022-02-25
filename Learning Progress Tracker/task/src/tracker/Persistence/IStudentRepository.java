package tracker.Persistence;

import tracker.Business.Student;

import java.util.List;

public interface IStudentRepository {
    public Student addStudent(Student student);
    public Student findStudent(String email);
    public boolean studentExist(String email);
    public Student addStudentWithId(Student student);
    public Student findStudentWithId(String id);
    public boolean studentExistWithId(String id);
    public List<Student> allStudents();
    public List<Student> allStudentsWithId();
    public Student update(Student student);
}
