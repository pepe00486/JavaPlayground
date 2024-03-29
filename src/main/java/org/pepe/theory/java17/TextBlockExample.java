package org.pepe.theory.java17;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SuppressWarnings("java:S1192")
public class TextBlockExample {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextBlockExample.class);

    public static void main(String[] args) {
        basicSyntax();
        quotesExample();
        equalityExample();
        indentationExample();
        lineEndEscape();
        trailingSpaces();
        methodCallExample();
        formattedExample();
        jsonExample();
    }

    private static void basicSyntax() {
        String textBlock = """
                This is a text block.
                This is more text
                """;
        LOGGER.info(textBlock);
        LOGGER.info("end");
    }

    private static void quotesExample() {
        String textBlock = """
                This is a text block.
                        
                This is more "text"
                And yet more""";

        LOGGER.info(textBlock);
    }

    @SuppressWarnings({"squid:S5663", "squid:S2629", "java:S4973"})
    private static void equalityExample() {
        String regular = "This is a text block";
        String textBlock = """
                This is a text block""";

        LOGGER.info(String.valueOf(regular.equals(textBlock)));
        LOGGER.info(String.valueOf(regular == textBlock));
    }

    private static void indentationExample() {
        LOGGER.info("start");
        String textBlock = """
                                    This is a text block.
                                    This is more text
                """;
        LOGGER.info(textBlock);
        String textBlockTwo = """
                This is a text block.
                This is more text
                """;
        LOGGER.info(textBlockTwo);
        LOGGER.info("end");
    }

    private static void lineEndEscape() {
        String textBlock = """
                This is a text block. \
                This is more text
                """;
        LOGGER.info(textBlock);
    }

    private static void trailingSpaces() {
        String textBlock = """
                This is a text block.  \s
                """;
        LOGGER.info(textBlock);
    }

    private static void methodCallExample() {
        method("""
                This is a text block
                """);
    }

    private static void formattedExample() {
        String textBlock = """
                This is a %s.
                """.formatted("text block");
        String textBlockTwo = "This is a %s.".formatted("regular String");
        LOGGER.info(textBlock);
        LOGGER.info(textBlockTwo);
    }

    private static void jsonExample() {
        String textBlock = """
                {
                    "text": "This is a text block",
                    "text2": "This is more text"
                }""";
        //language=JSON
        String regularString = "{\n    \"text\": \"This is a text block\",\n    \"text2\": \"This is more text\"\n}";
        LOGGER.info(textBlock);
        LOGGER.info(regularString);
    }


    private static void method(String textBlock) {
        LOGGER.info(textBlock);
    }

}
