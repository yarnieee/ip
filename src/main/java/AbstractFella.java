import java.util.Scanner;

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

    final String TODO_KEYWORD = "todo";
    final String DEADLINE_KEYWORD = "deadline";
    final String EVENT_KEYWORD = "event";
    final String DEADLINE_DELIM = "/by";
    final String EVENT_START_DELIM = "/from";
    final String EVENT_END_DELIM = "/to";

    final String INCORRECT_COMMAND_STRING = ">> cOMMAND nOT rECOGNISED ! ! !\n";
    final String INVALID_VALUE_STRING = ">> tHAT'S NOT A VALUE WE ACCEPT >:(\n";
    final String INVALID_NUMBER_STRING = ">> tHAT'S NOT A NUMBER! ! ! tRY AGAIN\n";

    /**
     * Tracking variables
     */
    static boolean isRunning = true;
    Task[] tasks = new Task[100];
    int nextFreeIndex = 0;

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
        System.out.println(">> " + tasks[nextFreeIndex-1].toString());
        System.out.println(">> nOW YOU HAVE " + nextFreeIndex + " TASKS IN THE LIST ! ! !");
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

        } else {
            System.out.println(INCORRECT_COMMAND_STRING);
        }
    }
    
    /**
     * Prints a list of all previous non-keyword commands, which have been saved as part of the To-do list.
     */
    private void getList() {
        int listCounter;

        for (int i = 0; i < nextFreeIndex; i++) {
            listCounter = i + 1;

            System.out.println(String.format("%d. %s", 
                listCounter,
                tasks[i].toString()));
        }
        System.out.println("");
    }

    /**
     * Takes the user input as param "cmd". If format of "cmd" is correct and within range, task at corresponding index will be marked as done/not done depending on "mark/unmark".
     * @param cmd
     */
    private void markDone(String cmd) {
        // check if input is valid
        if (!isValidMarkDone(cmd)) {
            return;
        }

        String[] data = cmd.split(" ");
        int index = Integer.parseInt(data[1]) - 1;
        
        //match with keyword & make change
        if (data[0].startsWith(MARK_KEYWORD)) {
            System.out.println(">> mARKED "
                + Integer.toString(index + 1)
                + "! ! !\n");
            tasks[index].markDone();
        } else {
            System.out.println(">> uNMARKED "
                + Integer.toString(index + 1)
                + "! ! !\n");
            tasks[index].unmarkDone();
        }
    }

    /**
     * Returns true if input matches intended mark done format, false otherwise
     * @return
     */
    private boolean isValidMarkDone(String input) {
        String[] data = input.split(" ");
        
        // check if is integer
        try {
            Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_NUMBER_STRING);
            return false;
        }

        // check if within range
        int index = Integer.parseInt(data[1]) - 1;
        if (index >= nextFreeIndex
                || index < 0) {
            System.out.println(INVALID_VALUE_STRING);
            return false;
        }

        return true;
    }
    // ============================================== ADD/VALIDATE ITEMS ============================================================
    /**
     * Add Todo item into list
     * @param input
     */
    private void addTodo(String input) {
        String description;

        // check if input is valid
        if (!isValidTodo(input)) {
            System.out.println(">> iNVALID TODO !\n");
            return;
        }

        //add todo
        description = input.substring(TODO_KEYWORD.length())
                            .strip();

        tasks[nextFreeIndex] = new Todo(description);
        nextFreeIndex++;

        printSuccessMessage();
    }

    /**
     * Returns true if input matches intended todo format, false otherwise
     * @return
     */
    private boolean isValidTodo(String input) {
        //check that TODO_KEYWORD is proceeded by a space
        //and that there exists content after the space
        boolean hasLength = (input.length() > TODO_KEYWORD.length()+1);
        if (!hasLength) {
            return false;
        }

        boolean hasSpace = (input.charAt(TODO_KEYWORD.length())==' ');
        if (!hasSpace) {
            return false;
        }

        return true;
    }

    /**
     * Add deadline item into list
     * @param input
     */
    private void addDeadline(String input) {
        String[] description;
        String text, deadline;

        //check if valid
        if (!isValidDeadline(input)) {
            System.out.println(">> iNVALID DEADLINE !");
            return;
        }

        //add
        description = input.substring(DEADLINE_KEYWORD.length())
                            .strip()
                            .split(DEADLINE_DELIM);
        text = description[0].strip();
        deadline = description[1].strip();

        tasks[nextFreeIndex] = new Deadline(text, deadline);
        nextFreeIndex++;

        printSuccessMessage();
    }

    /**
     * Returns true if input matches intended deadline format, false otherwise
     * @return
     */
    private boolean isValidDeadline(String input) {
        //check that DEADLINE_KEYWORD is proceeded by a space
        //and that there exists content after the space
        boolean hasLength = (input.length() > DEADLINE_KEYWORD.length()+1);
        if (!hasLength) {
            return false;
        }

        boolean hasSpace = (input.charAt(DEADLINE_KEYWORD.length())==' ');
        if (!hasSpace) {
            return false;
        }

        //check that DEADLINE_DELIM exists and that after splitting all substrings are non-empty
        String[] description;

        description = input.substring(DEADLINE_KEYWORD.length())
                            .strip()
                            .split(DEADLINE_DELIM);

        if (description.length != 2) {
            return false;
        }

        if (description[0].strip().isEmpty()
            || description[1].strip().isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Add event item into list
     * @param input
     */
    private void addEvent(String input) {
        String[] description;
        String text, from, to;

        //check if valid
        if (!isValidEvent(input)) {
            System.out.println(">> iNVALID EVENT !");
            return;
        }

        //add
        description = input.substring(EVENT_KEYWORD.length())
                            .strip()
                            .split(EVENT_START_DELIM + "|" + EVENT_END_DELIM);

        text = description[0].strip();
        from = description[1].strip();
        to = description[2].strip();

        //add
        tasks[nextFreeIndex] = new Event(text, from, to);
        nextFreeIndex++;

        printSuccessMessage();
    }

    /**
     * Returns true if input matches intended event format, false otherwise
     * @return
     */
    private boolean isValidEvent(String input) {
        // TODO: implement event validation
        //check that EVENT_KEYWORD is proceeded by a space
        //and that there exists content after the space
        boolean hasLength = (input.length() > EVENT_KEYWORD.length()+1);
        if (!hasLength) {
            return false;
        }

        boolean hasSpace = (input.charAt(EVENT_KEYWORD.length())==' ');
        if (!hasSpace) {
            return false;
        }

        String[] description;
        description = input.substring(EVENT_KEYWORD.length())
                            .strip()
                            .split(EVENT_START_DELIM + "|" + EVENT_END_DELIM);

        if (description.length != 3) {
            return false;
        }

        if (description[0].strip().isEmpty()
            || description[1].strip().isEmpty()
            || description[2].strip().isEmpty()) {
            return false;
        }


        return true;
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
