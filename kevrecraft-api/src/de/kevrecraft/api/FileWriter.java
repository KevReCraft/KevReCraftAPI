package de.kevrecraft.api;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileWriter {
	
	private File file;
	private YamlConfiguration config;
	
	public FileWriter(String path, String name) {
		this.file = new File("//plugins//KevReCraft//" + path, name + ".yml");
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileWriter(String name) {
		this.file = new File("//plugins//KevReCraft//", name + ".yml");
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileWriter setValue(String path, Object value) {
		this.config.set(path, value);
		return this;
	}
	
	public boolean exist() {
		return file.exists();
	}
	
	public Object getObject(String path) {
		return this.config.get(path);
	}
	
	public int getInt(String path) {
		return this.config.getInt(path);
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
