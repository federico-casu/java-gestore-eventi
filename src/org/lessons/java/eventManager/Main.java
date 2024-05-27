package org.lessons.java.eventManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        Event event = null;

        while (choice != 0) {
            printMenu();
            System.out.print("Select: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    // new event
                    event = createEvent(scanner);
                    break;
                case 2:
                    // print event
                    System.out.println(event);
                    break;
                case 3:
                    // reserve seats
                    reservation(scanner, event);
                    break;
                case 4:
                    // cancel reserved seats
                    cancellation(scanner, event);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
    }

    public static Event createEvent(Scanner scanner) {
        String title;
        LocalDate date;
        int totalSeats;
        Event e = null;
        boolean isConcert = false;

        do {
            try {
                System.out.println("Insert new event");
                title = getTitleFromUser(scanner);
                date = getDateFromUser(scanner);
                totalSeats = getTotSeatsFromUser(scanner);

                System.out.print("Is this event a concert? (y/n): ");
                isConcert = scanner.nextLine().equalsIgnoreCase("y");
                if (isConcert) {
                    LocalTime time = getTimeFromUser(scanner, date);
                    BigDecimal price = getPriceFromUser(scanner);

                    e = new Concert(title, date, totalSeats, time, price);
                } else {
                    e = new Event(title, date, totalSeats);
                }

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

    public static LocalTime getTimeFromUser(Scanner scanner, LocalDate date) {
        LocalTime time = null;
        while (time == null) {
            try {
                System.out.print("Insert event's time (HH:mm): ");
                time = Concert.validateTime(LocalTime.parse(scanner.nextLine()), date);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return time;
    }

    public static BigDecimal getPriceFromUser(Scanner scanner) throws IllegalArgumentException {
        BigDecimal price = null;

        while (price == null) {
            try {
                System.out.print("Insert event's price: ");
                price = Concert.validatePrice(new BigDecimal(scanner.nextLine()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public static void reservation(Scanner scanner, Event event) {
        boolean reserved = false;
        while (!reserved){
            try {
                System.out.print("How many seats do you want to reserve? ");
                reserved = event.reserveSeat(Event.validateSeats(Integer.parseInt(scanner.nextLine())));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void cancellation(Scanner scanner, Event event) {
        boolean cancelled = false;
        while (!cancelled){
            try {
                System.out.print("How many seats do you want to cancel? ");
                cancelled = event.cancelReservation(Event.validateSeats(Integer.parseInt(scanner.nextLine())));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("--------------------");
        System.out.println("|       MENU       |");
        System.out.println("--------------------");
        System.out.println("1 - New event");
        System.out.println("2 - Print event");
        System.out.println("3 - Reserve seats");
        System.out.println("4 - Cancel reserved seats");
        System.out.println("0 - Exit");
    }

}
