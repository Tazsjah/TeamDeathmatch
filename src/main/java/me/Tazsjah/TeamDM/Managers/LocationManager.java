package me.Tazsjah.TeamDM.Managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {

	// THIS LOCATION IS FOR THE LOBBY

	public void setLobby(Location loc) {

		File f = new File("plugins/TeamDM/Locations/", "Lobby.yml");
		FileConfiguration lobby = YamlConfiguration.loadConfiguration(f);

		lobby.set("lobby", loc);

		try {
			lobby.save(f);
		}catch (IOException e){
			e.printStackTrace();
		}

		return;


	}
	

	public Location getLobby() {

		File f = new File("plugins/TeamDM/Locations/", "Lobby.yml");
		FileConfiguration lobby = YamlConfiguration.loadConfiguration(f);

		return (Location) lobby.get("lobby");

	}



	// THIS LOCATION IS FOR THE SLAYERS

	public void setA(Location loc) {

		File f = new File("plugins/TeamDM/Locations/", "Slayers.yml");
		FileConfiguration slayers = YamlConfiguration.loadConfiguration(f);

		slayers.set("location", loc);

		try {
			slayers.save(f);
		}catch (IOException e){
			e.printStackTrace();
		}

		return;

	}

	public Location getA() {

		File f = new File("plugins/TeamDM/Locations/", "Slayers.yml");
		FileConfiguration slayers = YamlConfiguration.loadConfiguration(f);


		return (Location) slayers.get("location");

	}

	// THIS LOCATION IS FOR THE DRAGONS\

	public void setB(Location loc) {

		File f = new File("plugins/TeamDM/Locations/", "Dragons.yml");
		FileConfiguration dragons = YamlConfiguration.loadConfiguration(f);

		dragons.set("location", loc);

		try {
			dragons.save(f);
		}catch (IOException e){
			e.printStackTrace();
		}

		return;

	}

	public Location getB() {

		File f = new File("plugins/TeamDM/Locations/", "Dragons.yml");
		FileConfiguration dragons = YamlConfiguration.loadConfiguration(f);


		return (Location) dragons.get("location");

	}
	
	// Location for the end of the game
	
	public void setEnd(Location loc) {

		File f = new File("plugins/TeamDM/Locations/", "End.yml");
		FileConfiguration end = YamlConfiguration.loadConfiguration(f);

		end.set("location", loc);

		try {
			end.save(f);
		}catch (IOException e){
			e.printStackTrace();
		}

		return;


	}
	
	public Location getEnd() {
		File f = new File("plugins/TeamDM/Locations/", "End.yml");
		FileConfiguration end = YamlConfiguration.loadConfiguration(f);
		
		return end.getLocation("location");
		
	}


}
