package tracker;

import java.util.Locale;
import java.util.Scanner;

public class Tracker {

    private final Scanner scanner;

    public Tracker() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        options();
    }

    public void options() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            String sel = scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (sel) {
                case "exit":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                case "":
                    System.out.println("no input");
                    break;
                default:
                    if (sel.isBlank() || sel.isEmpty()) {
                        System.out.println("no input");
                    } else {
                        System.out.println("Unknown command!");
                    }
                    break;
            }
        }
    }
}
