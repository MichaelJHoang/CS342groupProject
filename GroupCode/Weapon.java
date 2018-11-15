/*Franky Zhang Weapon Class
**This class implements the weapon class which is extended from artifact class.
**It makes a character be able to equip and dequip weapons which raises the attack stat.
*/

import java.util.*;

public class Weapon extends Artifact{
  public Weapon(scanner s){
    super(s);
  }
  public EquipWeapon(Character c, Artifact s){
    if(c.equipWeapon.equalsTo(1)){
      System.out.println("Can't equip another weapon, please dequip current one.");
    }
    else if(s.name().equalsIgnoreCase("Short Sword")){
      c.attack += 10;
      //c.attackSpeed = 4? (4 hits a move?)
      //c.accuracy = 0.75? (75% of hitting every swing?)
      c.removeArtifact(s);
      c.equipWeapon = 1;
      System.out.println("Equipped Sword Sword");
    }
    else if(s.name().equalsIgnoreCase("Long Sword")){
      c.attack += 30;
      //c.attackSpeed = 1? (1 hit a move?)
      //c.accuracy = 1? (100% of hitting every swing?)
      c.removeArtifact(s);
      c.equipWeapon = 1;
      System.out.println("Equipped Long Sword");
    }
    else if(s.name().equalsIgnoreCase("Axe")){
      c.attack += 75;
      //c.attackSpeed = 1? (1 hit a move?)
      //c.accuracy = 0.5? (50% of hitting?)
      c.removeArtifact(s);
      c.equipWeapon = 1;
      System.out.println("Equipped Axe");
    }
    //found in treasure storeroom?
    else if(s.name().equalsIgnoreCase("Legendary Ogre Slayer"){
      c.attack += 100;
      //c.attackSpeed = 1?
      //c.accuracy = 1?
      c.removeArtifact(s);
      c.equipWeapon = 1;
      System.out.println("Equipped Legendary Ogre Slayer, NOW YOU OP!!!!!!!");
    }
  }
  public DequipWeapon(Character c, Artifact s){
    if(c.equipWeapon.equalsTo(0)){
      System.out.println("Can't dequip a weapon, You have none!");
    }
    else if(s.name().equalsIgnoreCase("Short Sword")){
      c.attack -= 10;
      //c.attackSpeed = 1
      //c.accuracy = 1
      c.addArtifact(s);
      c.equipWeapon = 0;
      System.out.println("Dequipped Sword Sword");
    }
    else if(s.name().equalsIgnoreCase("Long Sword")){
      c.attack -= 30;
      //c.attackSpeed = 1
      //c.accuracy = 1
      c.addArtifact(s);
      c.equipWeapon = 0;
      System.out.println("Dequipped Long Sword");
    }
    else if(s.name().equalsIgnoreCase("Axe")){
      c.attack -= 75;
      //c.attackSpeed = 1
      //c.accuracy = 1
      c.addArtifact(s);
      c.equipWeapon = 0;
      System.out.println("Dequipped Axe");
    }
    else if(s.name().equalsIgnoreCase("Legendary Ogre Slayer"){
      c.attack -= 100;
      //c.attackSpeed = 1?
      //c.accuracy = 1?
      c.addArtifact(s);
      c.equipWeapon = 0;
      System.out.println("Dequipped Legendary Ogre Slayer, WHYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY?!");
    }
  }

}
