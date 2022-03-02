package tracker.controller;

import tracker.StudentsRepository;
import tracker.UI;
import tracker.model.Course;
import tracker.model.Student;
import tracker.model.TrackCourseStats;

import java.util.*;
import java.util.stream.Collectors;

import static tracker.UI.Constants.*;

public class Controller {

    final StudentsRepository repository;
    public static boolean notified = false;

    public Controller(StudentsRepository repository) {
        this.repository = repository;
    }

    public String processMainInput(String input, UI ui){
        switch (input.trim().toLowerCase()) {
            case commandAddPoints: return ui.addPointsInteraction(true);
            case commandAddStudents: return ui.addStudentsInteraction();
            case commandBack: return responseBack;
            case commandEmpty: return responseEmpty;
            case commandFind: return ui.findInteraction(true);
            case commandExit: return GoodBye;
            case commandListStudents: {
                final String listStudentsId = repository.listStudentsId();
                return listStudentsId.equals("") ?
                        responseListStudentsNoneFound : responseListStudentsHeader + listStudentsId;
            }
            case commandStatistics: return ui.statisticsInteraction(true);
            case commandNotify:
                return ui.notifyInteraction(false);
            default: return responseInvalidCommand;
        }
    }


    public boolean emailIsUnique(String email) {
        return !repository.contains(email);
    }

    public void addStudent(String name, String lastName, String email) {
        final boolean wasSuccessful = repository.add(name, lastName, email);

        if(!wasSuccessful){
            throw new IllegalArgumentException(
                    "email already exists, you should check if email is unique before adding"
            );
        }
    }

    public Optional<Student> findStudentById(String id) {
        return repository.findById(id);
    }

    public void addPoints(String[] inputIdPointsArray, Student student) {
        final int[] points = Arrays.stream(inputIdPointsArray).skip(1)
                .mapToInt(Integer::parseInt)
                .toArray();

        student.updatePoints(points);
    }

    public String getCourseStats(String input) {
        final Course course = Course.getByName(input);

        final String listStudentsAsString = repository.studentsRepository.stream()
                .filter(student -> student.getCourseTrackerInfo(course).points != 0)
                .sorted((a, b) -> {
                    final TrackCourseStats aInfo = a.getCourseTrackerInfo(course);
                    final TrackCourseStats bInfo = b.getCourseTrackerInfo(course);
                    if (aInfo.points != bInfo.points) {
                        return bInfo.points - aInfo.points;
                    } else {
                        return a.id.compareTo(b.id);
                    }
                }).map(student -> {
                    final TrackCourseStats trackerInfo = student.getCourseTrackerInfo(course);
                    return String.format("%s %d %s",
                            student.id, trackerInfo.points, trackerInfo.completedPercentual());
                }).collect(Collectors.joining("\n"));

        return new StringBuilder()
                .append(course.name).append("\n")
                .append("id    points    completed\n")
                .append(listStudentsAsString).toString();
    }

    public String notifyStudent() {
        //final Course course = Course.getByName(input);
        if (Controller.notified) {
            return "Total 0 students have been notified.";
        }
        long count = 0;
        final Set<Student> list = new HashSet<>();
        String listStudentsAsString = "";
        List<Course> coList = List.of(Course.JAVA, Course.DSA, Course.DATABASES, Course.SPRING);
        for (Course c : coList) {
            Course course = Course.getByName(c.name);
            listStudentsAsString = listStudentsAsString + repository.studentsRepository.stream()
                    .filter(student -> student.getCourseTrackerInfo(course).points >= c.totalPoints)
                    .map(student -> {
                        final TrackCourseStats trackerInfo = student.getCourseTrackerInfo(course);
                        return String.format("\nTo: %s" +
                                        "\nRe: Your Learning Progress" +
                                        "\nHello, %s You have accomplished our %s course!",
                                student.email, student.firstName + " "+student.lastNames, c.name);
                    }).collect(Collectors.joining("\n"));


            repository.studentsRepository.stream()
                    .filter(student -> student.getCourseTrackerInfo(course).points >= c.totalPoints)
                    .forEach(student -> {
                        list.add(student);
                    });

        }

        if ("".equalsIgnoreCase(listStudentsAsString)) {
            return "Total 0 students have been notified.";
        }
        //System.out.println(list.size());
        Controller.notified = true;
        return new StringBuilder(listStudentsAsString)
                .append("\nTotal "+ list.size() +" students have been notified.")
                .toString();
    }

    public String getStatisticsReport() {
        final Map<String, String> statsMap = Course.getStatsMap();

        return String.format("Most popular: %s\nLeast popular: %s\nHighest activity: %s\nLowest activity: %s\nEasiest course: %s\nHardest course: %s",
                statsMap.get("Most popular"), statsMap.get("Least popular").isBlank() ? "n/a" : statsMap.get("Least popular"),
                statsMap.get("Highest activity"), statsMap.get("Lowest activity").isBlank() ? "n/a" : statsMap.get("Lowest activity"),
                statsMap.get("Easiest course").isBlank() ? "n/a" : statsMap.get("Easiest course"), statsMap.get("Hardest course")
        );
    }
}
