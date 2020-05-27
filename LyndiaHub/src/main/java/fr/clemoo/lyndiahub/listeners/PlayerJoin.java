package fr.clemoo.lyndiahub.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.clemoo.lyndiahub.LyndiaHub;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(player != null) {
			Bukkit.getScheduler().runTaskLater(LyndiaHub.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					player.sendMessage("§aWelcome to my server §d" + player.getDisplayName() + " §a,how are you?,\n§c->if you have problem, please contact a administrator!");
				}
			}, 20*3L);
		}
	}

}
