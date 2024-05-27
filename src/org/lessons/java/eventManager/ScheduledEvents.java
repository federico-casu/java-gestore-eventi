package org.lessons.java.eventManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduledEvents {

    // ATTRIBUTES
    private String title;
    private List<Event> events;

    // CONSTRUCTORS
    public ScheduledEvents(String title) {
        this.title = title;
        this.events = new ArrayList<>();
    }


    // METHODS
    public void addEvent(Event e) {
        this.events.add(e);
    }

    public List<Event> getEventsByPeriod(LocalDate date) {
        List<Event> filteredEvents = new ArrayList<>();

        for (Event e : this.events) {
            if (e.getDate().equals(date)) {
                filteredEvents.add(e);
            }
        }
        return filteredEvents;
    }

    public int programLength() {
        return events.size();
    }

    public void clearProgram() {
        this.events.clear();
    }

    // GETTER - SETTER
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    @Override
    public String toString() {
        return "ScheduledEvents{" + '\n' +
                "title='" + title + '\'' + ", " + '\n' +
                "events=" + events +
                '}';
    }
}
