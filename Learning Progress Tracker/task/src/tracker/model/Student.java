package tracker.model;

import tracker.UI;

import java.util.HashMap;
import java.util.Map;
import static tracker.model.Course.*;

public class Student {

    static int idSequenceGenerator = 1000000;

    public final String id;
    public final String firstName;
    public final String lastNames;
    public final String email;

    private final Map<Course, TrackCourseStats> courses = new /*mutable*/HashMap<>(
            Map.of(
                    JAVA, new TrackCourseStats(JAVA),
                    DATABASES, new TrackCourseStats(DATABASES),
                    DSA, new TrackCourseStats(DSA),
                    SPRING, new TrackCourseStats(SPRING)
            )
    );

    public Student(String firstName, String lastNames, String email) {
        this.id = String.valueOf(++idSequenceGenerator);
        this.firstName = firstName;
        this.lastNames = lastNames;
        this.email = email;
    }

    public String getStudentPoints(){
        return String.format(UI.Constants.responseStudentsPointsTemplate,
                id,
                JAVA.name, courses.get(JAVA).points,
                DSA.name, courses.get(DSA).points,
                DATABASES.name, courses.get(DATABASES).points,
                SPRING.name, courses.get(SPRING).points
        );
    }

    public TrackCourseStats getCourseTrackerInfo(Course course) {
        return courses.get(course);
    }

    public Map<Course, TrackCourseStats> getCoursesTrackerInfo(Course course) {
        return courses;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    public void updatePoints(int[] points) {
        courses.replace(JAVA, courses.get(JAVA).addInteraction(points[0]));
        courses.replace(DSA, courses.get(DSA).addInteraction(points[1]));
        courses.replace(DATABASES, courses.get(DATABASES).addInteraction(points[2]));
        courses.replace(SPRING, courses.get(SPRING).addInteraction(points[3]));
    }
}
