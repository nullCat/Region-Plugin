package com.nullcat.regenregion;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegionManager {

    private RegenRegion main;
    private File file;
    private YamlConfiguration config;

    public RegionManager(RegenRegion main){

        this.main = main;
        if(!main.getDataFolder().exists()){
            main.getDataFolder().mkdir(); //create a folder if doesnt exists!

        }

        file = new File(main.getDataFolder(), "regions.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    public void setRegion(Region tempRegion){

        List<String> list = new ArrayList<>();

        if(!config.contains(tempRegion.getType())){
            list.add(tempRegion.getName());
        }else{
            list = config.getStringList(tempRegion.getType());
            list.add(tempRegion.getName());
        }

        config.set(tempRegion.getType(), list);


        try {
            config.save(file); //save file before changes
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Region> getRegionList(String type){ //get a list with a specified type of regions

        List<Region> regionList = new ArrayList<>();

        for(Region tempRegion : main.getRegionGenericList()){
            if(tempRegion.getType().equalsIgnoreCase(type)){
                regionList.add(tempRegion);
            }
        }

        return regionList;


    }

    public boolean nullChecker(String type){
        switch(type.toLowerCase()){
            case "regeneration":
                return main.getRegenerationRunnable().isEnabled();

            default:
                return false;
        }
    }


}
