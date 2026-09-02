import java.util.Scanner;

public abstract class SmartFella {
    /**
     *  The SmartFella class runs a To-do list manager with an Interesting personality. The SmartFella class creates and tracks instances of the Task class.
     * This is the main file which runs the program.
    */
    
    private final String BYE_KEYWORD = "bye";
    private final String LIST_KEYWORD = "list";
    private final String MARK_KEYWORD = "mark";
    private final String UNMARK_KEYWORD = "unmark";
    private final String TODO_KEYWORD = "todo";
    private final String DEADLINE_KEYWORD = "deadline";
    private final String EVENT_KEYWORD = "event";
    private final String DEADLINE_DELIM = "/by";
    private final String EVENT_START_DELIM = "/from";
    private final String EVENT_END_DELIM = "/to";

    private final String GREET_STRING = ">> bEHOLD, A sMART fELLA ! ! !\n" + //
                        ">> i SHALL ANSWER YOUR BURNING QUESTIONS ! ! !\n";
    private final String GOODBYE_STRING = ">> fAREWELL sTRANGER, WE SHALL MEET AGAIN ! ! !\n\n";
    private final String INCORRECT_COMMAND_STRING = ">> cOMMAND nOT rECOGNISED ! ! !\n";
    private final String INVALID_VALUE_STRING = ">> tHAT'S NOT A VALUE WE ACCEPT >:(\n";
    private final String INVALID_NUMBER_STRING = ">> tHAT'S NOT A NUMBER! ! ! tRY AGAIN\n";


    private final String INPUT_MARKER_STRING = ">>> ";

    private final String SMARTFELLA = "                    ...                                                 \n"+
            "               .:------:                         -----:.                \n"+
            "              ---------.                         :-------.              \n"+
            "            .-------.                              :-------.            \n"+
            "           .--------                                --------            \n"+
            "          .--------.                                --------.           \n"+
            "          ---------.                               :---------.          \n"+
            "         .----------                              -----------:          \n"+
            "         :----------.                            .-----------:          \n"+
            "         :-----------            .-+++++==++****+=-----------:          \n"+
            "          -----------=+*##*++*###**++**#*+++***#*+=----------.          \n"+
            "          --------=+***##***#**#*+===+**++#*+**####*===-----.           \n"+
            "          :----==+*#####***#%#++*+=--=++++*#*++*#%%##*+===--            \n"+
            "          ---=+*#######*++*###+++=--=++++++*#*++*######**++==.          \n"+
            "          +**##%%######*+*##**+++===++++++++***++=-::-=++*****           ░▒▓███████▓▒░▒▓██████████████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░▒▓████████▓▒░\n"+
            "         :####%##*+===+++***+++**++++++++=++**+-.    ..:--=++*=         ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "         -#####*-:.    .:=***++***++++++==+**+:.      ...-====*-        ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "         *##**+-::.      .-*##*****++=====+*+-..      ..-+****+*         ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░  ░▒▓█▓▒░    \n"+
            "        :*+**##+-:.     ..:+#%###**++++====++-...   ...:=++++***=              ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "        -*#####*=...   ..::-+*****+==+++=----::.....:-======+***=              ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "        -##**++++=-:..:-======+++***%@@%+-::::::-==-----==+++**#=       ░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "        =##**+++=+++++****=====+#@@@@@@@*-::::::-------=++++**##=       \n"+
            "        =###****++++===+++=====+%@@@@@@@#-::::::--======++***###=                     ░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░      ░▒▓█▓▒░       ░▒▓██████▓▒░  \n"+
            "        =%%##***+++++++++++++==*@@@@@@@@#=-------==+++++++*#####=                     ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "        +%####******#*****+++==*@@@@@@@@%+-::-----==++*######*#%                      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "        +%#******####**++++++**%@@@@@@@@@+-===+++++**###***####%                      ░▒▓██████▓▒░ ░▒▓██████▓▒░ ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓████████▓▒░ \n"+
            "        +@%####**#######**+++*#@@@@@@@@@@#****+===++******######.                     ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "        +@%%%###******######*##@@@@@@@@@@@*****++**+++++**######*:                    ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "         #%%%##**++++++*******#@@@@@@@@@@@#++++++++===++**#####***=                   ░▒▓█▓▒░      ░▒▓████████▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "          %%####**+====++++++*%@@@@@@@@@@@%*+=======+++**######****#.   \n"+
            "          *@%#####*+=========*@@@@@@@@@@@@@*--===++***######**++++***   \n"+
            "          =@%%%%#####*++++==+%@@@@@@@@@@@@@#+=++**########***+====++**- \n"+
            "          %%%%%##########***#@@@@@@@@@@@@@@@%##########****++====-==++**\n"+
            "         #%%#########%%%%%##%@@@@@@@@@@@@@@@@@@%%%%#####**+++=----====++\n"+
            "        *###**###%%%%%%%%%@@@@@@@@@@@@@@@@@@@@%%%%%###%%#*++++====+==+++\n"+
            "      .####**#%%%%%####%%%@@@@@@@@@@@@@@@@@@@%%%%#########*++=++++++=+++\n"+
            "     .%%####%%%##########%%@@@@@@@@@%%%@@@@@%%%%############++===++++==+\n"+
            "     %%###%%%%#########%%%%%@@@@@@%###*#@@@%%%%%%############+=====+===-\n";

