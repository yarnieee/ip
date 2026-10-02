package task;

import java.time.LocalDateTime;

public class Deadline extends Todo {
    private LocalDateTime deadline;

    /**
     * Init method
     * @param description
     * @param deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String method
     * [D][X] this is the deadline (by: time)
     * D to denote class Deadline
     * X to denote task is marked done
     */
    public String toString() {
        super.toString();
        return super.toString() + String.format(" (by: %s)", getDeadline());
    }

    /**
     * Overridden method to identify which class a certain object is.
     */
    public char getIdentifier() {
        return 'D';
    }

    /**
     * Getter and setter methods
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime by) {
        this.deadline = by;
    }
}
