package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class RegenerationRunnable {

    private final RegenRegion main;

    private boolean enabled = true;
    private List<Region> listRegion = new ArrayList<>();

    public RegenerationRunnable(RegenRegion main){
        this.main = main;
        listRegion = main.getRegionManager().getRegionList("regeneration");

        if(listRegion.isEmpty()){
            enabled = false;
            return;
        }

        Bukkit.getScheduler().runTaskTimer(main, () -> {
            System.out.println("runnable working!");
            for(Player player : Bukkit.getOnlinePlayers()){

                player.sendMessage(ChatColor.LIGHT_PURPLE + "hi this is working!");
                for(Region tempRegion : listRegion){
                    if(tempRegion.getCuboid().contains(player.getLocation())){
                        player.sendMessage(ChatColor.GOLD + "You are in the AREA!!");
                    }
                }

            }
        }, 0, 100);

    }

    public boolean isEnabled(){ return enabled; }


}
