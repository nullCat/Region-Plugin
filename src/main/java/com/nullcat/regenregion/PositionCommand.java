package com.nullcat.regenregion;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PositionCommand implements CommandExecutor {

    /*
    *       /pos <order>
    *       (max order: 2)
     */

    private RegenRegion main;

    public PositionCommand(RegenRegion main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            //permissions
            if(player.hasPermission("RR.pos")){

                //get block
                Block block = player.getTargetBlock(null, 100);
                if(block.getType() != Material.AIR){
                    if(args.length == 1){

                        int intPos = Integer.parseInt(args[0]);

                        if(!(intPos <= 0 || intPos > 2)){
                            //getting coordinates
                            Position pos1 = null;
                            Position pos2 = null;
                            int x,y,z;
                            x = block.getX();
                            y = block.getY();
                            z = block.getZ();

                            //storage position
                            if(intPos == 1){
                                pos1 = new Position(x,y,z);
                                main.setPos1(player.getUniqueId(),pos1);
                            }else{
                                pos2 = new Position(x,y,z);
                                main.setPos2(player.getUniqueId(),pos2);
                            }

                            player.sendMessage(ChatColor.LIGHT_PURPLE + "pos" + intPos + " (" + x + " ; " + y + " ; " + z + ") has been successfully set!");

                        }


                    }else{
                        player.sendMessage(ChatColor.RED + "Invalid Usage, try /pos <order>");
                    }
                }

            }else{
                player.sendMessage(ChatColor.RED + "You don't have permission to perform this command!");
            }

        }


        return false;
    }
}
