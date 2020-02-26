package de.kevrecraft.api.classes;

public class Job {
	private int lvl;
	private int exp;
	private int points;
	private String name;
	
	public Job(int level, int experience, int points, String name) {
		this.lvl = level;
		this.exp = experience;
		this.points = points;
		this.name = name;
	}
	
	public Job(String name) {
		this.lvl = 1;
		this.exp = 0;
		this.points = 0;
		this.name = name;
	}
	
	public int getLevel() {
		return this.lvl;
	}
	
	public void setExperience(int experience) {
		this.exp = experience;
	}
	
	public int getExperience() {
		return this.exp;
	}
	
	public boolean removePoint() {
		if(this.points>0) {
			this.points--;
			return true;
		}
		return false;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name +"|"+this.lvl+"|"+this.exp+"|"+this.points;
	}
	
	public static Job parseJob(String job) {
		if(job.split("|").length == 4) {
			return new Job(Integer.parseInt(job.split("|")[1]), Integer.parseInt(job.split("|")[2]), Integer.parseInt(job.split("|")[2]), job.split("|")[0]);
		}
		return null;
	}
}
