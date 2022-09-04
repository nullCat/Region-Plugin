package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MineralRunnable {


    private RegenRegion main;
    private static List<Region> listRegion = new ArrayList<>();

    private int i = 0;

    private Block actualBlock;

    public MineralRunnable(RegenRegion main, Boolean executeRunnable){
        this.main = main;
        listRegion = main.getRegionManager().getRegionList("mineral");
        if(executeRunnable) { run();}

    }

    public void spawnBlock(BlockData blockData){

        //verify if the block at the same location has changed
        System.out.println("Actual Block: " + actualBlock.getType() + "  old block: " + blockData.getMaterial());
        if(actualBlock.getType() == Material.AIR){
            System.out.println("Setting new blocks!");
            BlockManager.setBlock(blockData,actualBlock); //spawn block in the original location if changed
        }

    }

    public void run(){


        Bukkit.getScheduler().runTaskTimer(main, () -> {
            i++;
            if(i == 50) { i = 0; }

            if(!listRegion.isEmpty()){


                if(i % 5 == 0){ //carbon: each 5 iterations / seconds

                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Carbon has been refilled!");
                    }
                    for(Region tempRegion : listRegion){

                        System.out.println("Carbon list length: " + tempRegion.getCarbonList().size());
                        //each region has a list of the specified mineral that we want to spawn.
                        for(BlockData blockData : tempRegion.getCarbonList()){
                            actualBlock = blockData.getBlock();
                            spawnBlock(blockData);
                        }

                    }

                } else if(i % 12 == 0){ //iron: each 12 iterations / seconds

                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Iron has been refilled!");
                    }
                    for(Region tempRegion : listRegion){

                        System.out.println("Iron list length: " + tempRegion.getIronList().size());
                        //each region has a list of the specified mineral that we want to spawn.
                        for(BlockData blockData : tempRegion.getIronList()){
                            actualBlock = blockData.getBlock();
                            spawnBlock(blockData);
                        }

                    }
                } else if(i % 16 == 0){ //gold: each 16 iterations / seconds

                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Gold has been refilled!");
                    }
                    for(Region tempRegion : listRegion){

                        System.out.println("Gold list length: " + tempRegion.getGoldList().size());
                        //each region has a list of the specified mineral that we want to spawn.
                        for(BlockData blockData : tempRegion.getGoldList()){
                            actualBlock = blockData.getBlock();
                            spawnBlock(blockData);
                        }

                    }
                } else if(i % 23 == 0){ //diamond: each 23 iterations / seconds

                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Diamond has been refilled!");
                    }
                    for(Region tempRegion : listRegion){

                        System.out.println("Diamond list length: " + tempRegion.getDiamondList().size());
                        //each region has a list of the specified mineral that we want to spawn.
                        for(BlockData blockData : tempRegion.getDiamondList()){
                            actualBlock = blockData.getBlock();
                            spawnBlock(blockData);
                        }

                    }
                }
            }

            System.out.println("time passed: " + i + " | region list length: " + listRegion.size());


        }, 0, 20);

    }

    public void updateListRegion(){
        listRegion = main.getRegionManager().getRegionList("mineral");
    }

}