    private final String FARTSMELLA = "@@@@@@@@*=-=#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%##*==+#######\n"+
            "@@@@@@@@@#+::-*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%###*+--+*#######\n"+
            "@@@@@@@@@@%*=-:-=*#%@@@@@@%%%%@@@@@@@@@@@@@@%%%%%%%%##**+=---+*#######*\n"+
            "@@@@@@@@@@@%#*==-==+**##***++++++******########**+++++==---=*########**\n"+
            "@@@@@@@@@@@@%**+=++*******++============+++++++======---:-=*########***\n"+
            "@@@@@@@@@@@@%**+**###******++++=====-====+++======-:::::-+*#########***\n"+
            "@@@@@@@@@@@@%***######**+**+++====---=======-==--:.::::-+*##########***\n"+
            "@@@@@@@@@@@@%######**##*+++===============-----:::..:.:=*#########*****\n"+
            "@@@@@@@@@@@%%##***+++*#*+========++===+*+=-::::::::..:+###########*****\n"+
            "@@@@@@@@@@%%#*+==++=+***+========++=-=+*+=-:::::.....-############*****\n"+
            "@@@@@@@@@%%%#+=:.:-=+**++===++=====-:-==--:::::.....:+###########******\n"+
            "@@@@@@@@%%%%%*==:  :+###*+=+*=-----:::.....::::.....:+##########*******\n"+
            "@@@@@@@@%%%%#=:     =%%%*++++-:::::.        .........-*########********\n"+
            "@@@@@@@@@%###+-:  .:+%%%#**+=-::::.         .........:=*######*********   ░▒▓████████▓▒░▒▓██████▓▒░░▒▓███████▓▒░▒▓████████▓▒░\n"+
            "@@@@@@@@@%####*******#%%%%#*+---:.         .:::.......-+######*********   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "@@@@@@@@@@%%%%@@@%%##%%@@%#*+---:..      ..::::..   ..:=*###******##**+   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "@@@@@@@@@@@@@@@@@@@@@@@@@%#*=---:-:::....::::.....  ..:+*#**=----+**++=   ░▒▓██████▓▒░░▒▓████████▓▒░▒▓███████▓▒░  ░▒▓█▓▒░    \n"+
            "%%%%%%%%%%@@@@@@@@@@@@@@%%*+-:---==--:::::............-*****+-::-+##*++   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "*%%%%%***#%##@@@@@@@%*++++=-::--=====-::::...........:-+++==:..           ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "++***##########%##%%*+--:::.::--=====--::::.........::-:.                 ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░    \n"+
            "=####################*=:...::--======--:::::::::::::--:..              \n"+
            "###############*####*+=:::---------===----::----:-----:..                     ░▒▓███████▓▒░▒▓██████████████▓▒░░▒▓████████▓▒░▒▓█▓▒░      ░▒▓█▓▒░       ░▒▓██████▓▒░  \n"+
            "################*##*+=----------=--==------------:::--::..                   ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "##########*#######%@#+=-::::----=====----------:::::---:.....                ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "#*####+####+####=#*@@@%*+=---======-------:::::::::::::::......               ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓██████▓▒░ ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓████████▓▒░ \n"+
            "##+###=*###-***+-**@@@@@%##***+++==--:::::......................                    ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "**=+**==+++----=++@@@@@@@@@@%#**+=--::.....................  .....                  ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"+
            ":...::...=+**+++*@@@@@@@@@@@@@@%#+=-:....  .................   ........      ░▒▓███████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ \n"+
            "...   ..:::-#@@@@@@@@@@@@@@@@@@@@*=:..  .....................         .\n"+
            ":  ..::::::=@@@@@@@@@@@@@@@@@@@@@#=:. ...:::::::::::..........         \n"+
            "...::::...:=@@@@@@@@@@@@@@@@@@@@%*-::::----====-==--::::.....          \n"+
            "::.......  .=@@@@@@@@@@@@@@@@%#********+++++============--::.          \n"+
            "-:::..     .=%@@@@@@@@@@@@@@@%#****+++*+++==============--::.          \n"+
            "++==+-=====--+@@@@@@@@@@@@@@@@@%#*+===++++*********+++++==-::.         \n"+
            "=-===-=--=+===*@@@@@@@@@@@%%%####*+===+*********++========-:::.        \n"+
            "=-==--======+==#@@@@@@@%#***+++***+===**####**++==========---:.        \n"+
            "+===--====--=+++#@@@@%#****++++***#*+**####**++++++++++++++==-:.       \n"+
            "+=+========--=+**#@@%#*****++++++#%%########**************+++=-:..     \n"+
            "+=++=======--==+*#%@%##****+++=++#%%%#######**********+++++++++=-:..   \n"+
            "====----====---=****###***++++==+*%%%%%%###********+++++++***#***+=:.. \n"+
            "====----=++====-=+*++####***+++++*#%%%%%%###******++++***##%%%%%%%#*=:.\n";
    

