package animals.game;

import animals.data.GetData;
import animals.data.Tree;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("App");
    private static File file;
    private static ObjectMapper objectMapper;

    public static Tree root;

    public static void initialize(ObjectMapper objectMapper1, File file1, Tree root1) {
        objectMapper = objectMapper1;
        file = file1;
        root = root1;

        if (root == null) {
            initialize();
        } else {
            System.out.println(resourceBundle.getString("greetings"));
        }
        mainMenu();
    }

    private static void initialize() {
        if (resourceBundle.getString("language").equals("english")) {
            System.out.println(resourceBundle.getString("greetings") + "\n");

            System.out.print(resourceBundle.getString("animal.wantLearn") + "\n" + resourceBundle.getString("animal.askFavorite") + "\n ");
        }

        String animal = scanner.nextLine().trim().toLowerCase();
        root = new Tree(Methods.removePrefix(animal), Methods.addPrefix(animal));
    }

    private static void mainMenu() {

        System.out.println("\n" + resourceBundle.getString("welcomeAnimalsExpertSystem"));

        System.out.println("\n" + resourceBundle.getString("menu"));

        if (root.isQueryNode) {
            try {
                GetData.initialize(root);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        do {
            String userInput = scanner.nextLine().trim().toLowerCase();
            switch (userInput) {
                case "1" : {
                    new GuessingGame(root);
                    GetData.initialize(root);
                    break;
                }
                case "2" : {
                    GetData.displayListOfAllAnimals(root);
                    break;
                }
                case "3" : {
                    GetData.searchForAnAnimal(root);
                    break;
                }
                case "unattainable" : {
                    GetData.deleteAnAnimal(root);
                    GetData.initialize(root);
                    break;
                }
                case "4": {
                    GetData.printKnowledgeTreeStats(root);
                    break;
                }
                case "5" : {
                    GetData.printKnowledgeTree(root);
                    break;
                }
                case "0" :
                case "exit" : {
                    save(root);
                    System.out.println("saved.");
                    String[] farewell = resourceBundle.getStringArray("farewell");
                    System.out.println(farewell[new Random().nextInt(farewell.length)]);
                    System.exit(0);
                }
                default: {
                    System.out.println(resourceBundle.getString("menu.error") + "\n");
                    continue;
                }
            }
            System.out.println("\n" + resourceBundle.getString("menu"));
        } while (true);
    }

    private static void save(Tree root) {
        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(file, root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
