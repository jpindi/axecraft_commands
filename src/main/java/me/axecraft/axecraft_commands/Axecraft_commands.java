package me.axecraft.axecraft_commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import org.bukkit.block.Block;
import me.axecraft.axecraft_commands.Axecraft_inputs;

import java.util.HashMap;
import me.axecraft.axecraft_commands.vars_arrays;

public final class Axecraft_commands extends JavaPlugin implements Listener {
    public HashMap<Player, Integer> got_item = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("JOSEPH is testing the doggy");
        getServer().getPluginManager().registerEvents(this, this);
        // vars_arrays.put_items();
       // getServer().getPluginManager().getClass()

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("This is a test for shutdown");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(command.getName().equals("lobby")){
                Location location = new Location(Bukkit.getWorld("AxeCraft"),295.5, 140, -49.5);
                location.setPitch(-0.7F);
                location.setYaw(6.1F);
                player.teleport(location);
            } else if(command.getName().equals("clear_items")){
                got_item.put(player, 0);
                player.getInventory().clear();
                player.sendMessage("Inventory cleared");
            }
        }else{
            System.out.println("This is a command for players not console");
        }

        return false;
    }
    @EventHandler
    public void onButtonPress (PlayerInteractEvent ev) {
        if ((ev.getClickedBlock() != null) && (ev.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            Player player = ev.getPlayer();
            int has_value = got_item.get(player);
            if(has_value == 1){
                ///means they already have items
                /// don't give them any more
                player.sendMessage("You have already been given items. Enter 'clear_items' command to clear");

            }
            else if (has_value == 0){
                ///give them items and make a note in hashmap that those items have
                ///  been given
                int set_value = 1;
                got_item.put(player, set_value);
                ///placeholder for checking location
                ///create array for the items you need, using keys from items
                int[] i_togive_archer = new int[]{262, 262, 298, 5, 322, 308, 301};
                int counter = 0;
                //HashSet<Integer> boots = new HashSet<>();
                ///you need a Hash Set because for some reason 'contains' doesn't work with simple array
                    //boots.add(301);
                    //boots.add(305);
                Integer[] boot_list = new Integer[]{301, 305};
                // Convert String Array to List
                List<Integer> boots = Arrays.asList(boot_list);
                for (int i:i_togive_archer){

                    ItemStack item_name = vars_arrays.axecraft_items.get(i);
                    player.sendMessage("I am giving you" + item_name);
                    if(i == 298){  ///put helmet on head
                        player.getInventory().setHelmet(item_name);
                    }
                    else if(i == 322){ /// put chestplate on

                        player.getInventory().setChestplate(item_name);
                    }
                    else if ((boots).contains(i)){ ///put on boots

                        player.getInventory().setBoots(item_name);

                    }
                    else {
                        counter++;
                        if(i == 5){
                            ///enchant the bow
                            item_name.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
                        }
                        player.getInventory().setItem(player.getInventory().firstEmpty(), item_name);
                    }
                }
            }

            //Block block = (Block) ev.getClickedBlock();
            //player.sendMessage("The location of this block is " + block.getLocation());
            //System.out.println("The player " + player.getDisplayName() +
             //       " touched a block located at " + block.getLocation());
            player.sendMessage("Your got_item value is " + has_value);
            player.sendMessage("Result of dog is " + vars_arrays.axecraft_items.get("dog"));
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        got_item.put(player, 0);
        int value = got_item.get(player);
        player.sendMessage("Your got_item is " + value);

    }
}
