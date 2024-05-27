package org.lessons.java.eventManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event {

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

    public static int validateSeats(int totSeats) {
        if (totSeats <= 0) {
            throw new IllegalArgumentException("Invalid number of seats: " + totSeats);
        }
        return totSeats;
    }

    public void reserveSeat(int seats) throws RuntimeException {
        if (this.date.isBefore(LocalDate.now())) {
            throw new RuntimeException("This event has already been");
        } else if ((this.totalSeats - this.reservedSeats) < validateSeats(seats)) {
            throw new RuntimeException("There are not enough seats");
        }
        this.reservedSeats += seats;
    }

    public void cancelReservation(int seats) {
        if (this.date.isBefore(LocalDate.now())) {
            throw new RuntimeException("This event has already been");
        } else if (this.reservedSeats < validateSeats(seats)) {
            throw new RuntimeException("There are not enough booked seats");
        }
        this.reservedSeats -= seats;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd", Locale.ENGLISH);
        return "Event{" +
                "date=" + date.format(formatter) +
                ", title='" + title + '\'' +
                '}';
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
}
