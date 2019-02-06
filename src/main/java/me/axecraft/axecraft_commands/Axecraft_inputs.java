package me.axecraft.axecraft_commands;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.HashMap;


public class Axecraft_inputs extends JavaPlugin implements Listener {
public HashMap<Player, Integer> got_item = new HashMap<>();
//public FixedMetadataValue(Axecraft_inputs, 0) pt;
public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
}


}
