package me.axecraft.axecraft_commands;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class vars_arrays {
    public static HashMap<Integer, ItemStack> axecraft_items = new HashMap<Integer, ItemStack>() {
        {
            put(262, new ItemStack(Material.ARROW, 20));
            put(298, new ItemStack(Material.IRON_HELMET, 1));
            put(5, new ItemStack(Material.BOW, 1));
            put(322, new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
            put(308, new ItemStack(Material.IRON_LEGGINGS, 1));
            put(301, new ItemStack(Material.LEATHER_BOOTS, 1));
            put(305, new ItemStack(Material.CHAINMAIL_BOOTS, 1));
            put(267, new ItemStack(Material.IRON_SWORD, 1));

        }
    };
  //  public static void put_items(){
  //      String[] arr = {"ARROW", "LEATHER_HELMET", "PLANKS"
  //      };
  //      for(String s : arr){
  //          new ItemStack(Material.s, 1);
  //          axecraft_items.put()
  //      }
  //  }
}
