package de.kevrecraft.api.classes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileWriter {
	
	private File file;
	private YamlConfiguration config;
	
	public FileWriter(String path, String name) {
		this.file = new File("plugins//KevReCraft//" + path, name + ".yml");
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileWriter(String name) {
		this.file = new File("plugins//KevReCraft//", name + ".yml");
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public boolean exist() {
		return file.exists();
	}
	
	// SETer --------------------------------------------------------------------
	public FileWriter setValue(String path, Object value) {
		this.config.set(path, value);
		return this;
	}
	
	public FileWriter setBlockArea(String path, BlockArea blockArea) {
		this.config.set(path + ".world", blockArea.getLocationA().getWorld().toString());
		
		this.config.set(path + ".a.x", blockArea.getLocationA().getX());
		this.config.set(path + ".a.y", blockArea.getLocationA().getY());
		this.config.set(path + ".a.z", blockArea.getLocationA().getZ());
		this.config.set(path + ".a.yaw", blockArea.getLocationA().getYaw());
		this.config.set(path + ".a.pitch", blockArea.getLocationA().getPitch());
		
		this.config.set(path + ".b.x", blockArea.getLocationB().getX());
		this.config.set(path + ".b.y", blockArea.getLocationB().getY());
		this.config.set(path + ".b.z", blockArea.getLocationB().getZ());
		this.config.set(path + ".b.yaw", blockArea.getLocationB().getYaw());
		this.config.set(path + ".b.pitch", blockArea.getLocationB().getPitch());
		
		return this;
	}
	
	//GETer ---------------------------------------------------------------------
	public Object getObject(String path) {
		return this.config.get(path);
	}
	
	public BlockArea getBlockArea(String path) {
		World world = Bukkit.getWorld(this.config.getString(path + ".world"));
		
		double aX = this.config.getDouble(path + ".a.x");
		double aY = this.config.getDouble(path + ".a.y");
		double aZ = this.config.getDouble(path + ".a.z");
		
		float aYaw = (float) this.config.get(path + ".a.yaw");
		float aPitch = (float) this.config.get(path + ".a.pitch");
		
		double bX = this.config.getDouble(path + ".b.x");
		double bY = this.config.getDouble(path + ".b.y");
		double bZ = this.config.getDouble(path + ".b.z");
		
		float bYaw = (float) this.config.get(path + ".b.yaw");
		float bPitch = (float) this.config.get(path + ".b.pitch");
		
		Location a = new Location(world, aX, aY, aZ, aYaw, aPitch);
		Location b = new Location(world, bX, bY, bZ, bYaw, bPitch);
		
		return new BlockArea(a, b);
	}
	
	public int getInt(String path) {
		return this.config.getInt(path);
	}
	
	public double getDouble(String path) {
		return this.config.getDouble(path);
	}
	
	public float getFloat(String path) {
		return (float) this.config.get(path);
	} 
	
	public long getLong(String path) {
		return this.config.getLong(path);
	}
	
	public boolean getBoolean(String path) {
		return this.config.getBoolean(path);
	}
	
	public String getString(String path) {
		return this.config.getString(path);
	}
	
	public List<String> getStringList(String path) {
		return this.config.getStringList(path);
	}
	
	public List<Integer> getIntList(String path) {
		return this.config.getIntegerList(path);
	}
	
	public List<Boolean> getBooleanList(String path) {
		return this.config.getBooleanList(path);
	}
	
	public List<Byte> getByteList(String path) {
		return this.config.getByteList(path);
	}
	
	public Set<String> getKeys(boolean deep) {
		return this.config.getKeys(deep);
	}
	
	public ConfigurationSection getConfigurationSection(String section) {
		return this.config.getConfigurationSection(section);
	}
	
	public FileWriter save() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this;
	}
}
