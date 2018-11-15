/*Franky Zhang Armor Class
**This class implements the armor class which is extended from artifact class.
**It makes a character be able to equip and dequip armor which raises defense stat.
*/

import java.util.*;

public class Armor extends Artifact{
  public Armor(scanner s){
    super(s);
  }
  public EquipArmor(Character c, Artifact s){
    if(c.equipArmor.equalsTo(1)){
      System.out.println("Can't equip anymore armor, please dequip current one.");
    }
    else if(s.name().equalsIgnoreCase("Cloth Armor")){
      c.defense += 20;
      c.removeArtifact(s);
      c.equipArmor = 1;
      System.out.println("Equipped Cloth Armor");
    }
    else if(s.name().equalsIgnoreCase("Leather Armor")){
      c.defense += 30;
      c.removeArtifact(s);
      c.equipArmor = 1;
      System.out.println("Equipped Leather Armor");
    }
    else if(s.name().equalsIgnoreCase("Heavy Armor")){
      c.defense += 40;
      c.removeArtifact(s);
      c.equipArmor = 1;
      System.out.println("Equipped Heavy Armor");
    }
  }
  public DequipArmor(Character c, Artifact s){
    if(c.equipArmor.equalsTo(0)){
      System.out.println("Can't dequip any armor, please equip one.");
    }
    else if(s.name().equalsIgnoreCase("Cloth Armor")){
      c.defense -= 20;
      c.addArtifact(s);
      c.equipArmor = 0;
      System.out.println("Dequipped Cloth Armor");
    }
    else if(s.name().equalsIgnoreCase("Leather Armor")){
      c.defense -= 30;
      c.addArtifact(s);
      c.equipArmor = 0;
      System.out.println("Dequipped Leather Armor");
    }
    else if(s.name().equalsIgnoreCase("Heavy Armor")){
      c.defense -= 40;
      c.addArtifact(s);
      c.equipArmor = 0;
      System.out.println("Dequipped Heavy Armor");
    }
}
