JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Direction.java \
	Game.java \
	GameTester.java \
	Place.java \
	Artifact.java \
	Move.java \
	Character.java \
	CleanLineScanner.java \
	DecisionMaker.java 
	
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class