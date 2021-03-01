# Guess-The-Animal

1/3/2021
A simple interactive text-based (console) game. The program tries its best to guess what animal the user is thinking of through questions from facts that it had aquired before.
The program asks the user (a bunch of) questions that relate to an animal. When the program narrows it down to a single animal, it then asks the user if that animal is the animal
they had in mind. If not, then the program prompts the user to enter what they were thinking of and a fact that distinguished this animal from the other one. It then repeats this 
cycle indefinitly.

It also has some other features, those being the ability to play the game (as mentioned above), display all the animals known, search for an animal, calculate statistics 
(min/max/average depth, node types + node counts, etc). In addition to that it also has support for Esperando.

What I've learned / implemented:
	-	(Binary) Trees, Binary Heaps, Maps  (Hashmaps/LinkedHashmaps)
	-	Recursion basics (w/ respect to trees)
	-	Jackson ObjectMapper (for JSON, XML, YAML)
	-	Rescource files / ListResourceManager / Internationaliztion (for Esperando)
	- 	Basic JVM options
	-	Somewhat basic regex
	
Program Arguments :
"-type (json|xml|yaml)" - specifies the type of ObjectMapper - and therefore filetype.
"-clear" - to clear the current knowledge tree.

JVM options :
"-Duser.language=eo" - to toggle Esperando.
