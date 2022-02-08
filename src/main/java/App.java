import animals.Print;

import java.util.ListResourceBundle;
import java.util.function.UnaryOperator;

public class App extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {

                {"language", "english"},

                {"welcomeAnimalsExpertSystem", "Welcome to the animal's expert system!"},

                {"greetings", "Hello!"  },

                {"greeting.morning", "Good morning!"},
                {"greeting.afternoon", "Good afternoon!"},
                {"greeting.evening", "Good Evening!"},
                {"greeting.night", "Hi Early Bird!"},

                {"farewell", new String[] {
                        "Bye!", "Bye, bye!", "See you later!", "See you soon!", "Talk to you later!", "I'm off!",
                        "It was nice seeing you!", "See ya!", "See you later, alligator!", "Have a nice one!"
                }},

                {"clarify", new String[]{
                        "Come one, yes or no?", "Please enter yes or no.", "Funny, I still don't understand, is it a yes or no?",
                        "Sorry, it must be a yes or no.", "I'm not sure I caught you there, was it yes or no?", "Oh it's too complicated" +
                        "for me, just say yes or no.", "I am a bit confused, give me a simple answer: yes or no?",
                        "Sorry, may I ask you again... Was that a yes or a no?"}
                },


                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

                {"animal.wantLearn", "I want to learn about animals."},
                {"animal.askFavorite", "Which animal do you like the most?"},
                {"animal.nice", new String[] {"Nice!", "Great!", "Fantastic!", "Marvelous!", "Wonderful!", "Awesome!"}},
                {"animal.learnedMuch", "I've learned so much about animals!"},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

                {"animal.prompt", "Enter the animal:"},
                {"animal.error", "The animal should be entered in the following format:\n" +
                        "a/an + name of the animal, for example, \"an elephant\"."},
                {"statement.prompt", new Print<String>()},
                {"statement.error", "Incorrect statement. The examples of a correct statement:\n" +
                        "   - It can fly\n" +
                        "   - It has horns\n" +
                        "   - It is a mammal"},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
                // The Guessing Game

                {"game.play", "Let's play a game!"},
                {"game.think", "You think of an animal, and I guess it."},
                {"game.enter", "Press enter when you're ready."},
                {"game.win", "It's great that I got it right!\n" +
                        "I didn't even hope that it would be possible to guess!"},

                {"game.giveUp", "I give up. What animal do you have in mind?"},
                {"game.isCorrect", (UnaryOperator<String>) animalWithPrefix -> {
                    return "Is the statement correct for " + animalWithPrefix + "?";
                }},
                {"game.learned", new Print<>()},

                {"game.thanks", new String[] {
                        "That was fun!", "Thank you for playing!", "Thank you! I had so much fun!"
                }},
                {"game.again", new String[] {
                        "Want to try again?", "Would you like to play again?", "Do you want to repeat?", "Want to play again?",
                        "One more game?", "Do you want to play again?"
                }},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
                // Application menu

                {"menu", "What do you want to do:\n\n" +
                        "1. Play the guessing game\n" +
                        "2. List of all animals\n" +
                        "3. Search for an animal\n" +
                        "4. Delete an animal\n" +
                        "5. Calculate statistics\n" +
                        "6. Print the Knowledge Tree\n" +
                        "0. Exit"},
                {"menu.error", "Please enter the number from 0 up to 6"},
                {"menu.choice", "Your choice:"},
                {"menu.prompt", "Enter the animal:"},


                {"tree.list.animals", "Here are the animals (facts) I know:"},

                {"tree.search.onlyNode", (UnaryOperator<String>) animalName -> {
                    return "I have no facts that distinguishes the " + animalName + " yet...t";
                }},
                {"tree.search.facts", (UnaryOperator<String>) animalName -> {
                    return "Facts about the " + animalName + ":";
                }},
                {"tree.search.noFacts", (UnaryOperator<String>) animalName -> {
                    return "No facts about the " + animalName + ".";
                }},


                {"tree.stats", new Print<String>()},


                {"tree.delete.root", "Can't delete the only animal from the root."},
                {"tree.delete.successful", (UnaryOperator<String>) animalName -> {
                    return "The " + animalName + " was deleted from the knowledge base.";
                }},
                {"tree.delete.fail", (UnaryOperator<String>) animalName -> {
                    return "The " + animalName + " was not found in the knowledge base.";
                }},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
                // PATTERNS

                {"positiveAnswer.isCorrect", "(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[.!]?"},
                {"negativeAnswer.isCorrect", "(n|no( way)?|nah|nope|negative|i don't think so|yeah no)[.!]?"},

                {"statement.positive.is", "it is"},
                {"statement.positive.can", "it can"},
                {"statement.positive.has", "it has"},

                {"statement.negative.is", "it (is not)|(isn'?t)"},
                {"statement.negative.can", "it (can'?t|canno'?t)"},
                {"statement.negative.has", "it ((does not|doesn'?t)) (have|has)?"},

                {"statement.query.is", "Is it"},
                {"statement.query.can", "Can it"},
                {"statement.query.has", "Does it have"},

                {"statement.it", "it"},
                {"statement.the", "The"},

                {"statement.is", "is"},
                {"statement.isn't", "isn't"},
                {"statement.can", "can"},
                {"statement.can't", "can't"},
                {"statement.has", "has"},
                {"statement.doesn't have", "doesn't have"}
        };
    }
}