import animals.Print;

import java.util.ListResourceBundle;
import java.util.function.UnaryOperator;

public class App_eo extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {

                {"language", "esperando"},

                {"welcomeAnimalsExpertSystem", "Bonvenon al la sperta sistemo de la besto!"},

                {"greetings", "Saluton!"},

                {"greeting.morning", "Bonan matenon!"},
                {"greeting.afternoon", "Bonan tagon!"},
                {"greeting.evening", "Bonan vesperon!"},
                {"greeting.night", "Saluton Nokta Strigo!"},

                {"farewell", new String[] {
                        "Ĝis!", "Ĝis revido!", "Estis agrable vidi vin!"
                }},

                {"clarify", new String[] {
                        "Bonvolu enigi jes aŭ ne.", "Amuze, mi ankoraŭ ne komprenas, ĉu jes aŭ ne?", "Pardonu, devas esti jes aŭ ne.",
                        "Ni provu denove: ĉu jes aŭ ne?", "Pardonu, ĉu mi rajtas demandi vin denove: ĉu tio estis jes aŭ ne?"
                }},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

                {"animal.wantLearn", "Mi volas lerni pri bestoj."},
                {"animal.askFavorite", "Kiun beston vi plej satas?"},
                {"animal.nice", new String[] {"Bela!", "Mirinde!", "Mojosa!", "Bonege!", "Mirinda!"}},
                {"animal.learnedMuch", "Mi lernis tiom multe pri bestoj!"},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //

                {"animal.prompt", "Enigu la nomon de besto:"},
                {"animal.error", "La besto devas esti enmetita en la jenan formaton:\n" +
                        "nomo de la besto, ekzemple, \"elefanto\""},
                {"statement.prompt", new Print<String>() {
                    @Override
                    public void learnedDistinguish(String animal1Fact, String animal2Fact, String query) {
                    }
                    @Override
                    public void knowledgeTreeStatistics(String rootNode, int[] data, double averageDepth) {
                    }

                    @Override
                    public void prompt(String value1, String value2) {
                        System.out.println("Indiku fakton, kiu distingas " + value1 + " de " + value2 + ".\n" +
                                "La frazo devas esti de la formato: 'Ĝi ...'.");
                    }
                }},
                {"statement.error", "La ekzemploj de aserto:\n" +
                        "   - Ĝi povas flugi\n" +
                        "   - Ĝi havas kornojn\n" +
                        "   - Ĝi estas mamulo"},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
                // The Guessing Game

                {"game.play", "Ni ludu!"},
                {"game.think", "Vi pensu pri besto, kaj mi divenos gin."},
                {"game.enter", "Premu enen kiam vi pretas."},
                {"game.win", "Bonege, ke mi trafis gin guste!"},

                {"game.giveUp", "Mi rezignas. Kiun beston vi havas en la kapo?"},
                {"game.isCorrect", (UnaryOperator<String>) animalWithPrefix -> {
                    return "Ĉu la aserto gustas por la " + animalWithPrefix + "?";
                }},
                {"game.learned", new Print<String>() {
                    @Override
                    public void prompt(String value1, String value2) {
                    }
                    @Override
                    public void knowledgeTreeStatistics(String rootNode, int[] data, double averageDepth) {
                    }

                    @Override
                    public void learnedDistinguish(String animal1Fact, String animal2Fact, String query) {
                        System.out.println("Mi lernis la jenajn faktojn pri bestoj:\n" +
                                "   - " + animal1Fact +
                                "\n   - " + animal2Fact +
                                "\nMi povas distingi ĉi tiujn bestojn per la demando:\n" +
                                "   - " +query);
                    }
                }},

                {"game.thanks", new String[] {
                        "Tio estis amuza!", "Estis agrable ludi kun vi!", "Dankon pro ludado!"
                }},
                {"game.again", new String[] {
                        "Ĉu vi volas provi denove?", "Ĉu vi satas ludi denove?", "Ĉu vi volas ripeti?", "Ĉu vi volas ludi denove?",
                        "Ankoraŭ unu ludo?", "Ĉu vi volas ludi denove?"
                }},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
                // Application menu

                {"menu", "Kion vi volas fari:\n\n" +
                        "1. Ludi la divenludon\n" +
                        "2. Listo de ĉiuj bestoj\n" +
                        "3. Serĉi beston\n" +
                        "4. Forigi beston\n" +
                        "5. Kalkuli statistikojn\n" +
                        "6. Printi la Sciarbon\n" +
                        "0. Eliri"},
                {"menu.error", "Bonvolu enigi numeron de 0 gis 6"},
                {"menu.choice", "Via elekto:"},
                {"menu.prompt", "Enigu la beston:"},


                {"tree.list.animals", "Jen la bestoj, kiujn mi konas:"},

                {"tree.search.onlyNode", (UnaryOperator<String>) animalName -> {
                    return "Mi havas neniujn faktojn, kiuj distingas la " + animalName;
                }},
                {"tree.search.facts", (UnaryOperator<String>) animalName -> {
                    return "Faktoj pri la " + animalName + ":";
                }},
                {"tree.search.noFacts", (UnaryOperator<String>) animalName -> {
                    return "Neniuj faktoj pri la " + animalName + ".";
                }},


                {"tree.stats", new Print<String>() {
                    @Override
                    public void prompt(String value1, String value2) {
                    }
                    @Override
                    public void learnedDistinguish(String animal1Fact, String animal2Fact, String query) {
                    }

                    @Override
                    public void knowledgeTreeStatistics(String rootNode, int[] data, double averageDepth) {
                        System.out.printf("La statistiko de la Scio-Arbo" +
                                        "\n   - radika nodo                     %s" +
                                        "\n   - totala nombro de nodoj          %d" +
                                        "\n   - totala nombro de bestoj         %d" +
                                        "\n   - totala nombro de deklaroj       %d" +
                                        "\n   - alteco de la arbo               %d" +
                                        "\n   - minimuma profundo               %d" +
                                        "\n   - averaga profundo                %s\n",
                                rootNode, data[0], data[1], data[2], data[3], data[4], String.format("%.1f", averageDepth));
                    }
                }},


                {"tree.delete.root", "Ne eblas forigi la solan beston de la radiko."},
                {"tree.delete.successful", (UnaryOperator<String>) animalName -> {
                    return "La " + animalName + " estis forigita de la sciobazo.";
                }},
                {"tree.delete.fail", (UnaryOperator<String>) animalName -> {
                    return "La " + animalName + " ne trovigis en la sciobazo.";
                }},

                //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
                // PATTERNS

                {"positiveAnswer.isCorrect", "(j|jes|certe)!?"},
                {"negativeAnswer.isCorrect", "(n|ne)!?"},

                {"statement.positive.is", "gi estas"},
                {"statement.positive.can", "gi povas"},
                {"statement.positive.has", "gi havas"},

                {"statement.negative.is", "(gi )?ne estas"},
                {"statement.negative.can", "(gi )?ne povas"},
                {"statement.negative.has", "(gi )?ne havas"},

                {"statement.query.is", "Ĉu gi estas"},
                {"statement.query.can", "Ĉu gi povas"},
                {"statement.query.has", "Ĉu gi havas"},

                {"statement.it", "Gi"},
                {"statement.the", "La"},

                {"statement.is", "estas"},
                {"statement.isn't", "ne estas"},
                {"statement.can", "povas"},
                {"statement.can't", "ne povas"},
                {"statement.has", "havas"},
                {"statement.doesn't have", "ne havas"}
        };
    }
}