package tracker.Business;

public class Course {
    public final static String JAVA = "Java";
    public final static String DSA = "Data Structures and Algorithms (DSA)";
    public final static String DATABASES = "Databases";
    public final static String Spring = "Spring";

    private String courseName;
    private int total = 0;

    public Course() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int point) {
        this.total = getTotal() + point;
    }
}
