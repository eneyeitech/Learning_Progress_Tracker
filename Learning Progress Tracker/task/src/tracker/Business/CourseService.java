package tracker.Business;

import tracker.Persistence.StudentRepository;

import java.util.*;

public class CourseService {

    private StudentRepository studentRepository;
    private int javaEnrollees;
    private int dsaEnrollees;
    private int databasesEnrollees;
    private int springEnrollees;
    public static boolean equalEnrollment = false;

    public static boolean out = false;

    private int javaTaskCompleted;
    private int dsaTaskCompleted;
    private int databasesTaskCompleted;
    private int springTaskCompleted;
    public static boolean equalTaskCompleted = false;

    private int javaPoints;
    private int dsaPoints;
    private int databasesPoints;
    private int springPoints;
    public static boolean equalPoints = false;

    private List<CourseEnrollment> list;
    private List<TaskCompletion> list1;
    private List<CoursePoint> list2;

    public CourseService() {
        studentRepository = new StudentRepository();

        javaEnrollees = Course.getJavaEnrollee();
        dsaEnrollees = Course.getDsaEnrollee();
        databasesEnrollees = Course.getDatabasesEnrollee();
        springEnrollees = Course.getSpringEnrollee();

        javaTaskCompleted = Course.getJavaTaskCompleted();
        dsaTaskCompleted = Course.getDsaTaskCompleted();
        databasesTaskCompleted = Course.getDatabasesTaskCompleted();
        springTaskCompleted = Course.getSpringTaskCompleted();

        javaPoints = Course.getJavaPoints();
        dsaPoints = Course.getDsaPoints();
        databasesPoints = Course.getDatabasesPoints();
        springPoints = Course.getSpringPoints();

        list = new ArrayList<>();
        if (javaEnrollees > 0) {
            list.add(new CourseEnrollment("Java", javaEnrollees));
        }
        if (dsaEnrollees > 0) {
            list.add(new CourseEnrollment("DSA", dsaEnrollees));
        }
        if (databasesEnrollees > 0) {
            list.add(new CourseEnrollment("Databases", databasesEnrollees));
        }
        if (springEnrollees > 0) {
            list.add(new CourseEnrollment("Spring", springEnrollees));
        }

        if (javaEnrollees > 0 || dsaEnrollees > 0 || databasesEnrollees > 0 || springEnrollees > 0) {
            if (javaEnrollees == dsaEnrollees && dsaEnrollees == databasesEnrollees && databasesEnrollees == springEnrollees) {
                CourseService.equalEnrollment = true;
            }
            out = true;
        }
        if (javaTaskCompleted > 0 || dsaTaskCompleted > 0 || databasesTaskCompleted > 0 || springTaskCompleted > 0) {
            if (javaTaskCompleted == dsaTaskCompleted && dsaTaskCompleted == databasesTaskCompleted && databasesTaskCompleted == springTaskCompleted) {
                CourseService.equalTaskCompleted = true;
            }
            out = true;
        }
        if (javaPoints > 0 || dsaPoints > 0 || databasesPoints > 0 || springPoints > 0) {
            if (javaPoints == dsaPoints && dsaPoints == databasesPoints && databasesPoints == springPoints) {
                CourseService.equalPoints = true;
            }
            out = true;
        }

        list1 = new ArrayList<>();
        if (javaTaskCompleted > 0) {
            list1.add(new TaskCompletion("Java", javaTaskCompleted));
        }
        if (dsaTaskCompleted > 0) {
            list1.add(new TaskCompletion("DSA", dsaTaskCompleted));
        }
        if (databasesTaskCompleted > 0) {
            list1.add(new TaskCompletion("Databases", databasesTaskCompleted));
        }
        if (springTaskCompleted > 0) {
            list1.add(new TaskCompletion("Spring", springTaskCompleted));
        }

        list2 = new ArrayList<>();
        if (javaPoints > 0) {
            float j = (javaPoints/(float)javaTaskCompleted) * 100;
            //System.out.println(javaPoints);
            //System.out.println(javaTaskCompleted);
            //System.out.println(j);
            list2.add(new CoursePoint("Java", j));
        }
        if (dsaPoints > 0) {
            float ds = (dsaPoints/(float)dsaTaskCompleted) * 100;
            //System.out.println(ds);
            list2.add(new CoursePoint("DSA", ds));
        }
        if (databasesPoints > 0) {
            float da = (databasesPoints/(float)databasesTaskCompleted) * 100;
            //System.out.println(da);
            list2.add(new CoursePoint("Databases", da));
        }
        if (springPoints > 0) {
            float s = (springPoints/(float)springTaskCompleted) * 100;
            //System.out.println(s);
            list2.add(new CoursePoint("Spring", s));
        }

        if (CourseService.out) {
            System.out.printf("\nEnrollees: %s %s %s %s\n", javaEnrollees, dsaEnrollees, databasesEnrollees, springEnrollees);
            System.out.printf("\nPoints: %s %s %s %s\n", javaPoints, dsaPoints, databasesPoints, springPoints);
            System.out.printf("\nTask Completed: %s %s %s %s\n", javaTaskCompleted, dsaTaskCompleted, databasesTaskCompleted, springTaskCompleted);
        }

        Comparator c = Collections.reverseOrder();
        Collections.sort(list,c);
        Collections.sort(list1,c);
        Collections.sort(list2, c);
    }

    public String mostPopular() {

        if (list.size() == 0){
            return "n/a";
        }

        if (CourseService.equalEnrollment) {
            int j = 0;
            String n = "";
            for(CourseEnrollment ce: list) {
                j++;
                if (j <= list.size()) {
                    n = n + ce.getName();
                    if (j != list.size()) {
                        n = n + ", ";
                    }
                }
            }
            return n;
        }


        int l = 0;
        String m = "";
        for(CourseEnrollment ce: list) {
            l++;
            if (l != list.size()) {
                m = m + ce.getName();
                if (l != list.size() - 1) {
                    m = m + ", ";
                }
            }
        }
        return m;

    }

    public String leastPopular() {
        if (list.size() == 0){
            return "n/a";
        }
        if (CourseService.equalEnrollment) {
            return "n/a";
        }
        return list.get(list.size() - 1).getName();
    }

    public String highestActivity() {

        if (list1.size() == 0){
            return "n/a";
        }
        if (CourseService.equalTaskCompleted) {
            //return "n/a";
        }
        if (CourseService.equalTaskCompleted) {
            int j = 0;
            String n = "";
            for(TaskCompletion t: list1) {
                j++;
                if (j <= list1.size()) {
                    n = n + t.getName();
                    if (j != list1.size()) {
                        n = n + ", ";
                    }
                }
            }
            return n;
        }
        return list1.get(0).getName();
    }

    public String lowestActivity() {
        if (list1.size() == 0){
            return "n/a";
        }
        if (CourseService.equalTaskCompleted) {
            return "n/a";
        }
        return list1.get(list1.size()-1).getName();
    }

    public String easiestCourse() {
        if (list2.size() == 0){
            return "n/a";
        }
        if (CourseService.equalPoints) {
            return "n/a";
        }
        return list2.get(0).getName();
    }

    public String hardestCourse() {
        if (list2.size() == 0){
            return "n/a";
        }
        if (CourseService.equalPoints) {
            return "n/a";
        }
        return list2.get(list2.size()-1).getName();
    }

}
