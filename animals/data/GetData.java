package animals.data;

import animals.Print;
import animals.game.Game;
import animals.game.Methods;
import org.jetbrains.annotations.TestOnly;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;

public class GetData {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("App");

    private static int nodeCount;
    private static Tree root;
    private static HashSet<Tree> animalNodes;
    private static HashSet<Tree> queryNodes;
    private static HashMap<String, AnimalData> animalData;
    private static double averageHeight;

    private static void traverse(Tree node) {
        if (!node.no.checked) {
            nodeCount++;
            queryNodes.add(node);

            node.no.checked = true;
            if (node.no.isQueryNode) {
                traverse(node.no);
            } else {
                animalNodes.add(node.no);
            }
        }
        if (!node.yes.checked) {
            nodeCount++;
            queryNodes.add(node);

            node.yes.checked = true;
            if (node.yes.isQueryNode) {
                traverse(node.yes);
            } else {
                animalNodes.add(node.yes);
            }
        }
    }

    private static void printTraverse(Tree node) {

        if (node.query != null) {
            System.out.println(node.query);
        }

        if (node.yes.isQueryNode) {
            printTraverse(node.yes);
        } else {
            System.out.println("- " + node.yes.animalWithPrefix);
        }

        if (node.no.isQueryNode) {
            printTraverse(node.no);
        } else {
            System.out.println("- " + node.no.animalWithPrefix);
        }
    }

    private static void initAnimalData() {
        AtomicInteger totalHeight = new AtomicInteger();
        AtomicInteger iterations = new AtomicInteger();

        animalNodes.forEach(node -> {
            Tree currentNode = node;
            ArrayList<String> facts = new ArrayList<>();
            int height = 0;

            while(true) {

                if (currentNode == null) {
                    return;
                }

                if (currentNode.parent == root) {
                    if (currentNode.parent.no == currentNode) {
                        facts.add(Methods.negateFact(currentNode.parent.fact));
                    } else {
                        facts.add(currentNode.parent.fact);
                    }
                    break;
                }

                if (currentNode.parent.no == currentNode) {
                    facts.add(Methods.negateFact(currentNode.parent.fact));
                } else {
                    facts.add(currentNode.parent.fact);
                }

                height++;
                currentNode = currentNode.parent;
            }

            totalHeight.addAndGet(height);
            iterations.getAndIncrement();

            animalData.putIfAbsent(node.animal, new AnimalData(height, facts ));

        });

        averageHeight = (Double.parseDouble(totalHeight.toString()) / Double.parseDouble(iterations.toString()));
    }

    public static void initialize(Tree tree) {
        if (animalNodes == null) {
            animalNodes = new HashSet<>();
        }
        if (queryNodes == null) {
            queryNodes = new HashSet<>();
        }
        if (animalData == null) {
            animalData = new HashMap<>();
        }

        root = tree;
        animalNodes.forEach(node -> node.checked = false);
        queryNodes.forEach(node -> node.checked = false);
        animalNodes.clear();
        queryNodes.clear();


        if (!root.isQueryNode) {
            animalNodes.add(root);
            return;
        }

        traverse(tree);
        initAnimalData();
    }

    public static void displayListOfAllAnimals(Tree tree) {
        System.out.println("\n" + resourceBundle.getString("menu.choice") + "\n2\n" +
                resourceBundle.getString("tree.list.animals"));
        //System.out.println("\nYour choice:\n2\nHere are all the animals I know:");

        if (!tree.isQueryNode) {
            System.out.println("- " + tree.animal);
            return;
        }

        ArrayList<String> temp = new ArrayList<>();
        String[] array = new String[animalNodes.size()];
        animalNodes.forEach(node -> temp.add(node.animal));
        for (int i = 0; i < array.length; i++) {
            array[i] = temp.get(i);
        }

        Arrays.sort(array);
        for (String animal : array) {
            System.out.println("- " + animal);
        }

        //animalNodes.forEach(animalNode -> System.out.println(" - " + animalNode.animal));
    }

    public static void searchForAnAnimal(Tree tree) {
        Scanner scanner = new Scanner(System.in);

        //System.out.println("\n" + resourceBundle.getString("menu.choice") + "\n3");
        System.out.println(resourceBundle.getString("menu.prompt"));
        String userInput = Methods.removePrefix(scanner.nextLine().trim().toLowerCase());

        if (!tree.isQueryNode) {
            if (userInput.equals(tree.animal)) {
                UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.search.onlyNode");
                System.out.println(operator.apply(Methods.removePrefix(tree.animal)));
                //System.out.println("I have no facts that distinguishes the " + Methods.removePrefix(tree.animal) + " yet...");
            } else {
                UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.search.noFacts");
                System.out.println(operator.apply(userInput));
                //System.out.println("No facts about the " + userInput + ".");
            }
            return;
        }

        if (animalData.containsKey(userInput)) {
            UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.search.facts");
            System.out.println(operator.apply(userInput));
            //System.out.println("Facts about the " + userInput + ":");
            AnimalData data = animalData.get(userInput);
            Stack<String> temp = new Stack<>();
            data.facts.forEach(fact -> temp.push(" - " + fact + "."));
            while (!temp.isEmpty()) {
                System.out.println(temp.pop());
            }
        } else {
            UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.search.noFacts");
            System.out.println(operator.apply(userInput));
        }
    }

