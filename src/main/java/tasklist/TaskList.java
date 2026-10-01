package tasklist;

import java.util.ArrayList;

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

import constants.Constants;

public class TaskList {
    ArrayList<Task> tasks;
    int taskListSize;


    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskListSize = 0;
    }

    // ============================================== SIZE ============================================================
    public int getSize() {
        return taskListSize;
    }

    public Task getTask(int N) {
        // TODO: add error handling checks
        return tasks.get(N);
    }

    /**
     * Prints a list of all previous non-keyword commands, which have been saved as part of the To-do list.
     */
    public void getList() {
        int listCounter;

        for (int i = 0; i < taskListSize; i++) {
            listCounter = i + 1;

            System.out.println(String.format("%d. %s", 
                listCounter,
                tasks.get(i).toString()));
        }
        System.out.println("");
    }

    // ============================================== ADD/VALIDATE ITEMS ============================================================
    /**
     * Add Todo item into list
     * @param input
     */
    public Task addTodo(String input) {
        String description;

        try {
            isValidTodo(input);
        } catch (IncorrectArgumentFormatException e) {
            System.out.println(Constants.TODO_FORMAT_ERROR_STRING);
            return null;
        } catch (EmptyArgumentException e) {
            System.out.println(Constants.TODO_EMPTY_ERROR_STRING);
            return null;
        }

        //add todo
        description = input.substring(Constants.TODO_KEYWORD.length())
                            .strip();

        Task tempTask =  new Todo(description);
        tasks.add(tempTask);
        taskListSize++;

        return tempTask;
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
        boolean hasLength = (input.length() > Constants.TODO_KEYWORD.length()+1);
        if (!hasLength) {
            throw new EmptyArgumentException();
        }

        boolean hasSpace = (input.charAt(Constants.TODO_KEYWORD.length())==' ');
        if (!hasSpace) {
            throw new IncorrectArgumentFormatException();
        }

        if (input.substring(Constants.TODO_KEYWORD.length()).strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
    }

    /**
     * Add deadline item into list
     * @param input
     */
    public Task addDeadline(String input) {
        String[] description;
        String text, deadline;

        try {
            isValidDeadline(input);
        } catch (IncorrectArgumentFormatException e) {
            System.out.println(Constants.DEADLINE_FORMAT_ERROR_STRING);
            return null;
        } catch (IncorrectArgumentCountException e) {
            System.out.println(Constants.DEADLINE_COUNT_ERROR_STRING);
            return null;
        } catch (EmptyArgumentException e) {
            System.out.println(Constants.DEADLINE_EMPTY_ERROR_STRING);
            return null;
        }

        //add
        description = input.substring(Constants.DEADLINE_KEYWORD.length())
                            .strip()
                            .split(Constants.DEADLINE_DELIM, -1);
        text = description[0].strip();
        deadline = description[1].strip();

        Task tempTask =  new Deadline(text, deadline);
        tasks.add(tempTask);
        taskListSize++;

        return tempTask;
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
        boolean hasLength = (input.length() > Constants.DEADLINE_KEYWORD.length()+1);
        if (!hasLength) {
            throw new EmptyArgumentException();
        }

        boolean hasSpace = (input.charAt(Constants.DEADLINE_KEYWORD.length())==' ');
        if (!hasSpace) {
            throw new IncorrectArgumentFormatException();
        }

        //check that DEADLINE_DELIM exists and that after splitting all substrings are non-empty
        String[] description;

        description = input.substring(Constants.DEADLINE_KEYWORD.length())
                            .strip()
                            .split(Constants.DEADLINE_DELIM, -1);

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
    public Task addEvent(String input) {
        String[] description;
        String text, from, to;

        try {
            isValidEvent(input);
        } catch (IncorrectArgumentFormatException e) {
            System.out.println(Constants.EVENT_FORMAT_ERROR_STRING);
            // TODO: for all of these, maybe do throw error instead, abstractfella will catch the error
            return null;
        } catch (IncorrectArgumentCountException e) {
            System.out.println(Constants.EVENT_COUNT_ERROR_STRING);
            return null;
        } catch (EmptyArgumentException e) {
            System.out.println(Constants.EVENT_EMPTY_ERROR_STRING);
            return null;
        }

        //add
        description = input.substring(Constants.EVENT_KEYWORD.length())
                            .strip()
                            .split(Constants.EVENT_START_DELIM + "|" + Constants.EVENT_END_DELIM, -1);

        text = description[0].strip();
        from = description[1].strip();
        to = description[2].strip();

        
        Task tempTask =  new Event(text, from, to);
        tasks.add(tempTask);
        taskListSize++;

        return tempTask;
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
        boolean hasLength = (input.length() > Constants.EVENT_KEYWORD.length()+1);
        if (!hasLength) {
            throw new EmptyArgumentException();
        }

        boolean hasSpace = (input.charAt(Constants.EVENT_KEYWORD.length())==' ');
        if (!hasSpace) {
            throw new IncorrectArgumentFormatException();
        }

        String[] description;
        description = input.substring(Constants.EVENT_KEYWORD.length())
                            .strip()
                            .split(Constants.EVENT_START_DELIM + "|" + Constants.EVENT_END_DELIM, -1);

        if (description.length != 3) {
            throw new IncorrectArgumentCountException();
        }

        if (description[0].strip().isEmpty()
            || description[1].strip().isEmpty()
            || description[2].strip().isEmpty()) {
            throw new EmptyArgumentException();
        }
    }

    /**
     * Takes the user input as param "cmd". If format of "cmd" is correct and within range, task at corresponding index will be marked as done/not done depending on "mark/unmark".
     * @param cmd
     */
    public void markDone(String cmd) {
        try {
            isValidMarkDone(cmd);
        } catch (TooFewArgumentsException e) {
            System.out.println(Constants.MISSING_TASK_NUMBER_STRING);
            return;
        } catch (NumberFormatException e) {
            System.out.println(Constants.INVALID_NUMBER_STRING);
            return;
        } catch (InvalidRangeException e) {
            System.out.println(Constants.INVALID_VALUE_STRING);
            return;
        } catch (TaskAlreadyMarkedException e) {
            System.out.println(Constants.ALREADY_MARKED_STRING);
            return;
        } catch (TaskAlreadyUnmarkedException e) {
            System.out.println(Constants.ALREADY_UNMARKED_STRING);
            return;
        }

        String[] data = cmd.split(" ");
        int index = Integer.parseInt(data[1]) - 1;
        
        //match with keyword & make change
        if (data[0].startsWith(Constants.MARK_KEYWORD)) {
            System.out.println(Constants.MARKED_SUCCESS_STRING
                + Integer.toString(index + 1)
                + "! ! !\n");
            tasks.get(index).markDone();
        } else {
            System.out.println(Constants.UNMARKED_SUCCESS_STRING
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

        if (data[0].startsWith(Constants.MARK_KEYWORD) && tasks.get(index).isDone()) {
            throw new TaskAlreadyMarkedException();
        }

        if (data[0].startsWith(Constants.UNMARK_KEYWORD) && !tasks.get(index).isDone()) {
            throw new TaskAlreadyUnmarkedException();
        }
    }
    // ============================================== DELETE ============================================================
    public boolean delete(String cmd) {
        try {
            isValidDelete(cmd);
        } catch (TooFewArgumentsException e) {
            System.out.println(Constants.MISSING_TASK_NUMBER_STRING);
            return false;
        } catch (NumberFormatException e) {
            System.out.println(Constants.INVALID_NUMBER_STRING);
            return false;
        } catch (InvalidRangeException e) {
            System.out.println(Constants.INVALID_VALUE_STRING);
            return false;
        }

        String[] data = cmd.split(" ");
        int index = Integer.parseInt(data[1]) - 1;
        
        //match with keyword & make change
        if (data[0].startsWith(Constants.DELETE_KEYWORD)) {
            System.out.println(Constants.DELETED_SUCCESS_STRING
                + Integer.toString(index + 1)
                + "! ! !\n");
            tasks.remove(index);
            taskListSize--;
        }

        return true;
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

    
}
