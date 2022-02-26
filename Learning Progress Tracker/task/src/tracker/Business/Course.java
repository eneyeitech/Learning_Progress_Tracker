package tracker.Business;

public class Course {
    public final static String JAVA = "Java";
    public final static String DSA = "Data Structures and Algorithms (DSA)";
    public final static String DATABASES = "Databases";
    public final static String Spring = "Spring";

    private static int javaEnrollee = 0;
    private static int dsaEnrollee = 0;
    private static int databasesEnrollee = 0;
    private static int springEnrollee = 0;

    private static int javaPoints = 0;
    private static int dsaPoints = 0;
    private static int databasesPoints = 0;
    private static int springPoints = 0;

    private static int javaTaskCompleted = 0;
    private static int dsaTaskCompleted = 0;
    private static int databasesTaskCompleted = 0;
    private static int springTaskCompleted = 0;

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

    public static int getJavaEnrollee() {
        return javaEnrollee;
    }

    public static void setJavaEnrollee(int javaEnrollee) {
        Course.javaEnrollee = Course.javaEnrollee + javaEnrollee;
    }

    public static int getDsaEnrollee() {
        return dsaEnrollee;
    }

    public static void setDsaEnrollee(int dsaEnrollee) {
        Course.dsaEnrollee = Course.dsaEnrollee + dsaEnrollee;
    }

    public static int getDatabasesEnrollee() {
        return databasesEnrollee;
    }

    public static void setDatabasesEnrollee(int databasesEnrollee) {
        Course.databasesEnrollee = Course.databasesEnrollee + databasesEnrollee;
    }

    public static int getSpringEnrollee() {
        return springEnrollee;
    }

    public static void setSpringEnrollee(int springEnrollee) {
        Course.springEnrollee = Course.springEnrollee + springEnrollee;
    }

    public static int getJavaTaskCompleted() {
        return javaTaskCompleted;
    }

    public static void setJavaTaskCompleted(int javaTaskCompleted) {
        Course.javaTaskCompleted = Course.javaTaskCompleted + javaTaskCompleted;
    }

    public static int getDsaTaskCompleted() {
        return dsaTaskCompleted;
    }

    public static void setDsaTaskCompleted(int dsaTaskCompleted) {
        Course.dsaTaskCompleted = Course.dsaTaskCompleted + dsaTaskCompleted;
    }

    public static int getDatabasesTaskCompleted() {
        return databasesTaskCompleted;
    }

    public static void setDatabasesTaskCompleted(int databasesTaskCompleted) {
        Course.databasesTaskCompleted = Course.databasesTaskCompleted + databasesTaskCompleted;
    }

    public static int getSpringTaskCompleted() {
        return springTaskCompleted;
    }

    public static void setSpringTaskCompleted(int springTaskCompleted) {
        Course.springTaskCompleted = Course.springTaskCompleted +  springTaskCompleted;
    }

    public static int getJavaPoints() {
        return javaPoints;
    }

    public static void setJavaPoints(int javaPoints) {
        Course.javaPoints = Course.javaPoints + javaPoints;
    }

    public static int getDsaPoints() {
        return dsaPoints;
    }

    public static void setDsaPoints(int dsaPoints) {
        Course.dsaPoints = Course.dsaPoints + dsaPoints;
    }

    public static int getDatabasesPoints() {
        return databasesPoints;
    }

    public static void setDatabasesPoints(int databasesPoints) {
        Course.databasesPoints = Course.databasesPoints + databasesPoints;
    }

    public static int getSpringPoints() {
        return springPoints;
    }

    public static void setSpringPoints(int springPoints) {
        Course.springPoints = Course.springPoints + springPoints;
    }
}
