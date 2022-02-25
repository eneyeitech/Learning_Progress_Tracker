package tracker.Business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void setTotal() {
        Course course = new Course();
        course.setTotal(2);
        assertEquals(2, course.getTotal());
    }
}