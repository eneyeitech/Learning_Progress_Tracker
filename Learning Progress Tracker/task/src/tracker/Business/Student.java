package tracker.Business;

import java.util.HashMap;
import java.util.Map;

public class Student implements Comparable{
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean javaEnrolled;
    private boolean dsaEnrolled;
    private boolean databasesEnrolled;
    private boolean springEnrolled;
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

    public boolean isJavaEnrolled() {
        return javaEnrolled;
    }

    public void setJavaEnrolled(boolean javaEnrolled) {
        this.javaEnrolled = javaEnrolled;
    }

    public boolean isDsaEnrolled() {
        return dsaEnrolled;
    }

    public void setDsaEnrolled(boolean dsaEnrolled) {
        this.dsaEnrolled = dsaEnrolled;
    }

    public boolean isDatabasesEnrolled() {
        return databasesEnrolled;
    }

    public void setDatabasesEnrolled(boolean databasesEnrolled) {
        this.databasesEnrolled = databasesEnrolled;
    }

    public boolean isSpringEnrolled() {
        return springEnrolled;
    }

    public void setSpringEnrolled(boolean springEnrolled) {
        this.springEnrolled = springEnrolled;
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        return this.id.compareTo(((Student) o).getId());
    }
}
