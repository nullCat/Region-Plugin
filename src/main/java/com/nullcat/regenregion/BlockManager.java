package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockManager {

    public static void setBlock(BlockData blockData, Block actualBlock){

        actualBlock.getWorld().getBlockAt(actualBlock.getLocation()).setType(blockData.getMaterial());

    }

    public static void removeBlock(Location location){

        location.getWorld().getBlockAt(location).setType(Material.AIR); //set air block

    }

    public static void mineralToList(Region region){ //analyze the cuboid and adds every mineral type to their individual region list

        System.out.println("Region Block info: ");
        for(Block block : region.getCuboid().getBlocks()){

            BlockData blockData = new BlockData(block.getLocation(), block.getType(), block);

            System.out.println("Block: " + blockData.getMaterial());

            if(block.getType() == Material.COAL_ORE){
                region.getCarbonList().add(blockData);
            } else if (block.getType() == Material.IRON_ORE){
                region.getIronList().add(blockData);
            } else if (block.getType() == Material.GOLD_ORE){
                region.getGoldList().add(blockData);
            } else if (block.getType() == Material.DIAMOND_ORE){
                region.getDiamondList().add(blockData);
            } else if (block.getType() == Material.NETHERITE_BLOCK){
                region.getNetheriteList().add(blockData);
            }  else if (block.getType() == Material.REDSTONE_ORE){
                region.getRedstoneList().add(blockData);
            }

        }

    }


}
