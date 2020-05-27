package fr.clemoo.lyndiahub.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4Please do this command in-game!");
			return false;
		}
		Player player = (Player) sender;
		if(player != null && label.equalsIgnoreCase("test")) {
			player.sendMessage("§cYou successfully passed the test");
			return true;
		}
		return true;
	}

}
