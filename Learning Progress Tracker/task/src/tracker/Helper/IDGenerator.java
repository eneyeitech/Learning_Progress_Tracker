package tracker.Helper;

import java.util.Random;

public class IDGenerator {
    private int leftLimit = 48;
    private int rightLimit = 57;
    private int targetStringLength = 5;
    private Random random = new Random(1000000);

    public String generate4Digits() {
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
