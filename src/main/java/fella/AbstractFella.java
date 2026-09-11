package fella;

import java.util.ArrayList;
import java.util.Scanner;

import error.EmptyArgumentException;
import error.IncorrectArgumentCountException;
import error.IncorrectArgumentFormatException;
import error.InvalidRangeException;
import error.TaskAlreadyMarkedException;
import error.TaskAlreadyUnmarkedException;
import error.TooFewArgumentsException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 *  The AbstractFella class runs a To-do list manager with an Interesting personality. The ___Fella class creates and tracks instances of the Task class.
 * This is the main file which runs the program.
*/
public abstract class AbstractFella {
    // ============================================== VARIABLES ============================================================
    /**
     * Constants
     */
    final String INPUT_MARKER_STRING = ">>> ";
    final String BYE_KEYWORD = "bye";
    final String LIST_KEYWORD = "list";
    final String MARK_KEYWORD = "mark";
    final String UNMARK_KEYWORD = "unmark";
    final String DELETE_KEYWORD = "delete";

    final String TODO_KEYWORD = "todo";
    final String DEADLINE_KEYWORD = "deadline";
    final String EVENT_KEYWORD = "event";
    final String DEADLINE_DELIM = "/by";
    final String EVENT_START_DELIM = "/from";
    final String EVENT_END_DELIM = "/to";

    final String INCORRECT_COMMAND_STRING = ">> cOMMAND nOT rECOGNISED ! ! !\n";
    final String INVALID_VALUE_STRING = ">> tHAT TASK NUMBER IS NOT IN THE LIST ! ! !\n";
    final String MISSING_TASK_NUMBER_STRING = ">> tELL ME WHICH TASK TO MARK OR UNMARK ! ! !\n";
    final String INVALID_NUMBER_STRING = ">> tHAT TASK NUMBER IS NOT A NUMBER ! ! !\n";
    final String ALREADY_MARKED_STRING = ">> tHAT TASK IS ALREADY MARKED ! ! !\n";
    final String ALREADY_UNMARKED_STRING = ">> tHAT TASK IS ALREADY UNMARKED ! ! !\n";
    final String TODO_FORMAT_ERROR_STRING = ">> tODO NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    final String TODO_EMPTY_ERROR_STRING = ">> tODO DESCRIPTION CANNOT BE EMPTY ! ! !\n";
    final String DEADLINE_FORMAT_ERROR_STRING = ">> dEADLINE NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    final String DEADLINE_COUNT_ERROR_STRING = ">> dEADLINE NEEDS A DESCRIPTION AND ONE /by DATE ! ! !\n";
    final String DEADLINE_EMPTY_ERROR_STRING = ">> dEADLINE DESCRIPTION AND DATE CANNOT BE EMPTY ! ! !\n";
    final String EVENT_FORMAT_ERROR_STRING = ">> eVENT NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    final String EVENT_COUNT_ERROR_STRING = ">> eVENT NEEDS A DESCRIPTION, ONE /from, AND ONE /to ! ! !\n";
    final String EVENT_EMPTY_ERROR_STRING = ">> eVENT DESCRIPTION, START, AND END CANNOT BE EMPTY ! ! !\n";

    /**
     * Tracking variables
     */
    static boolean isRunning = true;
    ArrayList<Task> tasks = new ArrayList<>();
    int taskListSize = 0;

    // ============================================== PRINT MESSAGES ============================================================
    /**
     * The following methods are intended to be overridden by subclasses SmartFella and FartSmella
     */
    public void printFella() {
        System.out.println("");
    }

    public void printGreeting() {
        System.out.println("<greeting message>\n");
    }

    public void printGoodbye() {
        System.out.println("<goodbye message>\n");
    }

    /**
     * Prints success message after successful adding of Todo/Event/Deadline
     */
    public void printSuccessMessage() {
        //print result
        System.out.println(">> aDDED INTO LIST !");
        System.out.println(">> " + tasks.get(taskListSize-1).toString());
        System.out.println(">> nOW YOU HAVE " + taskListSize + " TASKS IN THE LIST ! ! !");
        System.out.println();
    }

