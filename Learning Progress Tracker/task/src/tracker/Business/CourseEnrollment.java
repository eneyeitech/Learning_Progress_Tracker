package tracker.Business;

public class CourseEnrollment implements Comparable{
    private String name;
    private int no;

    public CourseEnrollment(String name, int no) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.no, ((CourseEnrollment) o).getNo());
    }
}
