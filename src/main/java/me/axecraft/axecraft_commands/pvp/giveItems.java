package me.axecraft.axecraft_commands.pvp;

import me.axecraft.axecraft_commands.vars_arrays;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class giveItems {
    public HashMap<Player, Integer> got_item = new HashMap<>();
    public void giveItemstoPlayer(int[] i_togive, Player p, String t, int h){
        // t indicates whether it is archer or swordsman
        int has_value = h;
        if(has_value == 1){
            ///means they already have items
            /// don't give them any more
            p.sendMessage("You have already been given items!!");

        }
        else if (has_value == 0) {
            int set_value = 1; ///this is where you set the item in the inventory
            got_item.put(p, set_value);
            int placement = 1;
            Integer[] boot_list = new Integer[]{301, 305};
            // Convert String Array to List
            List<Integer> boots = Arrays.asList(boot_list);
            ///arrow list
            Integer[] arrow_list = new Integer[]{262, 2620};
            List<Integer> arrows = Arrays.asList(arrow_list);
            for (int i : i_togive) {

                ItemStack item_name = vars_arrays.axecraft_items.get(i);
                //player.sendMessage("I am giving you" + item_name);
                if (i == 298) {  ///put helmet on head
                    p.getInventory().setHelmet(item_name);
                } else if (i == 303) { /// put chestplate on

                    p.getInventory().setChestplate(item_name);
                } else if ((boots).contains(i)) { ///put on boots

                    p.getInventory().setBoots(item_name);

                } else if(i == 308){
                    p.getInventory().setLeggings(item_name);
                }
                else {
                    if (i == 5) {
                        placement = 1;
                        if(t == "a") {
                            ///enchant the bow
                            item_name.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
                        }else{
                            item_name.removeEnchantment(Enchantment.ARROW_DAMAGE);
                        }
                    }
                    else if((arrows).contains(i)){
                        placement = 3;
                    }else if(i == 267){ //sword
                        placement = 0;
                        if(t == "s") {
                            item_name.addEnchantment(Enchantment.KNOCKBACK, 1);
                        }else{
                            item_name.removeEnchantment(Enchantment.KNOCKBACK);
                        }
                    }else if(i == 322){ //apples
                        placement = 2;
                    }
                    p.getInventory().setItem(placement, item_name);
                }
            }

        }
        //return false;
    }
}
