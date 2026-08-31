public class Todo extends Task {
    protected boolean isDone;

    /**
     * Init method
     */
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    /**
     * String method uses parent class toString(), where getIdentifier() is overridden into the Todo version.
     * [T][X] this is the todo
     * T to denote class todo
     * X to denote task is marked done
     */

    /**
     * Overridden method to identify which class a certain object is.
     */
    public char getIdentifier() {
        return 'T';
    }

    /**
     * Getter and setter methods
     */
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
