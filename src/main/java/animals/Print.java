package animals;

import java.text.DecimalFormat;

public class Print<T> {
    public void prompt(T value1, T value2) {
        System.out.println("Specify a fact that distinguishes " + value1 + " from " + value2 + ".\n" +
                "The sentence should be of the format: 'It can/has/is ...'.");
    }

    public void learnedDistinguish(T animal1Fact, T animal2Fact, T query) {
        System.out.println("I have learned the following facts about animals:\n" +
                "   - " + animal1Fact +
                "\n   - " + animal2Fact +
                "\nI can distinguish these animals by asking the following question:\n" +
                "   - " +query);
    }

    public void knowledgeTreeStatistics(T rootNode, int[] data, double averageDepth) {
        System.out.printf("The Knowledge Tree stats" +
                "\n   - root node                     %s" +
                "\n   - total number of nodes         %d" +
                "\n   - total number of animals       %d" +
                "\n   - total number of statements    %d" +
                "\n   - height of the tree            %d" +
                "\n   - minimum depth                 %d" +
                "\n   - average depth                 %s\n",
                rootNode, data[0], data[1], data[2], data[3], data[4], String.format("%.1f", averageDepth));
    }
}