    public static void deleteAnAnimal(Tree tree) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + resourceBundle.getString("menu.choice") + "\n4\n" +
                resourceBundle.getString("menu.prompt"));
        String userInput = Methods.removePrefix(scanner.nextLine().trim().toLowerCase());

        if (!tree.isQueryNode) {
            if (userInput.equals(tree.animal)) {
                System.out.println(resourceBundle.getString("tree.delete.root"));
            } else {
                UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.delete.fail");
                System.out.println(operator.apply(userInput));
            }
            return;
        }

        for (Tree node : animalNodes) {
            if (node.animal.equals(userInput)) {

                Tree parentParent = node.parent.parent;

                if (parentParent == null) {
                    if (nodeCount > 3) {

                        if (node.parent.no == node) {
                            Game.root = node.parent.yes;
                        } else {
                            Game.root = node.parent.no;
                        }

                    } else {
                        if (node.parent.no == node) {
                            node.parent.animal = node.parent.yes.animal;
                            node.parent.animalWithPrefix = node.parent.yes.animalWithPrefix;
                        } else {
                            node.parent.animal = node.parent.no.animal;
                            node.parent.animalWithPrefix = node.parent.no.animalWithPrefix;
                        }
                        node = node.parent;
                        node.isQueryNode = false;
                        node.query = null;
                        node.fact = null;
                        node.no = null;
                        node.yes = null;
                    }
                } else {

                    Tree newNode;
                    if (node.parent.no == node) {
                        newNode = new Tree(node.parent.yes.animal, node.parent.yes.animalWithPrefix);
                    } else {
                        newNode = new Tree(node.parent.no.animal, node.parent.no.animalWithPrefix);
                    }
                    if (parentParent.no == node.parent) {
                        parentParent.no = newNode;
                    } else {
                        parentParent.yes = newNode;
                    }
                    newNode.parent = parentParent;
                }

                UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.delete.successful");
                System.out.println(operator.apply(userInput));
                return;
            }
        }
        UnaryOperator<String> operator = (UnaryOperator<String>) resourceBundle.getObject("tree.delete.fail");
        System.out.println(operator.apply(userInput));
    }

    public static void printKnowledgeTreeStats(Tree tree) {
        int minimumDepth = Integer.MAX_VALUE;
        int maximumDepth = Integer.MIN_VALUE;

        String rootNode;
        int totalNodes;
        int numberOfAnimalNodes;
        int numberOfQueryNodes;
        if (tree.isQueryNode) {

            for (AnimalData data : animalData.values()) {
                minimumDepth = Math.min(data.height, minimumDepth);
                maximumDepth = Math.max(data.height, maximumDepth);
            }
            minimumDepth++;
            maximumDepth++;
            averageHeight++;


            rootNode = tree.fact.substring(0,1).toUpperCase(Locale.ROOT) + tree.fact.substring(1);
            totalNodes = (nodeCount + 1);
            numberOfAnimalNodes = animalNodes.size();
            numberOfQueryNodes = queryNodes.size();
        } else {
            rootNode = tree.animal;
            totalNodes = 1;
            numberOfAnimalNodes = 1;
            numberOfQueryNodes = 0;
            maximumDepth = 0;
            minimumDepth = 0;
            averageHeight = 0.0;
        }

        Print<String> knowledgeTreeStatistics = (Print<String>) resourceBundle.getObject("tree.stats");
        knowledgeTreeStatistics.knowledgeTreeStatistics(
                rootNode, new int[] {
                        totalNodes, numberOfAnimalNodes, numberOfQueryNodes, maximumDepth, minimumDepth
                }, averageHeight
        );
    }

    public static void printKnowledgeTree(Tree tree) {
        System.out.println();
        if (!tree.isQueryNode) {
            System.out.println("- " + tree.animalWithPrefix);
            return;
        }

        printTraverse(tree);
    }

    @TestOnly
    public static void test(String[] args) {
        Tree tree = new Tree("cat", "a cat");
        tree.extend("shark", "a shark", "It is a mammal", "Is it a mammal?", true);
        tree.yes.extend("fox", "a fox", "It is living in the forest", "Is it living in the forest?", false);
        tree.yes.no.extend("dog", "a dog", "It can climb trees", "Can it climb trees?", true);
        tree.yes.yes.extend("wolf", "a wolf", "It has a long bushy tail", "Does it have a long bushy tail?", true);
        tree.yes.yes.no.extend("hare", "a hare", "It is a shy animal", "Is it a shy animal?", false);
    }
}

class AnimalData {

    int height;
    ArrayList<String> facts;

    public AnimalData(int height, ArrayList<String> facts) {
        this.height = height;
        this.facts = facts;
    }
}
