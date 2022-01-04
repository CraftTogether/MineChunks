package com.github.crafttogether.minechunks.commands;

import com.github.crafttogether.minechunks.MineChunks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LoadCommand implements CommandExecutor {
    private static int loadedChunks = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You must run this command as a player");
        } else {
            Player player = (Player) commandSender;
            System.out.println(MineChunks.getPlugin().getConfig().getInt("maxLoadedChunks"));
            if (MineChunks.getPlugin().getConfig().getInt("maxLoadedChunks") <= loadedChunks) {
                player.sendMessage(ChatColor.RED + "Maximum loaded chunks has been reached");
                return true;
            }
            player.getChunk().setForceLoaded(true);
            loadedChunks++;
            player.sendMessage(ChatColor.GREEN + "The chunk you are in has been loaded");
        }
        return true;
    }
}
