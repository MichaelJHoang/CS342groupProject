JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Place.java \
	Direction.java \
	Game.java \
	GameTest.java \
	Artifact.java \
	Move.java \
	Character.java \
	CleanLineScanner.java \
	DecisionMaker.java \
	AI.java \
	KeyboardScanner.java \
	Player.java \
	UI.java \
	NPC.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
