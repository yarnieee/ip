public class Deadline extends Todo {
    private String deadline;

    /**
     * Init method
     * @param description
     * @param deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String method
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
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String by) {
        this.deadline = by;
    }
}
