package org.lessons.java.eventManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Event event = createEvent(scanner);
        System.out.println(event);

        scanner.close();
    }

    public static Event createEvent(Scanner scanner) {
        String title;
        LocalDate date;
        int totalSeats;
        Event e = null;

        do {
            try {
                System.out.println("Insert new event");
                title = getTitleFromUser(scanner);
                date = getDateFromUser(scanner);
                totalSeats = getTotSeatsFromUser(scanner);

                e = new Event(title, date, totalSeats);
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        } while (e == null);
        return e;
    }

    public static String getTitleFromUser(Scanner scanner) {
        String title = null;
        do {
            try {
                System.out.print("Insert event's title: ");
                title = scanner.nextLine();
                if (title.isEmpty()) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Event must have a title");
            }
        } while (title == null || title.isEmpty());
        return title;
    }

    public static LocalDate getDateFromUser(Scanner scanner) {
        LocalDate date = null;
        while (date == null) {
            try {
                System.out.print("Insert event's date (YYYY-mm-dd): ");
                date = Event.validateDate(LocalDate.parse(scanner.nextLine()));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return date;
    }

    public static int getTotSeatsFromUser(Scanner scanner) {
        int totSeats = 0;

        while (totSeats <= 0) {
            try {
                System.out.print("Insert event's total seats: ");
                totSeats = Event.validateSeats(Integer.parseInt(scanner.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return totSeats;
    }

}
