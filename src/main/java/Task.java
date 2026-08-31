public class Task {
    /**
     * The Task class is used for SmartFella to track tasks.
     */
    private String name;
    private boolean done;
    
    /**
     * Init method
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * String method
     */
    public String toString() {
        char isDone = (this.isDone()) ? 'X' : ' ';
        return String.format( "[%c][%c] %s", this.getIdentifier(), isDone, this.getName());
    }

    /**
     * Overridden method to identify which class a certain object is.
     */
    public char getIdentifier() {
        return ' ';
    }

    /**
     * Getter and setter methods
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }
}
