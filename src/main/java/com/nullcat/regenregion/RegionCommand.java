package com.nullcat.regenregion;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCommand implements CommandExecutor {

    /*
    * Command usage
    *  /region <create/list/remove/info> <region name> <type>
    *
     */

    private final RegenRegion main;
    private RegionManager regionManager;

    public RegionCommand(RegenRegion main){
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 3){

                //create argument
                if(args[0].equalsIgnoreCase("create")){
                    if(args[2].equalsIgnoreCase("mineral")){

                        if(!main.getRegionData().containsKey(player.getUniqueId())){
                            //create a new instance
                            main.newRegionData(player.getUniqueId());

                        }

                        if(main.getPos1().get(player.getUniqueId()) != null && main.getPos2().get(player.getUniqueId()) != null){
                            Region tempRegion = new Region();
                            tempRegion.setName(args[1]);
                            tempRegion.setOwner(player.getUniqueId());
                            tempRegion.setPos1(main.getPos1().get(player.getUniqueId()));
                            tempRegion.setPos2(main.getPos2().get(player.getUniqueId()));
                            tempRegion.setType(args[2]);
                            regionManager = main.getRegionManager();

                            //set the new instance
                            tempRegion.setCuboid(createCuboid(tempRegion));
                            main.setRegionData(player.getUniqueId(), tempRegion); //add to specified uuid hashmap
                            main.addRegionGenericList(tempRegion); //add to generic region class list
                            regionManager.setRegion(tempRegion); //write regions.yml file
                            BlockManager.mineralToList(tempRegion); //adds every mineral block inside the region into a list
                            MineralRunnable mineralRunnable = new MineralRunnable(main, false); //update region list from runnable class
                                                                                                                // without executing another runnable
                            mineralRunnable.updateListRegion(); //this will update the static private list

                            //success!
                            player.sendMessage(ChatColor.YELLOW + "the region " + tempRegion.getName() + " has been successfully created!");
                        }else{
                            //error
                            player.sendMessage(ChatColor.RED + "Error, you need a position first!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Error, Invalid Type!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Error, Invalid Argument!");
                }

            }else if(args.length == 1){

                if(args[0].equalsIgnoreCase("list")){
                    //returns the player a String List
                    player.sendMessage(ChatColor.GREEN + "< Region List >");

                    if(main.getRegionData().containsKey(player.getUniqueId())){
                        int i = 0;
                        for(Region tempRegion : main.getRegionData().get(player.getUniqueId())){
                            i++;
                            if(tempRegion == null){
                                player.sendMessage(ChatColor.GREEN + "No entries :(");
                            }else{
                                player.sendMessage(ChatColor.GREEN.toString() + "-" + i + ". "
                                        + tempRegion.getName() + " -> " + "( " + tempRegion.getPos1().getX() + " ; " + tempRegion.getPos1().getY() + " ; " + tempRegion.getPos1().getZ()
                                        + " ) to ( " + tempRegion.getPos2().getX() + " ; " + tempRegion.getPos2().getY() + " ; " + tempRegion.getPos2().getZ()
                                        + " ) ");
                            }
                        }
                    }else{
                        player.sendMessage(ChatColor.GREEN + "No entries :(");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Error, Invalid Argument!");
                }


            }


        }


        return false;
    }


    public Cuboid createCuboid(Region tempRegion){

        Cuboid cuboid = new Cuboid(
                new Location(Bukkit.getWorld("world"), tempRegion.getPos1().getX(), tempRegion.getPos1().getY(), tempRegion.getPos1().getZ()),
                new Location(Bukkit.getWorld("world"), tempRegion.getPos2().getX(), tempRegion.getPos2().getY(), tempRegion.getPos2().getZ()));


        return cuboid;
    }
}
