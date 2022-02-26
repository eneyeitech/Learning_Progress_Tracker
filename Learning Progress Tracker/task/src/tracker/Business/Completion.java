package tracker.Business;

public class Completion implements Comparable{
    private String id;
    private int points;
    private float completed;

    public Completion(String id, int points, float completed) {
        this.id = id;
        this.points = points;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public float getCompleted() {
        return completed;
    }

    public void setCompleted(float completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(Object o) {
        int j = Integer.compare(this.points, ((Completion) o).getPoints());
        if (j == 0) {
            return id.compareTo(((Completion) o).getId());
        }
        return Integer.compare(this.points, ((Completion) o).getPoints());
    }
}
