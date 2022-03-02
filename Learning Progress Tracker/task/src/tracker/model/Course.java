package tracker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Course {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    public final String name;
    public final int totalPoints;

    private /* mutable */ int interactions;
    private /* mutable */ int pointsAccumulatedByStudents;
    private /* mutable */ int subscribed;

    private static /* mutable */ int maxActivityScore = 0;
    private static /* mutable */ int minActivityScore = Integer.MAX_VALUE;
    private static /* mutable */ int maxPopularityScore = 0;
    private static /* mutable */ int minPopularityScore = Integer.MAX_VALUE;
    private static /* mutable */ float maxDifficultyScore = 0;
    private static /* mutable */ float minDifficultyScore = Float.MAX_VALUE;


    Course(String name, int totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;

    }

    public static boolean isCourse(String input) {
        return Arrays.stream(Course.values()).anyMatch(course -> course.name.equalsIgnoreCase(input));
    }

    public static Course getByName(String name) {
        return Course.valueOf(name.toUpperCase());
    }

    void updateCourseGeralStats(int points, boolean isNew){
        interactions += points > 0 ? 1 : 0;
        pointsAccumulatedByStudents += Math.max(points, 0);
        subscribed += isNew && points > 0 ? 1 : 0;
        updateScores(getActivityScore(), getPopularityScore(), getDifficultyScore());
    }

    public int getActivityScore(){
        return interactions;
    }

    public int getPopularityScore() {
        return subscribed;
    }

    public float getDifficultyScore() {
        return 100.0f - ((float) pointsAccumulatedByStudents / interactions) ;
    }

    public static Map<String, String> getStatsMap() {
        if(maxPopularityScore == 0) {
            return Map.of(
                    "Most popular", "n/a",
                    "Least popular", "n/a",
                    "Highest activity", "n/a",
                    "Lowest activity", "n/a",
                    "Easiest course", "n/a",
                    "Hardest course", "n/a"
            );
        }

        List<Course> maxPopularityList = new ArrayList<>();
        List<Course> minPopularityList = new ArrayList<>();
        List<Course> maxActivityList = new ArrayList<>();
        List<Course> minActivityList = new ArrayList<>();
        List<Course> maxDifficultyList = new ArrayList<>();
        List<Course> minDifficultyList = new ArrayList<>();

        for(Course course : Course.values()) {
            if(course.getPopularityScore() == maxPopularityScore) {
                maxPopularityList.add(course);
            } else if(course.getPopularityScore() == minPopularityScore) {
                minPopularityList.add(course);
            }
            if(course.getActivityScore() == maxActivityScore) {
                maxActivityList.add(course);
            } else if(course.getActivityScore() == minActivityScore) {
                minActivityList.add(course);
            }
            if(course.getDifficultyScore() == maxDifficultyScore) {
                maxDifficultyList.add(course);
            } else if(course.getDifficultyScore() == minDifficultyScore) {
                minDifficultyList.add(course);
            }
        }

        return Map.of(
                "Most popular", maxPopularityList.stream().map(course -> course.name).collect(Collectors.joining(" ")),
                "Least popular", minPopularityList.stream().map(course -> course.name).collect(Collectors.joining(" ")),
                "Highest activity", maxActivityList.stream().map(course -> course.name).collect(Collectors.joining(" ")),
                "Lowest activity", minActivityList.stream().map(course -> course.name).collect(Collectors.joining(" ")),
                "Easiest course", minDifficultyList.stream().map(course -> course.name).collect(Collectors.joining(" ")),
                "Hardest course", maxDifficultyList.stream().map(course -> course.name).collect(Collectors.joining(" "))
        );
    }

    private static void updateScores(int activityScore, int popularityScore, float difficultyScore) {

        maxActivityScore = Math.max(maxActivityScore, activityScore);
        maxPopularityScore = Math.max(maxPopularityScore, popularityScore);
        maxDifficultyScore = Math.max(maxDifficultyScore, difficultyScore);
        minActivityScore = Math.min(minActivityScore, activityScore);
        minPopularityScore = Math.min(minPopularityScore, popularityScore);
        minDifficultyScore = Math.min(minDifficultyScore, difficultyScore);

    }
}
