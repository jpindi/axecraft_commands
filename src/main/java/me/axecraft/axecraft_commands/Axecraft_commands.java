package me.axecraft.axecraft_commands;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Particle.DustOptions;
import java.util.*;
import org.bukkit.block.Block;
import me.axecraft.axecraft_commands.pvp.giveItems;

public final class Axecraft_commands extends JavaPlugin implements Listener {

    public HashMap<Player, Integer> got_item = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("JOSEPH is testing the plugins");
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("This is a test for shutdown");
    }

// entity.getWorld().spawnParticle(particle, location, 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(command.getName().equals("lobby")){
                Location location = new Location(Bukkit.getWorld("AxeCraft"),295.5, 140, -49.5);
                // Bukkit.getWorld("AxeCraft").spawnParticle(Particle.FLAME, location, 50, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                Bukkit.getWorld("AxeCraft").spawnParticle(Particle.FLAME, location, 50, 0, 0, 0);
                location.setPitch(-0.7F);
                location.setYaw(6.1F);
                player.teleport(location);
                clearPlayerItems(player);
            } else if(command.getName().equals("clear_items")){
                clearPlayerItems(player);
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
            Block block = ev.getClickedBlock();
            Location player_loc = block.getLocation();
                ///give them items and make a note in hashmap that those items have
                ///  been given
                Location arrow_loc = new Location(Bukkit.getWorld("AxeCraft"), 244, 136, -71); //give arrows
                Location sword_loc = new Location(Bukkit.getWorld("AxeCraft"), 234,136,-71); //give sword kit
                if (player_loc.distance(arrow_loc) < 3) {
                    int[] i_togive = new int[]{2620, 267, 298, 5, 322, 308, 301, 303};
                    giveItems g = new giveItems();
                    g.giveItemstoPlayer(i_togive, player, "a", got_item.get(player)); ///KEEP GOING WITH THIS
                   // giveItems(i_togive, player, "a");
                }else if(player_loc.distance(sword_loc) < 3){
                    int[] i_togive = new int[]{262, 298, 5, 322, 308, 305, 267, 303}; // this is for swordsman
                    giveItems g = new giveItems();
                    g.giveItemstoPlayer(i_togive, player, "s", got_item.get(player));
                    //giveItems(i_togive, player, "s");
                }
            }

        }



    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();

        if(entity instanceof Player){
                Player player = (Player) e.getEntity();
              Location player_loc = entity.getLocation();
              double x = player_loc.getX();
              double z = player_loc.getZ();
              if(x > 205 && x < 266 && z < -42 && z > -88){
                  Location pvp_loc = new Location(Bukkit.getWorld("AxeCraft"), 240, 137, -67.5);
                  entity.teleport(pvp_loc);
                  player.setHealth(16);
                  clearPlayerItems(player);
              }
            ///get the edges min / max of x and y (don't need z)
            /// if within that min and max of the player area, then
        }
        }

        @EventHandler
        public void onDamage(EntityDamageByBlockEvent e){
        Entity entity = e.getEntity();
        if(entity instanceof Player){
            Player player = (Player) e.getEntity();
            double h = player.getHealth();
            double d = e.getDamage();
            if (d > h){
           // return;

                e.setCancelled(true);
                resetPVP(player);

            }
        }
        }
    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e){
        Entity entity = e.getEntity();
        if(entity instanceof Player){
            Player player = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            // return;
            double h = player.getHealth();
            double d = e.getDamage();
            if (d > h){
                /////------NEED TO ADD LOCATION CHECK TO THIS
                e.setCancelled(true);
                player.sendMessage("You were defeated by " + ChatColor.GREEN + damager.getDisplayName());
                damager.sendMessage("You defeated " + ChatColor.RED + player.getDisplayName());
                resetPVP(player);

            }
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        got_item.put(player, 0);
        int value = got_item.get(player);
        String h = getConfig().getString("WelcomeHeader");
        String s = getConfig().getString("WelcomeFooter");
        player.sendTitle(ChatColor.WHITE + h, ChatColor.DARK_PURPLE + s,40,60,20);

    }

    public void clearPlayerItems(Player player){
        got_item.put(player, 0);
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.sendMessage("Inventory cleared");

    }
    public void resetPVP(Player player){
        Location pvp_loc = new Location(Bukkit.getWorld("AxeCraft"), 239.5, 137, -67.5);
        System.out.println("Player should have respawned.");
        pvp_loc.setPitch(37F);
        pvp_loc.setYaw(6.1F);
        player.teleport(pvp_loc);
        player.setGameMode(GameMode.CREATIVE);// you toggle this in case the player is on fire or something
        clearPlayerItems(player);
        //wait(500);
        try {
            Thread.sleep(500);
        } catch(InterruptedException I){
            System.out.println("Thread sleep got interrupted");
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(16);

    }

}
