package fr.clemoo.lyndiahub.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.clemoo.lyndiahub.LyndiaHub;

public class PlayerChat implements Listener {

	private List<Player> couldownPlayer = new ArrayList<>();
	
	@EventHandler
	public void playerChatInteract(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(couldownPlayer.contains(player)) {
			player.sendMessage("§cYou already can send a message every 5 secondes, please wait before send a new message!");
			event.setCancelled(true);
			return;
		}
		Bukkit.getScheduler().runTaskLater(LyndiaHub.getInstance(), new Runnable() {
			
			@Override
			public void run() {
					couldownPlayer.remove(player);
			}
		}, 20*5L);
		
		couldownPlayer.add(player);
	}

}
