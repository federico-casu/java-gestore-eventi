package org.lessons.java.eventManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event implements Comparable<Event> {

    // ATTRIBUTES
    private String title;
    private LocalDate date;
    private final int totalSeats;
    private int reservedSeats;

    // CONSTRUCTORS
    public Event(String title, LocalDate date, int totalSeats) {
        this.title = title;
        this.date = validateDate(date);
        this.totalSeats = validateSeats(totalSeats);
    }

    // METHODS
    public static LocalDate validateDate(LocalDate date) throws IllegalArgumentException {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date: " + date);
        }
        return date;
    }

    public static int validateSeats(int totSeats) throws IllegalArgumentException {
        if (totSeats <= 0) {
            throw new IllegalArgumentException("Invalid number of seats: " + totSeats);
        }
        return totSeats;
    }

    public boolean reserveSeat(int seats) throws RuntimeException {
        if (this.date.isBefore(LocalDate.now())) {
            throw new RuntimeException("This event has already been");
        } else if ((this.totalSeats - this.reservedSeats) < validateSeats(seats)) {
            throw new RuntimeException("There are not enough seats");
        }
        this.reservedSeats += seats;
        return true;
    }

    public boolean cancelReservation(int seats) throws RuntimeException {
        if (this.date.isBefore(LocalDate.now())) {
            throw new RuntimeException("This event has already been");
        } else if (this.reservedSeats < validateSeats(seats)) {
            throw new RuntimeException("There are not enough booked seats");
        }
        this.reservedSeats -= seats;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + date.format(formatter) +
                ", totalSeats=" + totalSeats +
                ", reservedSeats=" + reservedSeats +
                ", availableSeats=" + (totalSeats - reservedSeats) +
                '}' + '\n';
    }

    // GETTER - SETTER
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate newDate) {
        this.date = validateDate(newDate);
    }

    public int getTotalSeats() {
        return this.totalSeats;
    }

    public int getReservedSeats() {
        return this.reservedSeats;
    }

    @Override
    public int compareTo(Event o) {
        if (o == null) {
            return 1;
        } else {
            return date.compareTo(o.getDate());
        }
    }
}
