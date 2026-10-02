package parser;

import constants.Constants;

import java.util.Scanner;

public class Parser {
    private final Constants c;

    public Parser(Constants c) {
        this.c = c;
    }


    /**
     * Receives user commands and executes corresponding actions.
     */
    public String getInput() {
        String input;

        Scanner scanner = new Scanner(System.in); //should be closed at some point?
        System.out.print(c.INPUT_MARKER_STRING); // your inputs will be denoted by triple ">>>"
        input = scanner.nextLine();

        return input;
    }
}
