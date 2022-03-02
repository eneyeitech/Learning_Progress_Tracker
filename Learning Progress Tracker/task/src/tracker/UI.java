package tracker;

import tracker.controller.Controller;
import tracker.model.Course;
import tracker.model.Student;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static tracker.Validator.isValidCredentials;
import static tracker.Validator.isValidMail;
import static tracker.Validator.isValidName;
import static tracker.Validator.isValidPointsFormat;
import static tracker.UI.Constants.*;
import static java.lang.System.out;



public class UI {

    final Scanner scanner;
    final Controller controller;


    public UI(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;

    }

    public void interact(){
        out.println(initialGreeting);

        Stream.generate(scanner::nextLine)
                .map(input -> controller.processMainInput(input, this))
                .filter(str -> !str.isBlank())
                .peek(System.out::println)
                .takeWhile(response ->  !response.equals(GoodBye))
                .forEach(str -> {});
    }

    public String addStudentsInteraction() {
        out.println(addStudentsMenu);
        return addStudentsInteraction(0);
    }

    private String addStudentsInteraction(int count) {
        final String input = scanner.nextLine();

        if(input.equalsIgnoreCase(commandBack)){
            return String.format(responseAddedStudentsResultTemplate, count);
        }

        final String[] args = input.trim().split("\\s+");


        if(!isValidCredentials(args)) {
            out.println(invalidCredentials);
            return addStudentsInteraction(count);

        } else if(!isValidName(args[0])) {
            out.println(invalidFirstName);
            return addStudentsInteraction(count);

        } else if(Arrays.stream(args).skip(1).limit(args.length - 2).anyMatch(arg -> !isValidName(arg))){
            out.println(invalidLastName);
            return addStudentsInteraction(count);

        } else if(!isValidMail(args[args.length - 1])){
            out.println(invalidEmail);
            return addStudentsInteraction(count);

        } else if(!controller.emailIsUnique(args[args.length - 1])) {
            out.println("This email is already taken.");
            return addStudentsInteraction(count);
        } else {
            final String lastName = Arrays.stream(args)
                    .skip(1)
                    .limit(args.length - 2)
                    .collect(Collectors.joining(" "));

            controller.addStudent(args[0], lastName, args[args.length - 1]);
            out.println(studentAdded);
            return addStudentsInteraction(count + 1);
        }
    }

    public String addPointsInteraction(boolean printHeader) {
        if(printHeader) {
            out.println(addPointsMenu);
        }
        final String idAndPointsInput = scanner.nextLine();
        if(idAndPointsInput.equals(commandBack)) {
            return "";
        }

        final String[] inputIdPointsArray = idAndPointsInput.split("\\s+");

        if(!isValidPointsFormat(inputIdPointsArray)) {
            out.println(invalidPointsFormat);
            return addPointsInteraction(false);
        }

        final Optional<Student> studentById = controller.findStudentById(inputIdPointsArray[0]);

        if(studentById.isEmpty()) {
            out.printf(responseStudentIdNotFoundTemplate + "\n", inputIdPointsArray[0]);

        } else {
            controller.addPoints(inputIdPointsArray, studentById.get());
            out.println(responsePointsUpdated);
            Controller.notified = false;
        }

        return addPointsInteraction(false);

    }

    public String findInteraction(boolean printHeader) {
        if(printHeader) {
            out.println(findMenu);
        }

        final String idInput = scanner.nextLine();

        if(idInput.equals(commandBack)) {
            return "";
        }

        final Optional<Student> studentById = controller.findStudentById(idInput);

        if(studentById.isEmpty()) {
            out.printf(responseStudentIdNotFoundTemplate + "\n", idInput);
        } else {
            out.println(studentById.get().getStudentPoints());
        }

        return findInteraction(false);

    }

    public String statisticsInteraction(boolean printHeader) {
        if(printHeader) {
            out.println(statisticsMenu);
            out.println(controller.getStatisticsReport());
        }


        final String input = scanner.nextLine();

        if(input.equals(commandBack)) {
            return "";
        } else if(!Course.isCourse(input)){
            out.println("Unknown course.");
        }else {
            out.println(controller.getCourseStats(input));
        }

        return statisticsInteraction(false);
    }

    public String notifyInteraction(boolean printHeader) {
        String s = controller.notifyStudent();
        //System.out.println(s);
        return s;
    }


    public static class Constants {
        final static public String initialGreeting = "---===¡!¡()-Learning-Progress-Tracker-()!¡!===---";
        final static public String GoodBye = "Good-Bye!";
        final static public String addStudentsMenu = "Enter student credentials or 'back' to return:";
        final static public String addPointsMenu = "Enter an id and points or 'back' to return:";
        final static public String findMenu = "Enter an id or 'back' to return:";
        final static public String statisticsMenu = "Type the name of a course to see details or 'back' to quit:";;



        final static public String commandAddStudents = "add students";
        final static public String commandAddPoints = "add points";
        final static public String commandBack = "back";
        final static public String commandExit = "exit";
        final static public String commandFind = "find";
        final static public String commandListStudents = "list";
        final static public String commandStatistics = "statistics";
        final static public String commandNotify = "notify";


        final static public String commandEmpty = "";

        final static public String responseAddedStudentsResultTemplate = "There was a total of %d students added";
        final static public String responseStudentsPointsTemplate = "%s points: %s=%d; %s=%d; %s=%d; %s=%d";
        final static public String responseStudentIdNotFoundTemplate = "No student is found for id=%s.";

        final static public String responseBack = "Do you wish to quit the program? You may enter exit to do so";
        final static public String responseEmpty = "oooops....that's no input, try again";
        final static public String responseInvalidCommand =
                "Hmm... that is unknown for me. You can try a new command or type exit to leave";
        final static public String responseListStudentsNoneFound = "No students found";
        final static public String responseListStudentsHeader = "Students:\n";
        final static public String responsePointsUpdated = "Points updated.";



        final static public String invalidCredentials = "Nope, incorrect credentials, should be name lastnames email";
        final static public String invalidEmail = "That's some incorrect email, don't try to fool me";
        final static public String invalidFirstName = "Incorrect first name it probably is";
        final static public String invalidLastName =  "I'm sure this is an incorrect last name";
        final static public String invalidPointsFormat = "Incorrect points format.";


        final static public String studentAdded = "yeah student has been added.";


    }


}
