package animals.game;

import org.jetbrains.annotations.TestOnly;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Methods {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("App");

    public static boolean isNegated(String string) {
        if (string.matches(resourceBundle.getString("statement.negative.is") + " .*")) {
            return true;
        } else if (string.matches(resourceBundle.getString("statement.negative.can") + " .*")) {
            return true;
        } else return string.matches(resourceBundle.getString("statement.negative.has") + " .*");
    }

    public static String negateFact(String fact) {
        if (fact.matches(resourceBundle.getString("statement.positive.is") + " .*")) {
            return fact.replaceFirst(resourceBundle.getString("statement.is"),
                    resourceBundle.getString("statement.isn't"));
        } else if (fact.matches(resourceBundle.getString("statement.positive.can") + " .*")) {
            return fact.replaceFirst(resourceBundle.getString("statement.can"),
                    resourceBundle.getString("statement.can't"));
        } else {
            return fact.replaceFirst(resourceBundle.getString("statement.has"),
                    resourceBundle.getString("statement.doesn't have"));
        }
    }

    public static String positiveFact(String fact) {
        if (fact.matches(resourceBundle.getString("statement.negative.is") + " .*")) {
            return fact.replaceFirst(resourceBundle.getString("statement.isn't"),
                    resourceBundle.getString("statement.is"));
        } else if (fact.matches(resourceBundle.getString("statement.negative.can") + " .*")) {
            return fact.replaceFirst(resourceBundle.getString("statement.can't"),
                    resourceBundle.getString("statement.can"));
        } else {
            return fact.replaceFirst(resourceBundle.getString("statement.doesn't have"),
                    resourceBundle.getString("statement.has"));
        }
    }

    public static String animalFact(String animal, String unprocessedFact, boolean negate) {
        unprocessedFact = unprocessedFact.endsWith("?") ? unprocessedFact.substring(0, unprocessedFact.length() - 1) : unprocessedFact;
        unprocessedFact = unprocessedFact.endsWith(".") ? unprocessedFact : unprocessedFact + ".";

        if (unprocessedFact.matches(resourceBundle.getString("statement.positive.is") + " .*")) {
            String str = (resourceBundle.getString("statement.the") + " " + animal + " " +
                    (negate ? unprocessedFact.replaceFirst(resourceBundle.getString("statement.positive.is"), resourceBundle.getString("statement.isn't")) :
                            unprocessedFact.replaceFirst("statement.it", "")))
                    .replaceFirst(resourceBundle.getString("statement.it"), "");
            return str.replaceFirst("ĝi", "").replaceAll(" +", " ");

        } else if (unprocessedFact.matches(resourceBundle.getString("statement.positive.can") + " .*")) {
            String str = (resourceBundle.getString("statement.the") + " " + animal + " " +
                    (negate ? unprocessedFact.replaceFirst(resourceBundle.getString("statement.positive.can"), resourceBundle.getString("statement.can't")) :
                            unprocessedFact.replaceFirst("statement.can", "")))
                    .replaceFirst(resourceBundle.getString("statement.it"), "");

            return str.replaceFirst("ĝi", "").replaceAll(" +", " ");
        } else {
            String str = (resourceBundle.getString("statement.the") + " " + animal + " " +
                    (negate ? unprocessedFact.replaceFirst(resourceBundle.getString("statement.positive.has"), resourceBundle.getString("statement.doesn't have")) :
                            unprocessedFact.replaceFirst("statement.has", "")))
                    .replaceFirst(resourceBundle.getString("statement.it"), "");

            return str.replaceFirst("ĝi", "").replaceAll(" +", " ");
        }
    }

    public static String getQuery(String unprocessedFact) {
        unprocessedFact = unprocessedFact.toLowerCase().trim();
        unprocessedFact = unprocessedFact.endsWith(".") ? unprocessedFact.substring(0, unprocessedFact.length() - 1) : unprocessedFact;
        unprocessedFact = unprocessedFact.endsWith("?") ? unprocessedFact : unprocessedFact + "?";
        if (unprocessedFact.contains("is")) {
            return unprocessedFact.replaceFirst(resourceBundle.getString("statement.positive.is"), resourceBundle.getString("statement.query.is"));
        } else if (unprocessedFact.contains("can")) {
            return unprocessedFact.replaceFirst(resourceBundle.getString("statement.positive.can"), resourceBundle.getString("statement.query.can"));
        } else {
            return unprocessedFact.replaceFirst(resourceBundle.getString("statement.positive.has"), resourceBundle.getString("statement.query.has"));
        }
    }

    public static String removePrefix(String animal) {
        String language = resourceBundle.getString("language");

        if (language.equals("english")) {
            return  animal.replaceFirst("(an +)|(a +)", "");
        } else {
            return animal;
        }

    }

    public static String addPrefix(String animal) {
        String language = resourceBundle.getString("language");

        if (language.equals("english")) {
            if (!animal.startsWith("a ") && !animal.startsWith("an ")) {
                return animal.matches("[aeiouAEIOU].*") ? "an " + animal : "a " + animal;
            } else {
                return animal;
            }
        } else {
            return animal;
        }

    }

    public static boolean isValidFactFormat(String fact) {
        return fact.contains(resourceBundle.getString("statement.positive.is")) ||
                fact.contains(resourceBundle.getString("statement.positive.can")) ||
                fact.contains(resourceBundle.getString("statement.positive.has")) ||
                fact.contains(resourceBundle.getString("statement.negative.is")) ||
                fact.contains(resourceBundle.getString("statement.negative.can")) ||
                fact.contains(resourceBundle.getString("statement.negative.has")) ||
                fact.contains("ĝi");
    }

    public static boolean getUserInputAsBoolean() {
        String positiveValidUserInputsRegex = resourceBundle.getString("positiveAnswer.isCorrect");
        String negativeValidUserInputsRegex = resourceBundle.getString("negativeAnswer.isCorrect");
        String[] clarificationPhrases = resourceBundle.getStringArray("clarify");

        do {
            String userInput = new Scanner(System.in).nextLine().trim().toLowerCase();

            if (userInput.matches(positiveValidUserInputsRegex)) {
                return true;
            } else if (userInput.matches(negativeValidUserInputsRegex)) {
                return false;
            }
            System.out.println(clarificationPhrases[new Random().nextInt(clarificationPhrases.length)]);
        } while (true);
    }
}
