import java.util.Scanner;

public class SmartFella {
    /**
     *  The SmartFella class runs a To-do list manager with an Interesting personality. The SmartFella class creates and tracks instances of the Task class.
     * This is the main file which runs the program.
    */
    private final String TODO_KEYWORD = "todo";
    private final String DEADLINE_KEYWORD = "deadline";
    private final String EVENT_KEYWORD = "event";
    private final String DEADLINE_DELIM = "/by";
    private final String EVENT_START_DELIM = "/from";
    private final String EVENT_END_DELIM = "/to";

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
            System.out.println(">> tHAT'S NOT A NUMBER! ! ! tRY AGAIN\n");
            return;
        }

        // check if within range
        int index = Integer.parseInt(data[1]) - 1;
        if (index >= nextFreeIndex
                || index < 0) {
            System.out.println(">> tHAT'S NOT A VALUE WE ACCEPT >:(\n");
            return;
        }

        //match with keyword & make change
        if (data[0].startsWith("mark ")) {
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
        isValidTodo();
        Scanner scanner = new Scanner(System.in); //should be closed at some point?
        System.out.print(">>> "); // your inputs will be denoted by triple ">>>"
        input = scanner.nextLine();

        // keyword matching
        matchInput(input);
    }

    /**
     * Match input to specific keywords and perform related actions.
     * @param input
     */
    private void matchInput(String input) {
        if (input.equals("bye")) {
            isRunning = false;

        } else if (input.equals("list")){
            getList();

        } else if (input.startsWith("mark ")
                || input.startsWith("unmark ")) {
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
            System.out.println(">> cOMMAND nOT rECOGNISED"
                + input.toUpperCase()
                + "! ! !\n");
        }
    }

    /**
     * Adds a new item to the task list maintained by SmartFella.
     * @param input
     */
    // public void addToTasks(String input) {
    //     tasks[nextFreeIndex] = new Task(input);
    //     nextFreeIndex++;
    // }

    private void addTodo(String input) {
        String description;

        // check if input is valid
        if (!isValidTodo()) {
            System.out.println(">> iNVALID TODO !");
            return;
        }
        // if (input.length() < TODO_KEYWORD

        // if (input.startsWith(TODO_KEYWORD)) {
        //     text = text.substring(prefix.length());
        // }

        //add todo
        description = input.substring(TODO_KEYWORD.length())
                            .strip();

        tasks[nextFreeIndex] = new Todo(description);
        nextFreeIndex++;
    }

    private boolean isValidTodo() {
        // TODO: implement todo validation
        return true;
    }

    private void addDeadline(String input) {
        String[] description;
        String text, deadline;

        //check if valid
        if (!isValidDeadline()) {
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
    }

    private boolean isValidDeadline() {
        // TODO: implement deadline validation
        return true;
    }

    private void addEvent(String input) {
        String[] description;
        String text, from, to;

        //check if valid
        if (!isValidDeadline()) {
            System.out.println(">> iNVALID EVENT !");
            return;
        }

        //add
        description = input.substring(DEADLINE_KEYWORD.length())
                            .strip()
                            .split(EVENT_START_DELIM + "|" + EVENT_END_DELIM);

        text = description[0].strip();
        from = description[1].strip();
        to = description[2].strip();

        //add
        tasks[nextFreeIndex] = new Event(text, from, to);
        nextFreeIndex++;
    }

    private boolean isValidEvent() {
        // TODO: implement event validation
        return true;
    }    

    /**
     * Prints a very very large ASCII image of a very welcoming fella. Which fella it is depends on the constant WHICH_FELLA which is randomised upon initialisation of the SmartFella object.
     */
    public void summonFella() {
        String SmartFella = "                    ...                                                 \n"+
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


        String FartSmella = "@@@@@@@@*=-=#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%##*==+#######\n"+
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

        switch (WHICH_FELLA) {
            case 0:
                System.out.println(SmartFella);
                break;
            case 1:
                System.out.println(FartSmella);
                break;
        }

    }

    /**
     * Start the program.
     */
    public void main(String[] args) {
        // greet
        summonFella();
        String greet =  ">> bEHOLD, A sMART fELLA ! ! !\n" + //
                        ">> i SHALL ANSWER YOUR BURNING QUESTIONS ! ! !\n";
        System.out.println(greet);

        // main process
        while (isRunning) {
            this.getInput();
        }

        // end
        String goodbye = ">> fAREWELL sTRANGER, WE SHALL MEET AGAIN ! ! !\n\n";
        System.out.println(goodbye);
    }
}