    // ============================================== IMPORTANT FUNCTIONS ============================================================
    /**
     * Receives user commands and executes corresponding actions.
     */
    private void getInput() {
        String input;

        Scanner scanner = new Scanner(System.in); //should be closed at some point?
        System.out.print(INPUT_MARKER_STRING); // your inputs will be denoted by triple ">>>"
        input = scanner.nextLine();

        // keyword matching
        matchInput(input);
    }

    /**
     * Match input to specific keywords and perform related actions.
     * @param input
     */
    public void matchInput(String input) {
        if (input.equals(BYE_KEYWORD)) {
            isRunning = false;

        } else if (input.equals(LIST_KEYWORD)){
            getList();

        } else if (input.startsWith(MARK_KEYWORD)
                || input.startsWith(UNMARK_KEYWORD)) {
            markDone(input);

        } else if (input.startsWith(TODO_KEYWORD)){
            addTodo(input);

        } else if (input.startsWith(DEADLINE_KEYWORD)){
            addDeadline(input);

        } else if (input.startsWith(EVENT_KEYWORD)){
            addEvent(input);

        } else if (input.startsWith(DELETE_KEYWORD)) {
            delete(input);

        } else {
            System.out.println(INCORRECT_COMMAND_STRING);
        }
    }
    
    /**
     * Prints a list of all previous non-keyword commands, which have been saved as part of the To-do list.
     */
    private void getList() {
        int listCounter;

        for (int i = 0; i < taskListSize; i++) {
            listCounter = i + 1;

            System.out.println(String.format("%d. %s", 
                listCounter,
                tasks.get(i).toString()));
        }
        System.out.println("");
    }

    /**
     * Takes the user input as param "cmd". If format of "cmd" is correct and within range, task at corresponding index will be marked as done/not done depending on "mark/unmark".
     * @param cmd
     */
    private void markDone(String cmd) {
        try {
            isValidMarkDone(cmd);
        } catch (TooFewArgumentsException e) {
            System.out.println(MISSING_TASK_NUMBER_STRING);
            return;
        } catch (NumberFormatException e) {
            System.out.println(INVALID_NUMBER_STRING);
            return;
        } catch (InvalidRangeException e) {
            System.out.println(INVALID_VALUE_STRING);
            return;
        } catch (TaskAlreadyMarkedException e) {
            System.out.println(ALREADY_MARKED_STRING);
            return;
        } catch (TaskAlreadyUnmarkedException e) {
            System.out.println(ALREADY_UNMARKED_STRING);
            return;
        }

        String[] data = cmd.split(" ");
        int index = Integer.parseInt(data[1]) - 1;
        
        //match with keyword & make change
        if (data[0].startsWith(MARK_KEYWORD)) {
            System.out.println(">> mARKED "
                + Integer.toString(index + 1)
                + "! ! !\n");
            tasks.get(index).markDone();
        } else {
            System.out.println(">> uNMARKED "
                + Integer.toString(index + 1)
                + "! ! !\n");
            tasks.get(index).unmarkDone();
        }
    }

