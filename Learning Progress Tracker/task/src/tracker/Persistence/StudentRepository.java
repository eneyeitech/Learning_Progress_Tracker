package tracker.Persistence;

import tracker.Business.Student;
import tracker.Database.Store;

import java.util.List;

public class StudentRepository implements IStudentRepository{

    private Store store = Store.getInstance();

    @Override
    public Student addStudent(Student student) {
        return store.addStudent(student);
    }

    @Override
    public Student findStudent(String email) {
        return store.findStudent(email);
    }

    @Override
    public boolean studentExist(String email) {
        return store.studentExist(email);
    }

    @Override
    public Student addStudentWithId(Student student) {
        return store.addStudentWithId(student);
    }

    @Override
    public Student findStudentWithId(String id) {
        return store.findStudentWithId(id);
    }

    @Override
    public boolean studentExistWithId(String id) {
        return store.studentExistWithId(id);
    }

    @Override
    public List<Student> allStudents() {
        return store.all();
    }

    @Override
    public List<Student> allStudentsWithId() {
        return store.allWithId();
    }

    @Override
    public Student update(Student student) {
        return store.update(student);
    }
}
