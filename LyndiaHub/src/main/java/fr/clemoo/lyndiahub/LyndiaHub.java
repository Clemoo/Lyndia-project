package fr.clemoo.lyndiahub;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.clemoo.lyndiahub.commands.TestCommand;
import fr.clemoo.lyndiahub.listeners.PlayerChat;
import fr.clemoo.lyndiahub.listeners.PlayerJoin;
import fr.clemoo.lyndiahub.mysql.DatabaseManager;

/**
 * Copyright (c) undefended
 * @author Clemoo, Unprovided
 *
 */

public class LyndiaHub extends JavaPlugin {
	
	private static LyndiaHub Instance;
	private DatabaseManager databaseManager;
	
	@Override
	public void onEnable() {
		Instance = this;
		setup();
	}
	@Override
	public void onDisable() {
		databaseManager.disconnection();
	}
	
	public void setup() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new PlayerJoin(), this);
		pluginManager.registerEvents(new PlayerChat(), this);
		this.getCommand("test").setExecutor(new TestCommand());
		databaseManager = new DatabaseManager("jdbc:mysql://", "localhost", 3308, "lyndiahub", "root", "");
		databaseManager.connection();
	}
	
	public static LyndiaHub getInstance() {
		return Instance;
	}
	
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

}
