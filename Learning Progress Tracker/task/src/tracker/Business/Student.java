package tracker.Business;

import java.util.HashMap;
import java.util.Map;

public class Student implements Comparable{
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Map<String, Course> courses;

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Course> courses) {
        this.courses = new HashMap<>(courses);
    }


    @Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        return this.id.compareTo(((Student) o).getId());
    }
}
