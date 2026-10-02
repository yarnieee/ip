package constants;

public class Constants {

    public Constants() {
    }
    
    // ======================= CONSTANTS =============================
    public String INPUT_MARKER_STRING = ">>> ";
    public String BYE_KEYWORD = "bye";
    public String LIST_KEYWORD = "list";
    public String MARK_KEYWORD = "mark";
    public String UNMARK_KEYWORD = "unmark";
    public String DELETE_KEYWORD = "delete";

    public String TODO_KEYWORD = "todo";
    public String DEADLINE_KEYWORD = "deadline";
    public String EVENT_KEYWORD = "event";
    public String DEADLINE_DELIM = "/by";
    public String EVENT_START_DELIM = "/from";
    public String EVENT_END_DELIM = "/to";
    public String LIST_SORT_DELIM = "/sort";
    public String LIST_FILTER_DELIM = "/filter";
    public String LIST_NAME_FIELD = "name";
    public String LIST_DATE_FIELD = "date";
    public String LIST_ASCENDING = "asc";
    public String LIST_DESCENDING = "desc";
    public String LIST_DONE_FILTER = "done";
    public String LIST_NOT_DONE_FILTER = "notdone";
    
    public String TODO_CHAR = "T"; //slightly misleading given that it is a string not a char
    public String DEADLINE_CHAR = "D";
    public String EVENT_CHAR = "E";
    public String MARKDONE_CHAR = "X";

    public String FELLA_TEXT_MARKER = ">> ";

    // =================== OVERRIDEABLE CONSTANTS ========================
    public String FELLA_STRING = "";
    
    public String GREETING_STRING = "<greeting message>\n";
    public String GOODBYE_STRING = "<goodbye message>\n";

    public String MARKED_SUCCESS_STRING = FELLA_TEXT_MARKER + "mARKED ";
    public String UNMARKED_SUCCESS_STRING = FELLA_TEXT_MARKER + "uNMARKED ";
    public String DELETED_SUCCESS_STRING = FELLA_TEXT_MARKER + "dELETED ";
    public String ADD_SUCCESS_STRING = FELLA_TEXT_MARKER + "aDDED INTO LIST !";
    public String TASK_COUNT_STRING1 = FELLA_TEXT_MARKER + "nOW YOU HAVE ";
    public String TASK_COUNT_STRING2 =  " TASKS IN THE LIST ! ! !";

    public String INCORRECT_COMMAND_STRING = FELLA_TEXT_MARKER + "cOMMAND nOT rECOGNISED ! ! !\n";
    public String INVALID_VALUE_STRING = FELLA_TEXT_MARKER + "tHAT TASK NUMBER IS NOT IN THE LIST ! ! !\n";
    public String MISSING_TASK_NUMBER_STRING = FELLA_TEXT_MARKER + "tELL ME WHICH TASK TO MARK OR UNMARK ! ! !\n";
    public String INVALID_NUMBER_STRING = FELLA_TEXT_MARKER + "tHAT TASK NUMBER IS NOT A NUMBER ! ! !\n";
    public String ALREADY_MARKED_STRING = FELLA_TEXT_MARKER + "tHAT TASK IS ALREADY MARKED ! ! !\n";
    public String ALREADY_UNMARKED_STRING = FELLA_TEXT_MARKER + "tHAT TASK IS ALREADY UNMARKED ! ! !\n";
    public String TODO_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER + "tODO NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    public String TODO_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "tODO DESCRIPTION CANNOT BE EMPTY ! ! !\n";
    public String DEADLINE_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER + "dEADLINE NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    public String DEADLINE_COUNT_ERROR_STRING = FELLA_TEXT_MARKER + "dEADLINE NEEDS A DESCRIPTION AND ONE /by DATE ! ! !\n";
    public String DEADLINE_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "dEADLINE DESCRIPTION AND DATE CANNOT BE EMPTY ! ! !\n";
    public String EVENT_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT NEEDS A SPACE BEFORE ITS DESCRIPTION ! ! !\n";
    public String EVENT_COUNT_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT NEEDS A DESCRIPTION, ONE /from, AND ONE /to ! ! !\n";
    public String EVENT_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT DESCRIPTION, START, AND END CANNOT BE EMPTY ! ! !\n";
    public String EVENT_ORDER_ERROR_STRING = FELLA_TEXT_MARKER + "eVENT START TIME MUST BE EARLIER THAN END TIME ! ! !\n";
    public String LIST_EMPTY_ERROR_STRING = FELLA_TEXT_MARKER + "tHERE ARE NO TASKS IN THE LIST ! ! !\n";
    public String LIST_FORMAT_ERROR_STRING = FELLA_TEXT_MARKER
            + "lIST OPTIONS MUST USE /sort name/date asc/desc AND /filter todo/deadline/event/done/notdone ! ! !\n";
    public String LIST_NO_MATCHING_TASKS_ERROR_STRING = FELLA_TEXT_MARKER
            + "nO TASKS MATCH THE SELECTED FILTER ! ! !\n";
}
