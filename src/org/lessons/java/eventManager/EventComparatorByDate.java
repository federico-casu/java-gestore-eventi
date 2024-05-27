package org.lessons.java.eventManager;

import java.util.Comparator;

public class EventComparatorByDate implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}
