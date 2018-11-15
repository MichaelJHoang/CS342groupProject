/* Franky Zhang
** This class extends from the artifact class and declares a potion, it has one main overriding
** function in that a player character can use it to heal or gain stats. Only player for now.  
*/
import java.util.*;


public class Consumables extends Artifact{
  public Consumables(scanner s){
    super(s);
  }
  @override
  public use(Character c, Artifact s){
    if(c instanceof Player){
      if(s.name().equalsIgnoreCase("Small HP Pot")){
      	c.heal(20);
     	c.removeArtifact(s);
      }
      else if(s.name().equalsIgnoreCase("Medium HP Pot")){
        c.heal(40);
        c.removeArtifact(s);
      }
      else if(s.name().equalsIgnoreCase("Large HP Pot")){
	c.heal(60);
	c.removeArtifact(s);
      }
      else if(s.name().equalsIgnoreCase("DefPot")){
        c.def += 20;
        c.removeArtifact(s);
      }
      else if(s.name().equalsIgnoreCase("AtkPot")){
        c.atk += 20;
        c.removeArtifact(s);
      }
      else if(s.name().equalsIgnoreCase("LuckPot")){
        c.luck += 20;
        c.removeArtifact(s);
      }
    else{
      System.out.println("You are not a Player Character, FORBIDDEN TO USE");
  }

