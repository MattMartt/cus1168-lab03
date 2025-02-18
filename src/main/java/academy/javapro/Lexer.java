package academy.javapro;

import java.util.*;
import java.util.regex.*;

public class Lexer {
    private static final Pattern[] PATTERNS = {
            Pattern.compile("\\s+"),                                       // whitespace
            Pattern.compile("\\b(if|else|for|while|int|float|String)\\b"), // keywords
            Pattern.compile("\\b\\d+(\\.\\d+)?\\b"),                      // literals
            Pattern.compile("==|<=|>=|!=|&&|\\|\\||[+\\-*/=<>!]"),        // operators
            Pattern.compile("[;,.(){}\\[\\]]"),                           // punctuation
            Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b")               // identifiers
    };

    private static final String[] TYPES = {
            "WHITESPACE",
            "KEYWORD",
            "LITERAL",
            "OPERATOR",
            "PUNCTUATION",
            "IDENTIFIER"
    };

    private String input;
    private List<String[]> tokens;
    private int position;

    /**
     * Initialize the lexer with the input string  
     * and Instance variables
     * Set the initial position to 0
     *
     * @param input The source code string to be tokenized
     */
    public Lexer(String input) {
        // Your code here
        this.input = input;
        this.tokens = new ArrayList<>();
        this.position = 0;
    }

    /**
     * Process the input string and break it into tokens
     * 1. Create a while loop that continues while position < input.length()
     * 2. Get the remaining input using substring(position)
     * 3. Try to match each pattern in PATTERNS array:
     *    - Create a matcher using pattern.matcher(remainingInput)
     *    - Use matcher.lookingAt() to check if it matches at current position
     *    - If match found:
     *      a. Get the matched text using matcher.group()
     *      b. If not whitespace, add new token to tokens list
     *      c. Update position by adding length of matched text
     * 4. If no pattern matches, throw RuntimeException for invalid input
     */
    public void tokenize() {
        // Your code here
        while(position < input.length()) {
            String remainingInput = input.substring(position);
            boolean matchFound = false;
            for (int i = 0; i < PATTERNS.length; i++) {
                Matcher matcher = PATTERNS[i].matcher(remainingInput);
                if (matcher.lookingAt()) {
                    String match = matcher.group();
                    if (i != 0) {
                        tokens.add(new String[]{TYPES[i], match});
                    }
                    position += match.length();
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                throw new RuntimeException("Invalid input at position: " + position);
            }
        }
    }

    /**
     * Return the list of tokens
     * 1. Return the tokens list containing all found tokens
     * 2. Each token should be a String array with two elements:
     *    - First element: Token type (from TYPES array)
     *    - Second element: Token value (the actual text)
     *
     * @return List<String [ ]> The list of tokens
     */
    public List<String[]> getTokens() {
        // Your code here
        return tokens;
    }
        

    public static void main(String[] args) {
        String code = "int x = 10; if (x > 5) { x = x + 1; }";
        Lexer lexer = new Lexer(code);
        lexer.tokenize();
        for (String[] token : lexer.getTokens()) {
            System.out.println(token[0] + ": " + token[1]);
        }
    }
}
