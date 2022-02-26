package tracker.Business;

public class CoursePoint implements Comparable{

    private String name;
    private double no;

    public CoursePoint(String name, double no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNo() {
        return no;
    }

    public void setNo(double no) {
        this.no = no;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(this.no, ((CoursePoint) o).getNo());
    }
}
