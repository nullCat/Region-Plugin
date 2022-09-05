package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.List;

public class RegenerationRunnable {

    private final RegenRegion main;
    private static List<Region> listRegion;

    public RegenerationRunnable(RegenRegion main, Boolean executeRunnable){
        this.main = main;
        listRegion = main.getRegionManager().getRegionList("regeneration");
        if(executeRunnable){ run(); }


    }

    public void run(){

        Bukkit.getScheduler().runTaskTimer(main, () -> {

            for(Player player : Bukkit.getOnlinePlayers()){

                for(Region tempRegion : listRegion){
                    if(tempRegion.getCuboid().contains(player.getLocation())){
                        if(!player.hasPotionEffect(PotionEffectType.REGENERATION) && player.getHealth() < 20.0){
                            player.sendMessage(ChatColor.GOLD + "Received regeneration effect!");
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2, true ,true ,false));
                        }

                    }
                }

            }
        }, 0, 20);


    }

    public void updateListRegion(){
        listRegion = main.getRegionManager().getRegionList("regeneration"); //update the list
    }


}
