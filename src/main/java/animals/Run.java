package animals;

import animals.data.GetData;
import animals.data.Tree;
import animals.game.Game;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Run {

    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("App");
    private static final Scanner scanner = new Scanner(System.in);

    static File file;
    static ObjectMapper objectMapper;
    static String fileType;
    static boolean cleared;
    static String language;

    public static void main(String[] args) throws Exception {
        language = resourceBundle.getString("language");
        StartUp.initialize(args);
        Tree tree;

        if (cleared) {

            //TEST ONLY REMOVE WHEN DONE
            Run.file = new File("animals" + (Run.language.equals("esperando") ? "_eo." : ".") + Run.fileType);

            tree = new Tree("cat", "a cat");
            tree.extend("shark", "a shark", "It is a mammal", "Is it a mammal?", true);
            tree.yes.extend("fox", "a fox", "It is living in the forest", "Is it living in the forest?", false);
            tree.yes.no.extend("dog", "a dog", "It can climb trees", "Can it climb trees?", true);
            tree.yes.yes.extend("wolf", "a wolf", "It has a long bushy tail", "Does it have a long bushy tail?", true);
            tree.yes.yes.no.extend("hare", "a hare", "It is a shy animal", "Is it a shy animal?", false);
            //

            //tree = null; RESTORE WHEN ABOVE DELETED
        } else {
            tree = StartUp.load();
        }

        Game.initialize(objectMapper, file, tree);
    }
}

class StartUp {

    static void initialize(String[] arguments) throws Exception {
        //The default ObjectMapper is JSON
        Run.objectMapper = new JsonMapper();
        Run.fileType = "json";

        //If there are no arguments, then there is nothing to process
        if (arguments.length == 0) {
            return;
        }

        boolean mapperInitialized = false;
        for (int i = 0; i < arguments.length; i++) {
            switch (arguments[i]) {
                //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //
                case "-clear" : {
                    clear();
                    break;
                }
                //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //
                case "-type" : {
                    if (mapperInitialized) {
                        throw new Exception("Mapper has already been initialized!\n" +
                                "Error at args[" + i + "]");
                    }
                    try {
                        switch (arguments[i + 1]) {
                            case "json" : {
                                Run.objectMapper = new JsonMapper();
                                Run.fileType = "json";
                                mapperInitialized = true;
                                break;
                            }
                            case "xml" : {
                                Run.objectMapper = new XmlMapper();
                                Run.fileType = "xml";
                                mapperInitialized = true;
                                break;
                            }
                            case "yaml" : {
                                Run.objectMapper = new YAMLMapper();
                                Run.fileType = "yaml";
                                mapperInitialized = true;
                                break;
                            }
                            default: {
                                throwIllegalArgumentException(arguments[i + 1]);
                                break;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                        throw new IllegalArgumentException("Invalid file type!\n" +
                                "Expected: 'json', 'xml', 'yaml'\n" +
                                "Got: null");
                    }
                }
                //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //   //
            }
        }
    }

    static Tree load() {
        Run.file = new File("animals" + (Run.language.equals("esperando") ? "_eo." : ".") + Run.fileType);
        if (Run.file.length() == 0) {
            return null;
        }

        try {
            Tree root = Run.objectMapper.readValue(Run.file, Tree.class);
            if (root.isQueryNode) {
                root.initParents(root);
            }
            return root;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static void throwIllegalArgumentException(String got) throws IllegalArgumentException {
        throw new IllegalArgumentException("Invalid file type!\n" +
                "Expected: 'json', 'xml', 'yaml'\n" +
                "Got: " + got);
    }

    private static void clear() {
        Run.cleared = true;
        try {
            BufferedOutputStream clearJson = new BufferedOutputStream(new FileOutputStream(Paths.get("animals.json").toString()));
            clearJson.write("".getBytes());
            clearJson.flush();

            BufferedOutputStream clearXml = new BufferedOutputStream(new FileOutputStream(Paths.get("animals.xml").toString()));
            clearXml.write("".getBytes());
            clearXml.flush();

            BufferedOutputStream clearYaml = new BufferedOutputStream(new FileOutputStream(Paths.get("animals.yaml").toString()));
            clearYaml.write("".getBytes());
            clearYaml.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}