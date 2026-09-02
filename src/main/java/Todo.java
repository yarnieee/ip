public class Todo extends Task {
    /**
     * Init method
     */
    public Todo(String description) {
        super(description);
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

}
