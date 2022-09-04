package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class RegenRegion extends JavaPlugin {

    private HashMap<UUID, ArrayList<Region>> regionData = new HashMap<UUID, ArrayList<Region>>();
    private HashMap<UUID, Position> pos1 = new HashMap<>();
    private HashMap<UUID, Position> pos2 = new HashMap<>();

    private List<Region> regionGenericList = new ArrayList<>();
    private RegionManager regionManager;
    private RegenerationRunnable regionRunnable;
    private MineralRunnable mineralRunnable;

    @Override
    public void onEnable() {
        getCommand("pos").setExecutor(new PositionCommand(this));
        getCommand("region").setExecutor(new RegionCommand(this));
        Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
        regionManager = new RegionManager(this);
        mineralRunnable = new MineralRunnable(this,true);
        regionRunnable = new RegenerationRunnable(this, true);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public HashMap<UUID, Position> getPos1(){ return pos1; }
    public HashMap<UUID, Position> getPos2(){ return pos2; }

    public void setPos1(UUID uuid, Position pos1){

        this.pos1.put(uuid, pos1);
    }

    public void setPos2(UUID uuid, Position pos2){

        this.pos2.put(uuid, pos2);
    }

    public HashMap<UUID, ArrayList<Region>> getRegionData(){ return regionData; }

    public void newRegionData(UUID uuid){
        ArrayList<Region> arrayRegion = new ArrayList<>();
        regionData.put(uuid, arrayRegion);
    }

    public void setRegionData(UUID uuid, Region tempRegion){
        ArrayList<Region> arrayRegion;
        arrayRegion = regionData.get(uuid);
        arrayRegion.add(tempRegion);
        regionData.put(uuid, arrayRegion);
    }

    public RegionManager getRegionManager(){ return regionManager; }

    public RegenerationRunnable getRegenerationRunnable(){ return regionRunnable; }

    public void setRegenerationRunnable(RegenerationRunnable regionRunnable){
        this.regionRunnable = regionRunnable;
    }

    public List<Region> getRegionGenericList(){ return regionGenericList; }

    public void addRegionGenericList(Region regionElement){
        regionGenericList.add(regionElement);
    }

    public Region getRegionGeneric(String name){
        for(Region tempRegion : regionGenericList){
            if(tempRegion.getName() == name){
                return tempRegion;
            }
        }
        return null;
    }


}

