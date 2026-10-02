package fella;

import task.Task;
import storage.Storage;
import parser.Parser;
import tasklist.TaskList;
import constants.Constants;

/**
 *  The AbstractFella class runs a To-do list manager with an Interesting personality. The ___Fella class creates and tracks instances of the Task class.
 * This is the main file which runs the program.
*/
public abstract class AbstractFella<C extends Constants> {
    // ============================================== VARIABLES ============================================================
    /**
     * Constants
     */
    final String SAVE_FILE_PATH = "./data/SmartFella.txt";

    /**
     * Tracking variables
     */
    static boolean isRunning;
    protected C c;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    protected AbstractFella(C constants) {
        this.c = constants;
        this.tasks = new TaskList(constants);
        this.storage = new Storage(SAVE_FILE_PATH, constants);
        this.parser = new Parser(constants);
        isRunning = true;
    }

    // ============================================== PRINT MESSAGES ============================================================
    /**
     * The following methods are intended to be overridden by subclasses SmartFella and FartSmella
     */
    public void printFella() {
        System.out.println(c.FELLA_STRING);
    }

    public void printGreeting() {
        System.out.println(c.GREETING_STRING);
    }

    public void printGoodbye() {
        System.out.println(c.GOODBYE_STRING);
    }

    /**
     * Match input to specific keywords and perform related actions.
     * @param input
     */
    public void matchInput(String input) {
        if (input.equals(c.BYE_KEYWORD)) {
            isRunning = false;

        } else if (input.equals(c.LIST_KEYWORD)
                || input.startsWith(c.LIST_KEYWORD + " ")){
            tasks.getList(input);

        } else if (input.startsWith(c.MARK_KEYWORD)
                || input.startsWith(c.UNMARK_KEYWORD)) {
            tasks.markDone(input);
            storage.updateData(tasks);

        } else if (input.startsWith(c.TODO_KEYWORD)){
            Task new_task = tasks.addTodo(input);
            storage.saveData(new_task);

        } else if (input.startsWith(c.DEADLINE_KEYWORD)){
            Task new_task = tasks.addDeadline(input);
            storage.saveData(new_task);

        } else if (input.startsWith(c.EVENT_KEYWORD)){
            Task new_task = tasks.addEvent(input);
            storage.saveData(new_task);

        } else if (input.startsWith(c.DELETE_KEYWORD)) {
            boolean deleteSuccess = tasks.delete(input);
            if (deleteSuccess) {
                storage.updateData(tasks);
            }

        } else {
            System.out.println(c.INCORRECT_COMMAND_STRING);
        }
    }

    // ============================================== MAIN FUNCTION ============================================================
    /**
     * Start the program.
     */
    public void run() {
        printFella();
        printGreeting();

        //load current data if exists
        tasks = storage.loadData(tasks);

        // main process
        String input;
        while (isRunning) {
            input = parser.getInput();
            matchInput(input);
        }

        printGoodbye();
    }
}
