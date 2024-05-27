package org.lessons.java.eventManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concert extends Event{

    // ATTRIBUTES
    private LocalTime time;
    private BigDecimal price;

    // CONSTRUCTORS
    public Concert(String title, LocalDate date, int totalSeats, LocalTime time, BigDecimal price) {
        super(title, date, totalSeats);
        this.time = validateTime(time, this.getDate());
        this.price = validatePrice(price);
    }

    // METHODS
    public static LocalTime validateTime(LocalTime time, LocalDate date) throws IllegalArgumentException {
        if (time == null || date.equals(LocalDate.now()) && time.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Invalid time: " + time);
        }
        return time;
    }

    public static BigDecimal validatePrice(BigDecimal price) throws IllegalArgumentException {
        if (price == null || price.compareTo(new BigDecimal(0)) <= 0) {
            throw new IllegalArgumentException("Invalid price: " + price);
        }
        return price;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        return "Concert{" +
                "title='" + getTitle() + '\'' +
                ", date=" + getDate().format(formatter) +
                ", totalSeats=" + getTotalSeats() +
                ", reservedSeats=" + getReservedSeats() +
                ", availableSeats=" + (getTotalSeats() - getReservedSeats()) +
                ", time=" + time +
                ", price=" + price + " $" +
                '}' + '\n';
    }

    // GETTER - SETTER
    public LocalTime getTime() {
        return this.time;
    }
    public void setTime(LocalTime time) {
        this.time = validateTime(time, this.getDate());
    }

    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(BigDecimal price) {
        this.price = validatePrice(price);
    }
}
