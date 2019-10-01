/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beispiel1;

import com.sun.istack.internal.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 *
 * @author lehne
 */
public class Beispiel1
{


  
  public static void main(String[] args) throws IOException
  {
      Printable printer = (n) -> n.forEach((k)-> System.out.println(k.toString()));
      
          List<Weapon> myList2 = Files.lines(new File("weapons.csv").toPath()).skip(1).map((n) -> {
              String[] weaponValues = n.split(";");
              Weapon newWeapon = new Weapon(
                      weaponValues[0],
                      CombatType.valueOf(weaponValues[1]),
                      DamageType.valueOf(weaponValues[2]),
                      Integer.valueOf(weaponValues[3]),
                      Integer.valueOf(weaponValues[4]),
                      Integer.parseInt(weaponValues[5]),
                      Integer.valueOf(weaponValues[6]));
              return newWeapon;
          }).collect(Collectors.toList());
          
          myList2.sort(new Comparator<Weapon>()
          {
              @Override
              public int compare(Weapon t, Weapon t1)
              {
                  return t.getDamage() - t1.getDamage();
              }
          });
          for (Weapon weapon : myList2)
      {
          System.out.println(weapon);
      }
          
     myList2.sort(new Comparator<Weapon>()
     {
              @Override
              public int compare(Weapon t, Weapon t1)
              {
                  if(t.getCombatType().compareTo(t1.getCombatType())==0)
                  {
                     if(t.getDamageType().compareTo(t1.getDamageType())==0)
                     {
                         return t.getName().compareTo(t1.getName());
                     }
                     else
                     {
                         return t.getDamageType().compareTo(t1.getDamageType());
                     }
                  }
                  else
                  {
                      return t.getCombatType().compareTo(t1.getCombatType());
                  }
              }
          });
             
     printer.print(myList2);
  }
    
}



