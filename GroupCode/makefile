JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
ALL = CleanLineScanner.java Move.java DecisionMaker.java UI.java AI.java OgreAsleepAI.java OgreAwakeAI.java MerchantAI.java EnemyDogAI.java FriendlyDogAI.java KeyboardScanner.java Character.java Player.java NPC.java Ogre.java Merchant.java DOG.java Direction.java Place.java Artifact.java Game.java GameTest.java
default:	classes
classes: $(ALL:.java=.class)
clean:
	$(RM) *.class