    /**
     * Validates that a mark or unmark command contains an existing task number.
     *
     * @throws TooFewArgumentsException if the task number is missing
     * @throws NumberFormatException if the task number is not an integer
     * @throws InvalidRangeException if the task number is outside the task list
     * @throws TaskAlreadyMarkedException if an already marked task is marked again
     * @throws TaskAlreadyUnmarkedException if an already unmarked task is unmarked again
     */
    private void isValidMarkDone(String input)
            throws TooFewArgumentsException, InvalidRangeException, TaskAlreadyMarkedException,
            TaskAlreadyUnmarkedException {
        String[] data = input.split(" ");

        if (data.length < 2) {
            throw new TooFewArgumentsException();
        }

        try {
            Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        // check if within range
        int index = Integer.parseInt(data[1]) - 1;
        if (index >= taskListSize
                || index < 0) {
            throw new InvalidRangeException();
        }

        if (data[0].startsWith(MARK_KEYWORD) && tasks.get(index).isDone()) {
            throw new TaskAlreadyMarkedException();
        }

        if (data[0].startsWith(UNMARK_KEYWORD) && !tasks.get(index).isDone()) {
            throw new TaskAlreadyUnmarkedException();
        }
    }
    // ============================================== ADD/VALIDATE ITEMS ============================================================
    /**
     * Add Todo item into list
     * @param input
     */
    private void addTodo(String input) {
        String description;

        try {
            isValidTodo(input);
        } catch (IncorrectArgumentFormatException e) {
            System.out.println(TODO_FORMAT_ERROR_STRING);
            return;
        } catch (EmptyArgumentException e) {
            System.out.println(TODO_EMPTY_ERROR_STRING);
            return;
        }

        //add todo
        description = input.substring(TODO_KEYWORD.length())
                            .strip();
        tasks.add(new Todo(description));
        taskListSize++;

        printSuccessMessage();
    }

    /**
     * Validates the format of a todo command.
     *
     * @throws IncorrectArgumentFormatException if no space follows the command keyword
     * @throws EmptyArgumentException if the description is missing
     */
    private void isValidTodo(String input)
            throws IncorrectArgumentFormatException, EmptyArgumentException {
        //check that TODO_KEYWORD is proceeded by a space
        //and that there exists content after the space
        boolean hasLength = (input.length() > TODO_KEYWORD.length()+1);
        if (!hasLength) {
            throw new EmptyArgumentException();
        }

        boolean hasSpace = (input.charAt(TODO_KEYWORD.length())==' ');
        if (!hasSpace) {
            throw new IncorrectArgumentFormatException();
        }

        if (input.substring(TODO_KEYWORD.length()).strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
    }

    /**
     * Add deadline item into list
     * @param input
     */
    private void addDeadline(String input) {
        String[] description;
        String text, deadline;

        try {
            isValidDeadline(input);
        } catch (IncorrectArgumentFormatException e) {
            System.out.println(DEADLINE_FORMAT_ERROR_STRING);
            return;
        } catch (IncorrectArgumentCountException e) {
            System.out.println(DEADLINE_COUNT_ERROR_STRING);
            return;
        } catch (EmptyArgumentException e) {
            System.out.println(DEADLINE_EMPTY_ERROR_STRING);
            return;
        }

        //add
        description = input.substring(DEADLINE_KEYWORD.length())
                            .strip()
                            .split(DEADLINE_DELIM, -1);
        text = description[0].strip();
        deadline = description[1].strip();
        
        tasks.add(new Deadline(text, deadline));
        taskListSize++;

        printSuccessMessage();
    }

    /**
     * Validates the format of a deadline command.
     *
     * @throws IncorrectArgumentFormatException if no space follows the command keyword
     * @throws IncorrectArgumentCountException if there is not exactly one {@code /by} delimiter
     * @throws EmptyArgumentException if the description or deadline is empty
     */
    private void isValidDeadline(String input) throws IncorrectArgumentFormatException,
            IncorrectArgumentCountException, EmptyArgumentException {
        //check that DEADLINE_KEYWORD is proceeded by a space
        //and that there exists content after the space
        boolean hasLength = (input.length() > DEADLINE_KEYWORD.length()+1);
        if (!hasLength) {
            throw new EmptyArgumentException();
        }

        boolean hasSpace = (input.charAt(DEADLINE_KEYWORD.length())==' ');
        if (!hasSpace) {
            throw new IncorrectArgumentFormatException();
        }

        //check that DEADLINE_DELIM exists and that after splitting all substrings are non-empty
        String[] description;

        description = input.substring(DEADLINE_KEYWORD.length())
                            .strip()
                            .split(DEADLINE_DELIM, -1);

        if (description.length != 2) {
            throw new IncorrectArgumentCountException();
        }

        if (description[0].strip().isEmpty()
            || description[1].strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
    }

    /**
     * Add event item into list
     * @param input
     */
    private void addEvent(String input) {
        String[] description;
        String text, from, to;

        try {
            isValidEvent(input);
        } catch (IncorrectArgumentFormatException e) {
            System.out.println(EVENT_FORMAT_ERROR_STRING);
            return;
        } catch (IncorrectArgumentCountException e) {
            System.out.println(EVENT_COUNT_ERROR_STRING);
            return;
        } catch (EmptyArgumentException e) {
            System.out.println(EVENT_EMPTY_ERROR_STRING);
            return;
        }

        //add
        description = input.substring(EVENT_KEYWORD.length())
                            .strip()
                            .split(EVENT_START_DELIM + "|" + EVENT_END_DELIM, -1);

        text = description[0].strip();
        from = description[1].strip();
        to = description[2].strip();

        //add
        tasks.add(new Event(text, from, to));
        taskListSize++;

        printSuccessMessage();
    }

    /**
     * Validates the format of an event command.
     *
     * @throws IncorrectArgumentFormatException if no space follows the command keyword
     * @throws IncorrectArgumentCountException if there are not exactly two time delimiters
     * @throws EmptyArgumentException if the description, start, or end time is empty
     */
    private void isValidEvent(String input) throws IncorrectArgumentFormatException,
            IncorrectArgumentCountException, EmptyArgumentException {
        //check that EVENT_KEYWORD is proceeded by a space
        //and that there exists content after the space
        boolean hasLength = (input.length() > EVENT_KEYWORD.length()+1);
        if (!hasLength) {
            throw new EmptyArgumentException();
        }

        boolean hasSpace = (input.charAt(EVENT_KEYWORD.length())==' ');
        if (!hasSpace) {
            throw new IncorrectArgumentFormatException();
        }

        String[] description;
        description = input.substring(EVENT_KEYWORD.length())
                            .strip()
                            .split(EVENT_START_DELIM + "|" + EVENT_END_DELIM, -1);

        if (description.length != 3) {
            throw new IncorrectArgumentCountException();
        }

        if (description[0].strip().isEmpty()
            || description[1].strip().isEmpty()
            || description[2].strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
    }

    // ============================================== DELETE ============================================================

    private void delete(String cmd) {
        try {
            isValidDelete(cmd);
        } catch (TooFewArgumentsException e) {
            System.out.println(MISSING_TASK_NUMBER_STRING);
            return;
        } catch (NumberFormatException e) {
            System.out.println(INVALID_NUMBER_STRING);
            return;
        } catch (InvalidRangeException e) {
            System.out.println(INVALID_VALUE_STRING);
            return;
        }

        String[] data = cmd.split(" ");
        int index = Integer.parseInt(data[1]) - 1;
        
        //match with keyword & make change
        if (data[0].startsWith(DELETE_KEYWORD)) {
            System.out.println(">> dELETED "
                + Integer.toString(index + 1)
                + "! ! !\n");
            //TODO: implement delete based on index
            tasks.remove(index);
        }
    }

    /**
     * Validates that a delete command contains an existing task number.
     *
     * @throws TooFewArgumentsException if the task number is missing
     * @throws NumberFormatException if the task number is not an integer
     * @throws InvalidRangeException if the task number is outside the task list
     */
    private void isValidDelete(String input)
            throws TooFewArgumentsException, InvalidRangeException {
        String[] data = input.split(" ");

        if (data.length < 2) {
            throw new TooFewArgumentsException();
        }

        try {
            Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        // check if within range
        int index = Integer.parseInt(data[1]) - 1;
        if (index >= taskListSize
                || index < 0) {
            throw new InvalidRangeException();
        }
    }

    // ============================================== MAIN FUNCTION ============================================================
    /**
     * Start the program.
     */
    public void run() {
        printFella();
        printGreeting();

        // main process
        while (isRunning) {
            this.getInput();
        }

        printGoodbye();
    }
}