    private final int WHICH_FELLA = (int)(Math.random() * 101) % 2;

    private static boolean isRunning = true;
    private Task[] tasks = new Task[100];
    private int nextFreeIndex = 0;
    

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
        String[] data = cmd.split(" ");
        
        // check if is integer
        try {
            Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_NUMBER_STRING);
            return;
        }

        // check if within range
        int index = Integer.parseInt(data[1]) - 1;
        if (index >= nextFreeIndex
                || index < 0) {
            System.out.println(INVALID_VALUE_STRING);
            return;
        }

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
     * Receives user commands and executes corresponding actions.
     */
    public void getInput() {
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
    private void matchInput(String input) {
        if (input.equals(BYE_KEYWORD)) {
            isRunning = false;

        } else if (input.equals(LIST_KEYWORD)){
            getList();

        } else if (input.startsWith(MARK_KEYWORD)
                || input.startsWith(UNMARK_KEYWORD)) {
            markDone(input);

        } else if (input.startsWith(TODO_KEYWORD)){
            System.out.println(">> aDDED TODO !");
            addTodo(input);

        } else if (input.startsWith(DEADLINE_KEYWORD)){
            System.out.println(">> aDDED DEADLINE !");
            addDeadline(input);

        } else if (input.startsWith(EVENT_KEYWORD)){
            System.out.println(">> aDDED EVENT !");
            addEvent(input);

        } else {
            System.out.println(INCORRECT_COMMAND_STRING);
        }
    }

    /**
     * Add Todo item into list
     * @param input
     */
    private void addTodo(String input) {
        String description;

        // check if input is valid
        if (!isValidTodo(input)) {
            System.out.println(">> iNVALID TODO !");
            return;
        }

        //add todo
        description = input.substring(TODO_KEYWORD.length())
                            .strip();

        tasks[nextFreeIndex] = new Todo(description);
        nextFreeIndex++;

        printSuccessMessage();
    }

    private void printSuccessMessage() {
        //print result
        System.out.println(">> " + tasks[nextFreeIndex-1].toString());
        System.out.println(">> nOW YOU HAVE " + nextFreeIndex + " TASKS IN THE LIST ! ! !");
        System.out.println();
    }

    /**
     * Returns true if input matches intended todo format, false otherwise
     * @return
     */
    private boolean isValidTodo(String input) {
        // TODO: implement todo validation
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
        // TODO: implement deadline validation
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
        return true;
    }    

    /**
     * Prints a very very large ASCII image of a very welcoming fella. Which fella it is depends on the constant WHICH_FELLA which is randomised upon initialisation of the SmartFella object.
     */
    public void summonFella() {

        switch (WHICH_FELLA) {
            case 0:
                System.out.println(SMARTFELLA);
                break;
            case 1:
                System.out.println(FARTSMELLA);
                break;
        }

    }

    /**
     * Start the program.
     */
    public void main(String[] args) {
        // greet
        summonFella();
        System.out.println(GREET_STRING);

        // main process
        while (isRunning) {
            this.getInput();
        }

        // end
        System.out.println(GOODBYE_STRING);
    }
}
