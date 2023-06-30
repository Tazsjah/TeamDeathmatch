package me.Tazsjah.TeamDM;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tazsjah.TeamDM.Commands.ForceStart;
import me.Tazsjah.TeamDM.Commands.ForceStop;
import me.Tazsjah.TeamDM.Commands.Help;
import me.Tazsjah.TeamDM.Commands.HowTo;
import me.Tazsjah.TeamDM.Commands.Lobby;
import me.Tazsjah.TeamDM.Commands.Remove;
import me.Tazsjah.TeamDM.Commands.SetPoint;
import me.Tazsjah.TeamDM.Events.Combat;
import me.Tazsjah.TeamDM.Events.General;
import me.Tazsjah.TeamDM.Events.GoldenApples;
import me.Tazsjah.TeamDM.Events.Map;
import me.Tazsjah.TeamDM.Managers.GameState;
import me.Tazsjah.TeamDM.Managers.KitManager;
import me.Tazsjah.TeamDM.Managers.LocationManager;
import me.Tazsjah.TeamDM.Utils.Config;
import me.Tazsjah.TeamDM.Utils.Sidebar;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	// Calling classes & instances
	Config config = new Config();
	LocationManager lm = new LocationManager();
	Sidebar bar = new Sidebar();
	KitManager km = new KitManager();

	General general = new General(config, lm, bar,km);
	Help help = new Help(config);
	Lobby arena = new Lobby(config, lm);
	SetPoint sp = new SetPoint(lm, config);
	Map map = new Map(general, lm);
	Combat ch = new Combat(general, lm, km);

	public void onEnable() {
		
		// Startup Events
		this.saveResource("config.yml", false);
		GameState.setState(GameState.HALT);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Enabling Team Deathmatch");
		
		// Registering Commands
		this.getCommand("svd").setExecutor(new Help(config));
		this.getCommand("setlobby").setExecutor(new Lobby(config, lm));
		this.getCommand("setpoint").setExecutor(new SetPoint(lm, config));
		this.getCommand("forcestop").setExecutor(new ForceStop(config, general, bar));
		this.getCommand("forcestart").setExecutor(new ForceStart(config, general));
		this.getCommand("remove").setExecutor(new Remove(config, general));
		this.getCommand("howto").setExecutor(new HowTo());
		this.getCommand("setend").setExecutor(new SetEnd(config, lm));
	

		// Registering Events
		Bukkit.getPluginManager().registerEvents(general, this);
		Bukkit.getPluginManager().registerEvents(map, this);
		Bukkit.getPluginManager().registerEvents(ch, this);
		Bukkit.getPluginManager().registerEvents(new GoldenApples(), this);
		Bukkit.getPluginManager().registerEvents(new HowTo(), this);
	}
	

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disabling Team Deathmatch");
	}

}
