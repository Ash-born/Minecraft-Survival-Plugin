package fr.minecraft.survival.plugin.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player))
            return false;

        Player player = (Player) commandSender;
        if (player.isInvisible()) {
            player.setGameMode(GameMode.CREATIVE);
            player.setInvisible(false);
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.setInvisible(true);
        }

        return true;
    }
}
