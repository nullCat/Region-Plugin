package com.nullcat.regenregion;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockData {

    //stores data that can't be changed by cuboid
    private Location location;
    private Material material;
    private Block block;

    public BlockData(Location location, Material material, Block block){
        this.location = location;
        this.material = material;
        this.block = block;
    }

    public BlockData(){}


    public Location getLocation() { return location; }

    public Material getMaterial() { return material; }

    public Block getBlock(){ return block; }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    public void setBlock(Block block){
        this.block = block;
    }

}
