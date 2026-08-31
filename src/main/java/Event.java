public class Event extends Task {
    private String from;
    private String to;

    /**
     * Init method
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * String method
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
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
