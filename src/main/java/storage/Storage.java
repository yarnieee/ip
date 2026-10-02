package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import constants.Constants;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;

public class Storage {

    private static final DateTimeFormatter STORAGE_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yy HHmm");

    private String saveFilePath;
    private final Constants c;
    
    public Storage(String inputFilePath, Constants constants) {
        this.saveFilePath = inputFilePath;
        this.c = constants;
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
    public void saveData(Task task) {
        if (!fileExists(saveFilePath)) {
            return;
        }

        try (FileWriter writer = new FileWriter(saveFilePath, true)) {
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
   public void updateData(TaskList tasks) {
    if (!fileExists(saveFilePath)) {
        return;
    }

    try (FileWriter writer = new FileWriter(saveFilePath)) {
        for (int i = 0; i < tasks.getSize(); i++) {
            writer.write(formatSaveString(tasks.getTask(i)) + System.lineSeparator());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /**
     * Returns string which is in the right format to be saved into smartfella.txt save file.
     * @param task
     * @return
     */
    private String formatSaveString(Task task) {
        String saveString;
        String isDoneString = (task.isDone()) ? c.MARKDONE_CHAR : "";

        if (task instanceof Deadline deadline) {
            saveString = c.DEADLINE_CHAR
                    + "," + isDoneString
                    + "," + deadline.getName()
                    + "," + STORAGE_DATE_FORMAT.format(deadline.getDeadline());
        } else if (task instanceof Event event) {
            saveString = c.EVENT_CHAR
                    + "," + isDoneString
                    + "," + event.getName()
                    + "," + STORAGE_DATE_FORMAT.format(event.getFrom())
                    + "," + STORAGE_DATE_FORMAT.format(event.getTo());
        } else {
            saveString = c.TODO_CHAR
                    + "," + isDoneString
                    + "," + task.getName();
        }

        return saveString;
    }
    
    public TaskList loadData(TaskList tasks) {
        // open path of ./data/SmartFella.txt
        if (!fileExists(saveFilePath)) {
            return tasks; // TODO: is it ok to just return? shld probably do something about this
        }

        //file exists. so load data from file
        File f = new File(saveFilePath);
        Task tempTask;

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                tempTask = parseSaveString(s.nextLine());
                tasks.addTaskObject(tempTask);
            }

            return tasks;
            
        } catch (FileNotFoundException e) {
            return tasks; // TODO: is it ok to just return? shld probably do something about this
        }
        
    }

    // TODO: how does the storage interact with the TaskList object?
    private Task parseSaveString(String saveString) {
        String[] temp = saveString.split(",");

        Task newTask;

        if (saveString.startsWith(c.DEADLINE_CHAR)){
            newTask = new Deadline(temp[2], LocalDateTime.parse(temp[3], STORAGE_DATE_FORMAT));

        } else if (saveString.startsWith(c.EVENT_CHAR)){
            newTask = new Event(temp[2],
                    LocalDateTime.parse(temp[3], STORAGE_DATE_FORMAT),
                    LocalDateTime.parse(temp[4], STORAGE_DATE_FORMAT));

        } else {
            newTask = new Todo(temp[2]);

        } 

        if (temp[1].equals(c.MARKDONE_CHAR)) {
            newTask.markDone();
        }

        return newTask;
    }

}
