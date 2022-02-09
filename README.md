<h1>
    Guess The Animal
</h1>


<div class="About" >
	<h2> About </h2>
	<p>
        Guess The Animal is a simple, interactive, text-based console game where the program tries to guess what animal you have in mind by keeping track of facts given about that animal. Coded solely using Java using the Gradle build tool.
        <br><br>
        The program primarily rotates around a single binary tree with each non-leaf node representing a query about a fact, and a leaf representing an animal.
        <br><br>
    </p>
</div>    
<div class="About" >
	<p>
    	<img src="https://ucarecdn.com/e1566b86-c4ef-41da-99ad-198a0cb6dc16/" alt="Binary Tree" width="400" height="300" align="center"/>
</p>
</div>    
<div class="About" >
	<p>
        Taking example from the image above, the game starts from the root node. The game traverses the tree, going down either left or right based on the response to the query. When the game reaches a leaf node, it asks if the animal in the node is what the player is thinking of. If yes, the game ends; If no, then the game asks what animal the player is thinking of along with a fact that distinguishes it from the other animal.
    </p>
</div>  
<figure>
<blockquote>
        <p>
            ... <br>
            Is it a mammal? <br>
            > No <br>
            Is it a shark? <br>
            > Nope <br>
            What animal did you have in mind? <br>
            > A turtle <br>
            Name one fact that distinguishes a shark from a turtle <br>
            > It is a reptile <br>
            Does this fact apply to a turtle? <br>
            > Yes <br><br>
            ...<br>
            A new node gets created
        </p>
</blockquote>
</figure>

Aside from the main guessing game, the game has five other options in the main menu: display list of all animals; search for a certain animal and display all its facts; delete an animal from the database; calculate tree statistics; print the knowledge tree.



---



The program saves the binary tree in JSON (Default)/XML/YAML as defined in the CLI arguments on runtime using the Jackson ObjectMapper API. For the example above, here is what it would look like saved in [JSON](https://pastebin.com/qrzjCvTB), [XML](https://pastebin.com/bxUnSJgq), [YAML](https://pastebin.com/mxW40BCz). The ObjectMapper can be configured before the application runtime as follows:

> java -jar guesstheanimal.jar -type xaml 
> // ObjectMapper is set to json by default, but can be either json, xml, or yaml



Additionally, the program has support for esperando - without the speical characters, however. Esperando mode can be toggled by setting the following values in the JVM options.

> -Duser.language=eo



<h2>
    Medium
</h2>
Made solely using Java through the Gradle build tool. Extensions/dependencies include FasterXML's Jackson ObjectMapper. 


<h2>
    Process
</h2>
This [project](https://hyperskill.org/projects/132?track=17) was part of the Java Developer track in the hyperskill learning platform. I chose this project because I wanted challenging learning experience. In the end, I leant about multiple data structures (trees, binary heaps, maps), tree traversion using recursion, resource files, and basic JVM options.



The project started with the main game basics. I had to program methods that correctly separate and apply articles to given animals (for example, if the user inputs 'elephant', the program returns 'an elephant', similarly 'unicorn' becomes 'a unicorn'). After that I had to make the program take two different animals and then prompts the user for a fact that distinguishes the two. It then states the two given animals once more, along with their fact.



After that, I had to make the main guessing game, with the program starting out asking the player what animal they like the most. It then asks if the player is thinking of said animal; If not, the program asks for the animal the player is thinking of along with a fact that distinguishes the two animals. It then expands its knowledge base, having an extra query to ask and an extra animal with another fact.



The next part of the project involved me having to make a way for the project to save data. First of all, I had to make the tree class I made serializable. After that, using the Jackson Serializer, the program serializes the Tree object and saves it into a JSON/XML/YAML file. After that, I implemented the menu, implementing animal search and delete functionality, as well as a methods that display all animals, the knowledge tree, and tree statistics.


<h2>
    Download Instructions
</h2>
Download the latest .jar from the releases.

You can check if you have Java in your computer by going into the cmd and typing **java -version**. If so, go to the directory of the .jar file you downloaded and type, in the cmd:

> java -jar guesstheanimal.jar

If you do not have Java, you can [download it from their official website](https://www.java.com/en/download/manual.jsp) - instructions included

Otherwise, if you have any other application that can run .jar files, you can use those too.



