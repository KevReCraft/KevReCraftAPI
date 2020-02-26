package de.kevrecraft.api.classes;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockArea {
	private Location a;
	private Location b;
	
	public BlockArea(Location a, Location b) {
		this.a = a;
		this.b = b;
	}
	
	public ArrayList<Block> getBlockList() {
		ArrayList<Block> list = new ArrayList<Block>();
		
		int maxX = Math.max(this.a.getBlockX(), this.b.getBlockX());
		int maxY = Math.max(this.a.getBlockY(), this.b.getBlockY());
		int maxZ = Math.max(this.a.getBlockZ(), this.b.getBlockZ());
		
		int minX = Math.min(this.a.getBlockX(), this.b.getBlockX());
		int minY = Math.min(this.a.getBlockY(), this.b.getBlockY());
		int minZ = Math.min(this.a.getBlockZ(), this.b.getBlockZ());
		
		for(int x = minX; x <= maxX; x++) {
			for(int y = minY; y <= maxY; y++) {
				for(int z = minZ; z <= maxZ; z++) {
					list.add(a.getWorld().getBlockAt(new Location(a.getWorld(), x, y, z)));
				}
			}
		}
		return list;
	}
	
	public void setLocationA(Location a) {
		this.a = a;
	}
	
	public void setLocationB(Location b) {
		this.b = b;
	}
	
	public Location getLocationA() {
		return this.a;
	}
	
	public Location getLocationB() {
		return this.b;
	}

}
