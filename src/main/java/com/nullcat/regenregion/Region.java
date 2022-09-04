package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Region {


    private List<BlockData> carbonList = new ArrayList<>();
    private List<BlockData> ironList = new ArrayList<>();
    private List<BlockData> goldList = new ArrayList<>();
    private List<BlockData> copperList = new ArrayList<>();
    private List<BlockData> diamondList = new ArrayList<>();
    private List<BlockData> netheriteList = new ArrayList<>();
    private List<BlockData> redstoneList = new ArrayList<>();
    private Position pos1,pos2;
    private String name, type;
    private UUID owner;

    private Cuboid cuboid;

    public Position getPos1() { return pos1; }
    public Position getPos2() { return pos2; }

    public String getType(){
        return type;
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() { return owner; }

    public Cuboid getCuboid(){ return cuboid; }

    public void setType(String type){
        this.type = type;
    }

    public void setPos1(Position pos1){
        this.pos1 = pos1;
    }

    public void setPos2(Position pos2){
        this.pos2 = pos2;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setOwner(UUID owner){
        this.owner = owner;
    }

    public void setCuboid(Cuboid cuboid) { this.cuboid = cuboid; }

    public List<BlockData> getCarbonList() {
        return carbonList;
    }

    public List<BlockData> getIronList() {
        return ironList;
    }

    public List<BlockData> getCopperList() {
        return copperList;
    }

    public List<BlockData> getGoldList() {
        return goldList;
    }

    public List<BlockData> getDiamondList() {
        return diamondList;
    }

    public List<BlockData> getNetheriteList() {
        return netheriteList;
    }

    public List<BlockData> getRedstoneList() {
        return redstoneList;
    }

}
