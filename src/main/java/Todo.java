public class Todo extends Task {
    /**
     * Init method
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overridden method to identify which class a certain object is.
     */
    public char getIdentifier() {
        return 'T';
    }

}
