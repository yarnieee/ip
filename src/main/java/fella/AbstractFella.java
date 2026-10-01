package fella;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import tasklist.TaskList;
import constants.Constants;

/**
 *  The AbstractFella class runs a To-do list manager with an Interesting personality. The ___Fella class creates and tracks instances of the Task class.
 * This is the main file which runs the program.
*/
public abstract class AbstractFella {
    // ============================================== VARIABLES ============================================================
    /**
     * Constants
     */
    final String SAVE_FILE_PATH = "./data/SmartFella.txt";

    /**
     * Tracking variables
     */
    static boolean isRunning;
    private TaskList tasks;

    public AbstractFella() {
        tasks = new TaskList();
        isRunning = true;
    }

    // ============================================== PRINT MESSAGES ============================================================
    /**
     * The following methods are intended to be overridden by subclasses SmartFella and FartSmella
     */
    public void printFella() {
        System.out.println(Constants.FELLA_STRING);
    }

    public void printGreeting() {
        System.out.println(Constants.GREETING_STRING);
    }

    public void printGoodbye() {
        System.out.println(Constants.GOODBYE_STRING);
    }

    /**
     * Prints success message after successful adding of Todo/Event/Deadline
     */
    public void printSuccessMessage() {
        //print result
        System.out.println(Constants.ADD_SUCCESS_STRING);
        System.out.println("" + tasks.getTask(tasks.getSize()-1).toString());
        System.out.println(Constants.TASK_COUNT_STRING1 + tasks.getSize() + Constants.TASK_COUNT_STRING2);
        System.out.println();
    }

    // ============================================== LOAD SAVE ============================================================
    /**
     * Check whether the save file exists and is a file.
     * @param path
     * @return
     */
    private boolean fileExists(String path) {
        Path filePath = Paths.get(path);

        if (Files.isRegularFile(filePath)) {
            return true;
        }

        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            return Files.isRegularFile(filePath);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Save task at index (index is arraylist index not display index.)
     * @param index
     */
    private void saveData(Task task) {
        if (!fileExists(SAVE_FILE_PATH)) {
            return;
        }

        try (FileWriter writer = new FileWriter(SAVE_FILE_PATH, true)) {
            String saveString = formatSaveString(task);

            writer.write(saveString + System.lineSeparator());

        } catch (IOException e) {
            return;
        }
    }

    /**
     * Update the saved task list after a mark or unmark operation.
     * @param index the task index in the in-memory array
     */
    private void updateData() {
        if (!fileExists(SAVE_FILE_PATH)) {
            return;
        }

        try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(formatSaveString(tasks.getTask(i)));

                if (i < tasks.getSize() - 1) {
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Returns string which is in the right format to be saved into smartfella.txt save file.
     * @param task
     * @return
     */
    private String formatSaveString(Task task) {
        String saveString;
        String isDoneString = (task.isDone()) ? Constants.MARKDONE_CHAR : "";

        if (task instanceof Deadline deadline) {
            saveString = Constants.DEADLINE_CHAR
                    + "," + isDoneString
                    + "," + deadline.getName()
                    + "," + deadline.getDeadline();
        } else if (task instanceof Event event) {
            saveString = Constants.EVENT_CHAR
                    + "," + isDoneString
                    + "," + event.getName()
                    + "," + event.getFrom()
                    + "," + event.getTo();
        } else {
            saveString = Constants.TODO_CHAR
                    + "," + isDoneString
                    + "," + task.getName();
        }

        return saveString;
    }
    
    private void loadData() {
        // open path of ./data/SmartFella.txt
        if (!fileExists(SAVE_FILE_PATH)) {
            return;
        }

        //file exists. so load data from file
        File f = new File(SAVE_FILE_PATH);

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                parseSaveString(s.nextLine());
            }
            
        } catch (FileNotFoundException e) {
            return;
        }
    }

    // TODO: how does the storage interact with the TaskList object?
    private void parseSaveString(String saveString) {
        String[] temp = saveString.split(",");

        if (saveString.startsWith(Constants.DEADLINE_CHAR)){
            tasks.addDeadline(new Deadline(temp[2], temp[3]));

        } else if (saveString.startsWith(Constants.EVENT_CHAR)){
            tasks.addEvent(new Event(temp[2], temp[3], temp[4]));

        } else {
            tasks.addTodo(new Todo(temp[2]));

        } 

        if (temp[1].equals(Constants.MARKDONE_CHAR)) {
            tasks.getTask(tasks.getSize()-1).markDone();
            // TODO: probably will not work
        }
    }


    // ============================================== IMPORTANT FUNCTIONS ============================================================
    /**
     * Receives user commands and executes corresponding actions.
     */
    private void getInput() {
        String input;

        Scanner scanner = new Scanner(System.in); //should be closed at some point?
        System.out.print(Constants.INPUT_MARKER_STRING); // your inputs will be denoted by triple ">>>"
        input = scanner.nextLine();

        // keyword matching
        matchInput(input);
    }

    /**
     * Match input to specific keywords and perform related actions.
     * @param input
     */
    public void matchInput(String input) {
        if (input.equals(Constants.BYE_KEYWORD)) {
            isRunning = false;

        } else if (input.equals(Constants.LIST_KEYWORD)){
            tasks.getList();

        } else if (input.startsWith(Constants.MARK_KEYWORD)
                || input.startsWith(Constants.UNMARK_KEYWORD)) {
            tasks.markDone(input);
            updateData();

        } else if (input.startsWith(Constants.TODO_KEYWORD)){
            Task new_task = tasks.addTodo(input);
            saveData(new_task);

        } else if (input.startsWith(Constants.DEADLINE_KEYWORD)){
            Task new_task = tasks.addDeadline(input);
            saveData(new_task);

        } else if (input.startsWith(Constants.EVENT_KEYWORD)){
            Task new_task = tasks.addEvent(input);
            saveData(new_task);

        } else if (input.startsWith(Constants.DELETE_KEYWORD)) {
            boolean deleteSuccess = tasks.delete(input);
            if (deleteSuccess) {
                updateData();
            }

        } else {
            System.out.println(Constants.INCORRECT_COMMAND_STRING);
        }
    }

    public void addItem(String input) {
        // TODO: add item
    }

    // ============================================== MAIN FUNCTION ============================================================
    /**
     * Start the program.
     */
    public void run() {
        printFella();
        printGreeting();

        //load current data if exists
        loadData();

        // main process
        while (isRunning) {
            this.getInput();
        }

        printGoodbye();
    }
}
