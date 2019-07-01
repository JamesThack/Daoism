package com.daoism.cultivation.Commands;

import com.daoism.cultivation.API.PlayerMethods;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

/**
 * This is the command base for the /daoism command
 */
public class DaoismCommand extends CommandBase {

    /**
     * Gets the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return "daoism";
    }

    /**
     * Returns the usage of the command
     * @param sender The sender
     * @return The command usage
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return "command.daoism.usage";
    }

    /**
     * This is where all the code related to running the command works
     * @param server An instance of the server
     * @param sender The sender
     * @param args Any arguments with the command
     * @throws CommandException
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            if(args.length >= 3 && args[0].equalsIgnoreCase("spirit")) {
                if(args[1].equalsIgnoreCase("add")) {
                    int addItion = 0;
                    try {
                        addItion = Integer.valueOf(args[2]);

                    } catch(Exception e) {
                        PlayerMethods.sendMsgToPlayer(player, "Invalid cultivation level", new Style().setColor(TextFormatting.RED));
                    }
                    PlayerMethods.addEntityCultivation(addItion, player);
                }
            } else {
                PlayerMethods.sendMsgToPlayer(player, "/daoism help", new Style().setColor(TextFormatting.RED));
            }
        }
    }

}
