package tracker.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TrackCourseStats {

    public final int points;
    public final int interactions;
    public final Course course;

    public TrackCourseStats(Course course){
        this.points = 0;
        this.interactions = 0;
        this.course = course;
    }

    private TrackCourseStats(int points, int interactions, Course course) {
        this.points = points;
        this.interactions = interactions;
        this.course = course;
    }

    public TrackCourseStats addInteraction(int points){
        course.updateCourseGeralStats(points, this.points == 0);
        return points > 0 ?
                new TrackCourseStats(
                        this.points + points, this.interactions + 1, this.course
                )
                : this;
    }

    public String completedPercentual(){

        final double percentual = BigDecimal.valueOf(100.0)
                .multiply(BigDecimal.valueOf(points))
                .divide(BigDecimal.valueOf(course.totalPoints),1, RoundingMode.HALF_UP)
                .doubleValue();
        return String.format("%.1f%%", percentual);
    }
}
