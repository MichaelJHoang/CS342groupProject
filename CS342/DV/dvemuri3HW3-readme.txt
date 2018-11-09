Dilip Vemuri
dvemuri3
HW3 Readme

My program does everything the outline asks, including command line arguments. I DID NOT include backwards compatibility for gdf files 3.1, but it works just fine with 4.0. I did all testing with MystiCity40.gdf provided by Professor Bell.

Command line arguments: I accept zero, one or two command line arguments. If one is present, it must be a file name. If two are present, it must be filename numCharacters. Anymore than 2 and the program exits.

Player moves I have implemented:
USE, EXIT, INVENTORY, GET, DROP, LOOK, GO, NONE, WAIT, QUIT

Most are self explanitory and carry over from HW2. 'QUIT' quits the entire process, where as 'EXIT' only removes one character.
Commands must be entered in all caps, as the appear above. I did not have time to implement different cases.
Arguments for GET/DROP/USE must be typed exactly as they appear in the file.

I added some functions that were not specified in the UML diagram:

Game class:
	4 functions to add and remove players/npcs. I have players and npcs in two different collections
Place class:
	getRandomPlace() - Returns a random place from the collection of places
	getFirstPlace() - gets the first place in the collection
	getRandomDirection() - Gets a radnom direction from a specific Place object.
	(These functions were made mostly for AI and Character/Artifact constructors)
Character: 
	I implemented execute functions for every Move listed above. Also some getters for stuff like name and 
	place
AI:
	My AI class so far only picks between GO and WAIT commands. I hard coded for the ogre to wait all game.

Make file:
	Just running 'make' in the directory with the makefile and all the java files should work. I tested this on bertvm. make file also has a 'make clean' command.

NOTE: I wrote my program in Eclipse so there are package statements at the top of all my files. They should all be commented out in the zipfile I included, but if they are not for whatever reason I apologise. Any errors in the make process are probably a result of this.
