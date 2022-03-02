package tracker;

import tracker.model.Student;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class StudentsRepository {

    public final Collection<Student> studentsRepository =
            new TreeSet<>(Comparator.comparing(a -> a.id));

    public boolean contains(String email) {
        return studentsRepository.stream().anyMatch(student -> student.email.equals(email));
    }

    public boolean add(String name, String lastName, String email){
        if(contains(email)) {
            return false;
        } else {
            studentsRepository.add(new Student(name, lastName, email));
            return true;
        }
    }

    public String listStudentsId() {
        return studentsRepository.stream()
                .map(student -> student.id)
                .collect(Collectors.joining("\n"));
    }

    public Optional<Student> findById(String id) {
        return studentsRepository.stream().filter(student -> student.id.equals(id))
                .findFirst();
    }
}
