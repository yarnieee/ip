package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Init method
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * String method
     * [E][X] this is the event (from: time to: timetime)
     * E to denote class Event
     * X to denote task is marked done
     */
    public String toString() {
        super.toString();
        return super.toString() + String.format(" (from: %s to: %s)", this.getFrom(), this.getTo());
    }

    /**
     * Overridden method to identify which class a certain object is.
     */
    public char getIdentifier() {
        return 'E';
    }

    /**
     * Getter and setter methods
     */
    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
