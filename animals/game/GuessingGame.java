package animals.game;

import animals.Print;
import animals.data.Tree;

import java.lang.invoke.MethodHandle;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class GuessingGame {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("App");

    private static Scanner scanner = new Scanner(System.in);
    private final Tree root;
    private Tree currentNode;

    public GuessingGame(Tree tree) {
        this.root = tree;
        do {
            run();

            String[] playAgain = resourceBundle.getStringArray("game.again");
            System.out.println("\n" + playAgain[new Random().nextInt(playAgain.length)]);
        } while (Methods.getUserInputAsBoolean());
    }

    private void run() {

        System.out.println("\n" + resourceBundle.getString("game.think") + "\n" +
                resourceBundle.getString("game.enter"));
        scanner.nextLine();
        currentNode = root;

        if (!currentNode.isQueryNode) {
            System.out.println(resourceBundle.getString("statement.query.is") + " " +
                    currentNode.animalWithPrefix + "?");
            //System.out.println("Is it " + currentNode.animalWithPrefix + "?");
            boolean anotherYesOrNo = Methods.getUserInputAsBoolean();

            if (anotherYesOrNo) {
                System.out.println(resourceBundle.getString("game.win"));
            } else {
                String[] newAnimalInfo = getAnotherAnimal();
                distinguishAnimals(newAnimalInfo);
            }
            return;
        }

        System.out.println(currentNode.query);
        do {
            boolean yesOrNo = Methods.getUserInputAsBoolean();
            currentNode = yesOrNo ? currentNode.yes : currentNode.no;
            if (currentNode.isQueryNode) {
                System.out.println(currentNode.query);
            } else {
                System.out.println(resourceBundle.getString("statement.query.is") + " " +
                        currentNode.animalWithPrefix + "?");
                //System.out.println("Is it " + currentNode.animalWithPrefix + "?");
                boolean anotherYesOrNo = Methods.getUserInputAsBoolean();

                if (anotherYesOrNo) {
                    System.out.println(resourceBundle.getString("game.win"));
                } else {
                    String[] newAnimalInfo = getAnotherAnimal();
                    distinguishAnimals(newAnimalInfo);
                }
                break;
            }
        } while (true);
    }

    private String[] getAnotherAnimal() {
        System.out.println(resourceBundle.getString("game.giveUp"));
        //System.out.println("I give up. What animal do you have in mind?");
        String animal = scanner.nextLine().toLowerCase().trim();
        animal = Methods.removePrefix(animal);
        String animalWithPrefix = Methods.addPrefix(animal);
        return new String[] {animal, animalWithPrefix};
    }

    private void distinguishAnimals(String[] secondAnimal) {
        String unprocessedFact;
        while (true) {
            Print<String> prompt = (Print<String>) resourceBundle.getObject("statement.prompt");
            prompt.prompt(currentNode.animalWithPrefix, secondAnimal[1]);
            /*
            System.out.println("Specify a fact that distinguishes " + currentNode.animalWithPrefix + " from " + secondAnimal[1] + ".\n" +
                    "The sentence should satisfy one of the following templates:\n" +
                    "- It can ...\n- It has ...\n- It is a/an ...\n");
             */
            unprocessedFact = scanner.nextLine().trim().toLowerCase();
            if (Methods.isValidFactFormat(unprocessedFact)) {
                break;
            } else {
                System.out.println(resourceBundle.getString("statement.error"));
            }
        }

        boolean forSecondAnimal;
        UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("game.isCorrect");
        System.out.println(operator.apply(secondAnimal[1]));
        //System.out.println("Is the statement correct for " + secondAnimal[1] + "?");

        forSecondAnimal = Methods.getUserInputAsBoolean();
        if (Methods.isNegated(unprocessedFact)) {
            unprocessedFact = Methods.positiveFact(unprocessedFact);
            forSecondAnimal = !forSecondAnimal;
        }

        String factQuery = Methods.getQuery(unprocessedFact);
        String fact = unprocessedFact;

        Print<String> learnedDistinguish = (Print<String>) resourceBundle.getObject("game.learned");
        learnedDistinguish.learnedDistinguish(
                Methods.animalFact(currentNode.animal, unprocessedFact, forSecondAnimal),
                Methods.animalFact(secondAnimal[0], unprocessedFact, !forSecondAnimal),
                factQuery
        );
        /*
        System.out.println("I learned the following facts about animals:\n" +
                "- The " + currentNode.animal + " "  + Methods.isolateFact(unprocessedFact, !forSecondAnimal) + "\n" +
                "- The " + secondAnimal[0] + " " + Methods.isolateFact(unprocessedFact, forSecondAnimal) + "\n" +
                "I can distinguish these animals by asking the question:\n- " + factQuery + "\n" +
                "Nice! I've learned so much about animals!\n");
         */

        currentNode.extend(secondAnimal[0], secondAnimal[1], fact, factQuery, !forSecondAnimal);
    }
}
