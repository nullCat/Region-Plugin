package com.nullcat.regenregion;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    private RegenRegion main;

    public EventListener(RegenRegion main){
        this.main = main;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        main.getPos1().remove(player.getUniqueId()); //remove HashMap key
        main.getPos2().remove(player.getUniqueId());

    }


}
