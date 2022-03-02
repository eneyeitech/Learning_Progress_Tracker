package tracker;

import tracker.controller.Controller;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Tracker tracker = new Tracker();
        //tracker.run();
        //finished 1

        final Scanner scanner = new Scanner(System.in);

        final StudentsRepository repository = new StudentsRepository();
        final Controller controller = new Controller(repository);

        new UI(scanner, controller).interact();
    }
}
