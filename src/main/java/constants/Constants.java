package constants;

public final class Constants {

    private Constants() {
    }
    
    public static final String INPUT_MARKER_STRING = ">>> ";
    public static final String BYE_KEYWORD = "bye";
    public static final String LIST_KEYWORD = "list";
    public static final String MARK_KEYWORD = "mark";
    public static final String UNMARK_KEYWORD = "unmark";
    public static final String DELETE_KEYWORD = "delete";

    public static final String TODO_KEYWORD = "todo";
    public static final String DEADLINE_KEYWORD = "deadline";
    public static final String EVENT_KEYWORD = "event";
    public static final String DEADLINE_DELIM = "/by";
    public static final String EVENT_START_DELIM = "/from";
    public static final String EVENT_END_DELIM = "/to";
    
    public static final String TODO_CHAR = "T"; //slightly misleading given that it is a string not a char
    public static final String DEADLINE_CHAR = "D";
    public static final String EVENT_CHAR = "E";
    public static final String MARKDONE_CHAR = "X";

    public static final String FELLA_TEXT_MARKER = ">> ";



    
    public static final String FELLA_STRING = "";
    public static final String GREETING_STRING = "<greeting message>\n";
    public static final String GOODBYE_STRING = "<goodbye message>\n";

    public static final String MARKED_SUCCESS_STRING = FELLA_TEXT_MARKER + "mARKED ";
    public static final String UNMARKED_SUCCESS_STRING = FELLA_TEXT_MARKER + "uNMARKED ";
    public static final String DELETED_SUCCESS_STRING = FELLA_TEXT_MARKER + "dELETED ";
    public static final String ADD_SUCCESS_STRING = FELLA_TEXT_MARKER + "aDDED INTO LIST !";
    public static final String TASK_COUNT_STRING1 = FELLA_TEXT_MARKER + "nOW YOU HAVE ";
    public static final String TASK_COUNT_STRING2 =  " TASKS IN THE LIST ! ! !";

    public static final String INCORRECT_COMMAND_STRING = FELLA_TEXT_MARKER + "cOMMAND nOT rECOGNISED ! ! !\n";
    public static final String INVALID_VALUE_STRING = FELLA_TEXT_MARKER + "tHAT TASK NUMBER IS NOT IN THE LIST ! ! !\n";
    public static final String MISSING_TASK_NUMBER_STRING = FELLA_TEXT_MARKER + "tELL ME WHICH TASK TO MARK OR UNMARK ! ! !\n";
    public static final String INVALID_NUMBER_STRING = FELLA_TEXT_MARKER + "tHAT TASK NUMBER IS NOT A NUMBER ! ! !\n";
    public static final String ALREADY_MARKED_STRING = FELLA_TEXT_MARKER + "tHAT TASK IS ALREADY MARKED ! ! !\n";
    public static final String ALREADY_UNMARKED_STRING = FELLA_TEXT_MARKER + "tHAT TASK IS ALREADY UNMARKED ! ! !\n";
    public static final String TODO_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER + "tODO NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    public static final String TODO_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "tODO DESCRIPTION CANNOT BE EMPTY ! ! !\n";
    public static final String DEADLINE_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER + "dEADLINE NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    public static final String DEADLINE_COUNT_ERROR_STRING = FELLA_TEXT_MARKER + "dEADLINE NEEDS A DESCRIPTION AND ONE /by DATE ! ! !\n";
    public static final String DEADLINE_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "dEADLINE DESCRIPTION AND DATE CANNOT BE EMPTY ! ! !\n";
    public static final String EVENT_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    public static final String EVENT_COUNT_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT NEEDS A DESCRIPTION, ONE /from, AND ONE /to ! ! !\n";
    public static final String EVENT_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT DESCRIPTION, START, AND END CANNOT BE EMPTY ! ! !\n";
}
